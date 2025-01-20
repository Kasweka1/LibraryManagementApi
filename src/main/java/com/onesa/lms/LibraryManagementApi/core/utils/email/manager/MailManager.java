package com.onesa.lms.LibraryManagementApi.core.utils.email.manager;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.util.concurrent.CompletableFuture;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.onesa.lms.LibraryManagementApi.core.services.messageBroker.config.RabbitMQConfig;
import com.onesa.lms.LibraryManagementApi.core.services.user.models.User;
import com.onesa.lms.LibraryManagementApi.core.utils.email.service.MailService;
import com.onesa.lms.LibraryManagementApi.core.utils.email.utils.MailMessage;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailManager implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.name}")
    private String from;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${app.url}") 
    private String appUrl;

    
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    @Async
    public void sendMail(String to, String subject, String content) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(username, from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace(); 
            throw new RuntimeException("Failed to send plain email", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Unsupported encoding for email", e);
        }
        CompletableFuture.completedFuture(null);
    }

    @Override
    public void updateUserOnSuccessfulAccountCreationAndActivation(User user) {
        try {

            String message = "Welcome to the library system! Please click the link below to activate your account:";
            String emailContent = generateEmailContent(user, message);
            String subject = "Welcome to the Library System!";

            // sendMail(user.getEmail(), subject, emailContent); 

            MailMessage emailMessage = new MailMessage(
                user.getEmail(),
                subject,
                emailContent
        );
        amqpTemplate.convertAndSend(RabbitMQConfig.EMAIL_EXCHANGE, RabbitMQConfig.EMAIL_ROUTING_KEY, emailMessage);
        }
        catch (IOException e) {
            throw new RuntimeException("Failed to send account creation and activation email", e);
        }
    }

    @Override
    public void alertLibrarianOrAdminOnAccountCreation(User user) {
        try {
            String message = "Your account has been successfully created. You can now login to the system.";
            String emailContent = generateEmailContent(user, message );
            String subject = "Account Creation";

            // sendMail(user.getEmail(), subject, emailContent); 

            
            MailMessage emailMessage = new MailMessage(
                user.getEmail(),
                subject,
                emailContent
             );
        amqpTemplate.convertAndSend(RabbitMQConfig.EMAIL_EXCHANGE, RabbitMQConfig.EMAIL_ROUTING_KEY, emailMessage);

        } catch (Exception e) {
            throw new RuntimeException("Failed to send account creation alert to librarian/admin", e);
        }
    }

    private String generateEmailContent(User user, String message) throws IOException {

        ClassPathResource resource = new ClassPathResource("email-template.html");
        String content = new String(Files.readAllBytes(resource.getFile().toPath()));

        
        content = content.replace("{{firstName}}", user.getFirstName());
        content = content.replace("{{lastName}}", user.getLastName());
        content = content.replace("{{message}}", message);

    
        content = content.replace("{{message}}", message);

        return content;
    }


}

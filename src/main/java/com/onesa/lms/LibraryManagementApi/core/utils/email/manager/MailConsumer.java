package com.onesa.lms.LibraryManagementApi.core.utils.email.manager;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onesa.lms.LibraryManagementApi.core.services.messageBroker.config.RabbitMQConfig;
import com.onesa.lms.LibraryManagementApi.core.utils.email.service.MailService;
import com.onesa.lms.LibraryManagementApi.core.utils.email.utils.MailMessage;

@Component
public class MailConsumer {
     @Autowired
    private MailService mailService;

    @RabbitListener(queues = RabbitMQConfig.EMAIL_QUEUE)
    public void consumeEmail(MailMessage emailMessage) {
        try {

            mailService.sendMail(emailMessage.getTo(), emailMessage.getSubject(), emailMessage.getContent());
        } catch (Exception e) {
            throw new RuntimeException("Failed to process email queue message", e);
        }
    }
}

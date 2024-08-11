package com.example.LibraryManagmentApi.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.example.LibraryManagmentApi.security.repository.RoleRepository;
import com.example.LibraryManagmentApi.security.repository.UserRepository;
import com.example.LibraryManagmentApi.security.restapi.token.TokenRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import jakarta.transaction.Transactional;

import com.example.LibraryManagmentApi.security.model.Role;
import com.example.LibraryManagmentApi.security.model.User;
import com.example.LibraryManagmentApi.security.restapi.token.Token;


import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    
    @Autowired
    @Qualifier("jwtPasswordEncoder")
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    // @Autowired
    // private JavaMailSender javaMailSender;
 

    @Autowired
    private TokenRepository tokenRepository;

     //Get All Users
     public List<User> findAll() {
        return userRepository.findAll();
    }

      //Get User By Id
    public User findById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    //Delete User
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public void deleteUser(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Remove user from roles
        for (Role role : user.getRoles()) {
            user.removeRole(role);
        }

        for (Token token : user.getTokens()) {
            // Perform any additional cleanup if needed
            tokenRepository.delete(token);
        }

        // Clear roles from the user
        user.getRoles().clear();

        // Delete the user
        userRepository.delete(user);
    }

    //save new user
    public void save(User user)
    {
        userRepository.save(user);
    }

    public User findByUsername(String username)
    {
        return userRepository.findByUsername(username);
        
    }

    public void createAdminUser() {
        Optional<User> optionalAdmin = userRepository.findByEmail("admin@toshokan.com");
    
        if (optionalAdmin.isPresent()) {
            return; // admin user already exists
        }
    
        Role adminRole = new Role(null, "ADMIN", "ADMIN");
        Role librarianRole = new Role(null, "STAFF", "STAFF");
    
        String encodedPassword = encoder.encode("admin");
    
        User adminUser = User.builder()
                .username("admin")
                .password(encodedPassword) // password is "password"
                .email("admin@toshokan.com")
                .firstname("Admin")
                .lastname("Admin")
                .roles(Set.of(adminRole))
                .build();
    
        userRepository.save(adminUser);

        Set<Role> rolesToCreate = Set.of(librarianRole);
        for (Role role : rolesToCreate) {
            Optional<Role> optionalRole = roleRepository.findByDescription(role.getDescription());
            if (!optionalRole.isPresent()) {
                roleRepository.save(role);
            }
        }
    }
    

    public List<User> getLoggedInAgents() {
        return userRepository.findByRolesDescriptionOrDetailsAndLoggedIn("LIBRARIAN", true);
    }

    //search for users
    public List<User> searchUsers(String query) {
        return userRepository.searchUsers(query);
    }
    
    ///////////////////RESET PASSWORD//////////////////////
    // public void generateResetPasswordToken(User user) {
    //     user.generateResetPasswordToken();
    //     userRepository.save(user);
    //     sendResetPasswordEmail(user);
    // }

    // public void resetPassword(User user, String token, String newPassword) {
    //     String encodedPassword = encoder.encode(newPassword);
    //     user.resetPassword(token, encodedPassword);
    //     userRepository.save(user);
    // }


    // private void sendResetPasswordEmail(User user) {
    //     String recipientAddress = user.getEmail();
    //     String subject = "Reset Password";
    //     String body = "Dear " + user.getUsername() + ",\n\n"
    //             + "Please use the following token to reset your password: " + user.getResetPasswordToken();
        
    //     SimpleMailMessage mailMessage = new SimpleMailMessage();
    //     mailMessage.setTo(recipientAddress);
    //     mailMessage.setSubject(subject);
    //     mailMessage.setText(body);
        
    //     javaMailSender.send(mailMessage);
    // }

}

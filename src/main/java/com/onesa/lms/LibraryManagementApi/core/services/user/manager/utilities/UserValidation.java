package com.onesa.lms.LibraryManagementApi.core.services.user.manager.utilities;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onesa.lms.LibraryManagementApi.core.services.user.models.User;
import com.onesa.lms.LibraryManagementApi.core.services.user.repository.UserRepository;

@Service
public class UserValidation {

    @Autowired
    private UserRepository userRepository;
    
    public void validateUserInfo(String email, String phoneNumber, String userName) {
        if (email != null && existsByEmail(email)) {
            throw new IllegalArgumentException("Email already exists");
        }

        if (phoneNumber != null && existsByPhoneNumber(phoneNumber)) {
            throw new IllegalArgumentException("Phone number already exists");
        }

        if (userName != null && existsByUserName(userName)) {
            throw new IllegalArgumentException("Username already exists");
        }
    }


    // Email and Password Validation

    public void validateUserCredentials(String email, String password) {
        validateEmail(email);
        validatePassword(password);
    }


    public Boolean existsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public Boolean existsByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber).isPresent();
    }

    public Boolean existsByUserName(String userName) {
        User existingUser = userRepository.findByUsername(userName);
        return existingUser != null;
    }



    //  Email and Password Validation

    private static final Pattern PASSWORD_PATTERN = Pattern
            .compile("^(?=.*[A-Z])(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    /**
     * Validates the password.
     *
     * @param password The password to validate.
     * @throws IllegalArgumentException if the password doesn't meet security
     *                                  requirements.
     */
    public void validatePassword(String password) {
        if (password == null || !PASSWORD_PATTERN.matcher(password).matches()) {
            throw new IllegalArgumentException(
                    "Password must be at least 8 characters long, contain an uppercase letter, and a symbol.");
        }
    }

    /**
     * Validates the email format.
     *
     * @param email The email to validate.
     * @throws IllegalArgumentException if the email format is invalid.
     */
    public void validateEmail(String email) {
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email format.");
        }
    }

  
}

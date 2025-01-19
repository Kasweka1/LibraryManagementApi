package com.onesa.lms.LibraryManagementApi.core.utils.email.service;

import com.onesa.lms.LibraryManagementApi.core.services.user.models.User;

public interface MailService {

    // Plain mail sending method
    void sendMail(String to, String subject, String content);

    // Mail sending method to User on successful account creation
    void updateUserOnSuccessfulAccountCreationAndActivation(User user);

    // Mail sending method to Librarian or Admin on account creation
    void alertLibrarianOrAdminOnAccountCreation(User user);
}

package com.onesa.lms.LibraryManagementApi.core.services.user.service;

import java.util.List;

import com.onesa.lms.LibraryManagementApi.core.services.user.models.User;

public interface UserService {

    //  Registers a new member by saving them as role member
    public User  registerMember(User user);

    public String verify (User user);

    // Created a new user of 'librarian' or 'admin' by saving them as role librarian
    User createUser(User user);

 
    // User getUserByEmail(String email);

   
    User getUserById(long id);

    // User getUserByPhoneNumber(String phoneNumber);

    List<User> getAllUsers();


    // Boolean existsByEmail(String email);

    // boolean isEmailOrPhoneNumberTaken(String email, String phoneNumber);

}

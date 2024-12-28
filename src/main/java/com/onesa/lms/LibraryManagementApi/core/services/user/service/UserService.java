package com.onesa.lms.LibraryManagementApi.core.services.user.service;

import java.util.List;

import com.onesa.lms.LibraryManagementApi.core.services.user.models.User;

public interface UserService {

    User saveUser(User user);

 
    User getUserByEmail(String email);

   
    User getUserById(long id);

    User getUserByPhoneNumber(String phoneNumber);

    List<User> getAllUsers();

    Boolean existsByEmail(String email);

    Boolean existsByPhoneNumber(String phoneNumber);

}

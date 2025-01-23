package com.onesa.lms.LibraryManagementApi.core.services.user.service;

import java.util.List;

import com.onesa.lms.LibraryManagementApi.core.services.user.constants.UserStatus;
import com.onesa.lms.LibraryManagementApi.core.services.user.models.User;

public interface UserService {

    //  Registers a new member by saving them as role member
    public User  registerMember(User user);


    //  Verifies user when login in 
    public String verify (User user);

    // Created a new user of 'librarian' or 'admin' by saving them as role librarian
    User createUser(User user);

    //  Updates get by id
    User getUserById(long id);

    List<User> getAllUsers();

    public User updateUser(User user, Long id);

    public User changeUserStatus(Long userId, UserStatus newStatus);

    public User getLoggedInUser();

    public User getUserByLibraryId(String libraryIdNumber);


}

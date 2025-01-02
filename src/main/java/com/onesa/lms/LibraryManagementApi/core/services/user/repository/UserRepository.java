package com.onesa.lms.LibraryManagementApi.core.services.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onesa.lms.LibraryManagementApi.core.services.user.constants.RoleType;
import com.onesa.lms.LibraryManagementApi.core.services.user.models.User;

public interface UserRepository extends JpaRepository<User, Long>{

    User findByUsername(String Username);

    User findUserByEmailIgnoreCase(String email);


    User findUserByPhoneNumber(String phoneNumber);


    Boolean existsUserByEmail(String email);


    Boolean existsUserByPhoneNumber(String phoneNumber);

  
    List<User> findUsersByRoleIn(List<RoleType> roles);

    // Query to find a user by email
    Optional<User> findByEmail(String email);
    
    // Query to find a user by phone number
    Optional<User> findByPhoneNumber(String phoneNumber);
}



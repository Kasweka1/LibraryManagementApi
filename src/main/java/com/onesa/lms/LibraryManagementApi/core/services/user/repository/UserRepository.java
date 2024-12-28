package com.onesa.lms.LibraryManagementApi.core.services.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onesa.lms.LibraryManagementApi.core.services.role.constants.RoleType;
import com.onesa.lms.LibraryManagementApi.core.services.user.models.User;

public interface UserRepository extends JpaRepository<User, Long>{

    User findUserByEmailIgnoreCase(String email);


    User findUserByPhoneNumber(String phoneNumber);


    Boolean existsUserByEmail(String email);


    Boolean existsUserByPhoneNumber(String phoneNumber);

  
    List<User> findUsersByRoleIn(List<RoleType> roles);
}

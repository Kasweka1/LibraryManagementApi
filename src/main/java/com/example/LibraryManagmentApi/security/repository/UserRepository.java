package com.example.LibraryManagmentApi.security.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.LibraryManagmentApi.security.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    
    Optional<User> findByEmail(String email);
}

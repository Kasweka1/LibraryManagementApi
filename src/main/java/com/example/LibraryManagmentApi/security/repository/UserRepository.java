package com.example.LibraryManagmentApi.security.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.LibraryManagmentApi.security.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    
    User findByUsername(String username);

    Optional<User> findByEmail(String email);

    //find by first name and last name 
    User findByFirstnameAndLastname(String firstname, String lastname);

    @Query("SELECT u FROM User u INNER JOIN u.roles r WHERE lower(r.description) = lower(:roleDescription) OR lower(r.details) = lower(:roleDescription) AND u.loggedIn = :loggedIn")
    List<User> findByRolesDescriptionOrDetailsAndLoggedIn(@Param("roleDescription") String roleDescription, @Param("loggedIn") boolean loggedIn);

    //search for users
    @Query("SELECT u FROM User u WHERE u.firstname LIKE %:query% OR u.lastname LIKE %:query% OR u.email LIKE %:query%")
    List<User> searchUsers(@Param("query") String query);

}

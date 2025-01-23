package com.onesa.lms.LibraryManagementApi.core.services.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.onesa.lms.LibraryManagementApi.core.services.user.constants.UserStatus;
import com.onesa.lms.LibraryManagementApi.core.services.user.models.User;
import com.onesa.lms.LibraryManagementApi.core.services.user.service.UserService;
import com.onesa.lms.LibraryManagementApi.core.utils.dtos.ApiResponse;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>> registerMember(@RequestBody User user) {

        try {
            User createdUser = userService.registerMember(user);
            ApiResponse<User> response = new ApiResponse<>(1200, "User registered Succesfully", createdUser);
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            ApiResponse<User> response = new ApiResponse<>(1203, e.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }

        catch (Exception e) {
            System.out.println(e);
            ApiResponse<User> response = new ApiResponse<>(1202, "Failed to register User", null);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @PostMapping("/create-user")
    public ResponseEntity<ApiResponse<User>> createUser(@RequestBody User user) {

        try {

            User createdUser = userService.createUser(user);
            ApiResponse<User> response = new ApiResponse<>(1200, "User created Succesfully", createdUser);
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            ApiResponse<User> response = new ApiResponse<>(1203, e.getMessage(), null);
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }

        catch (Exception e) {
            System.out.println(e);
            ApiResponse<User> response = new ApiResponse<>(1202, "Failed to create User", null);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Map<String, String>>> login(@RequestBody User user) {
        try {
            String token = userService.verify(user);
            if (token != null) {
                Map<String, String> responseData = new HashMap<>();
                responseData.put("token", token);
                ApiResponse<Map<String, String>> response = new ApiResponse<>(1200, "Login successful", responseData);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                ApiResponse<Map<String, String>> response = new ApiResponse<>(1201, "Invalid username or password",
                        null);
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            System.out.println(e);
            ApiResponse<Map<String, String>> response = new ApiResponse<>(1202, "Failed to login:" + e.getMessage(),null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
        try {
            ApiResponse<List<User>> response = new ApiResponse<>(1200, "Users retrieved successfully",
                    userService.getAllUsers());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            ApiResponse<List<User>> response = new ApiResponse<>(1202, "Failed to retrieve users", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable long id) {
        try {
            User user = userService.getUserById(id);
            if (user == null) {
                ApiResponse<User> response = new ApiResponse<>(1204, "User not found", null);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            ApiResponse<User> response = new ApiResponse<>(1200, "User found", user);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            ApiResponse<User> response = new ApiResponse<>(1202, "Failed to retrieve user", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<ApiResponse<User>> updateUser(@RequestBody User updatedUser, @PathVariable long id) {
        try {
            User user = userService.updateUser(updatedUser, id);
            if (updatedUser == null) {
                ApiResponse<User> response = new ApiResponse<>(1204, "User not found", null);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            ApiResponse<User> response = new ApiResponse<>(1200, "User updated successfully", user);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            String message = "Failed to update user, " + e.getMessage();
            ApiResponse<User> response = new ApiResponse<>(1202, message, null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/user/{id}/status")
    public ResponseEntity<ApiResponse<User>> changeUserStatus(@PathVariable long id,
            @RequestBody Map<String, String> body) {
        try {
            
            String newStatusString = body.get("userStatus");

            if (newStatusString == null) {
                throw new IllegalArgumentException("userStatus field is required");
            }

            UserStatus newStatus = UserStatus.valueOf(newStatusString.toUpperCase());

            User user = userService.changeUserStatus(id, newStatus);
            ApiResponse<User> response = new ApiResponse<>(1200, "User status updated successfully", user);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            String message = "Failed to update user status, " + e.getMessage();
            ApiResponse<User> response = new ApiResponse<>(1202, message, null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

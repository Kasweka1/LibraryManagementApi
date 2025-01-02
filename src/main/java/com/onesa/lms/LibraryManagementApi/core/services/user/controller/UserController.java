package com.onesa.lms.LibraryManagementApi.core.services.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.onesa.lms.LibraryManagementApi.core.dtos.ApiResponse;
import com.onesa.lms.LibraryManagementApi.core.services.user.models.User;
import com.onesa.lms.LibraryManagementApi.core.services.user.service.UserService;


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
                ApiResponse<Map<String, String>> response = new ApiResponse<>(1201, "Invalid username or password", null);
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            System.out.println(e);
            ApiResponse<Map<String, String>> response = new ApiResponse<>(1202, "Failed to login", null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   
}

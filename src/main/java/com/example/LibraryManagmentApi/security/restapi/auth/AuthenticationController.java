package com.example.LibraryManagmentApi.security.restapi.auth;

import java.io.IOException;
import java.util.Collections;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import lombok.RequiredArgsConstructor;
import com.example.LibraryManagmentApi.security.model.User;
import com.example.LibraryManagmentApi.security.service.UserService;
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;

    @Autowired
    private UserService userService;

    // @PostMapping("/register")
    // public ResponseEntity<AuthenticationResponse> register(
    //     @RequestBody RegisterRequest request
    // ){
    //     //
    // }
    
    // @PostMapping("/authenticate")
    // public ResponseEntity<AuthenticationResponse> register(
    //     @RequestBody AuthenticationRequest request
    // ){
    //     //
    // }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {
        try {
            AuthenticationResponse response = service.authenticate(request);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            // Handle authentication error
            String errorMessage = "Invalid email or password";
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("error", errorMessage));
        }
    }

    @PostMapping("/reset-password/{username}")
    public ResponseEntity<?> resetPassword(@PathVariable String username, @RequestParam String token, @RequestParam String newPassword) {
        User user = userService.findByUsername(username);
        if (user != null) {
            userService.resetPassword(user, token, newPassword);
            String message = "Password reset successful.";
            return ResponseEntity.ok()
                    .body(Collections.singletonMap("message", message));
        }
        else {
            String errorMessage = "User not found.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error", errorMessage));
        }
    }
    
    @PostMapping("/forgot-password/{username}")
    public ResponseEntity<?> forgotPassword(@PathVariable String username) {
        User user = userService.findByUsername(username);
        if (user != null) {
            userService.generateResetPasswordToken(user);
            String message = "Reset password token sent to your email.";
            return ResponseEntity.ok()
            .body(Collections.singletonMap("message", message));
        } else {
            String errorMessage = "User not found.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error", errorMessage));
        }
    }


    @PostMapping("/refresh-token")
    public void refreshToken(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }
}

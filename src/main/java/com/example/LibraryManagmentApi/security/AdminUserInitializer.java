package com.example.LibraryManagmentApi.security;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.LibraryManagmentApi.security.service.UserService;

@Component
public class AdminUserInitializer implements ApplicationRunner {
    private final UserService userService;

    public AdminUserInitializer(UserService userService) {
        this.userService = userService;
    }

  

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // TODO Auto-generated method stub
        userService.createAdminUser();
        // userService.createRoles();
    }
}

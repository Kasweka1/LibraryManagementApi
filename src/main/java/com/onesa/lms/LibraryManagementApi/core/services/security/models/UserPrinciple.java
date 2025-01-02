package com.onesa.lms.LibraryManagementApi.core.services.security.models;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.onesa.lms.LibraryManagementApi.core.services.user.constants.UserStatus;
import com.onesa.lms.LibraryManagementApi.core.services.user.models.User;

public class UserPrinciple implements UserDetails{

    @Autowired
    private User user;


    public UserPrinciple(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String userRole  = "ROLE_" + user.getRole().name();
        return Collections.singleton(new SimpleGrantedAuthority(userRole));
    }

    @Override
    public String getPassword() {
        
        return user.getPassword();
    }

    @Override
    public String getUsername() {
       
        return user.getUsername();
    }
    
    // TODO: add logic for active or inactive authorties
    @Override
    public boolean isEnabled() {
        return user.getUserStatus() == UserStatus.ACTIVE; 
    }
}

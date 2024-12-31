package com.onesa.lms.LibraryManagementApi.core.services.security.models;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.onesa.lms.LibraryManagementApi.core.services.user.models.User;

public class UserPrinciple implements UserDetails{

    @Autowired
    private User user;


    public UserPrinciple(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        
        return user.getPassword();
    }

    @Override
    public String getUsername() {
       
        return user.getUsername();
    }
    
}

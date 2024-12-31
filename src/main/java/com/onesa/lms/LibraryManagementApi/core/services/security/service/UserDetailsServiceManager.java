package com.onesa.lms.LibraryManagementApi.core.services.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.onesa.lms.LibraryManagementApi.core.services.security.models.UserPrinciple;
import com.onesa.lms.LibraryManagementApi.core.services.user.models.User;
import com.onesa.lms.LibraryManagementApi.core.services.user.repository.UserRepository;


@Service
public class UserDetailsServiceManager implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = userRepository.findByUsername(username);
       if(user == null){
           throw new UsernameNotFoundException("User not found");
        }
        System.out.println("User not found");
           
       return new UserPrinciple(user);
    }
    
}

package com.onesa.lms.LibraryManagementApi.core.services.user.manager;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.onesa.lms.LibraryManagementApi.core.services.security.service.JWTService;
import com.onesa.lms.LibraryManagementApi.core.services.user.constants.RoleType;
import com.onesa.lms.LibraryManagementApi.core.services.user.constants.UserStatus;
import com.onesa.lms.LibraryManagementApi.core.services.user.models.User;
import com.onesa.lms.LibraryManagementApi.core.services.user.repository.UserRepository;
import com.onesa.lms.LibraryManagementApi.core.services.user.service.UserService;

import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;


@Service
public class UserManager implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    
    @Autowired
    private JWTService jwtService;


    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    @Override
    public User registerMember(User user) {

        // if (isEmailOrPhoneNumberTaken(user.getEmail(), user.getPhoneNumber())) {
        //     throw new IllegalArgumentException("Email or phone number already exists");
        // }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(RoleType.MEMBER);
        user.setUserStatus(UserStatus.INACTIVE);
        return userRepository.save(user);
    }

    @Override
    public User createUser(User user) {

        // if (isEmailOrPhoneNumberTaken(user.getEmail(), user.getPhoneNumber())) {
        //     throw new IllegalArgumentException("Email or phone number already exists");
        // }


        if (user.getRole() != RoleType.ADMIN && user.getRole() != RoleType.LIBRARIAN) {
            throw new IllegalArgumentException("Only ADMIN or LIBRARIAN roles are allowed for this operation");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public String verify(User user) {
         Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        if(authentication.isAuthenticated())
            return jwtService.generateToken(user.getUsername());
      
        return "User not logged in";    
    }

    // Check if user exists by email or phone number
    // @Override
    // public boolean isEmailOrPhoneNumberTaken(String email, String phoneNumber) {
    //     return userRepository.existsUserByEmail(email) || userRepository.existsUserByPhoneNumber(phoneNumber);
    // }


    //  // Check if email already exists
    //  public boolean existsByEmail(String email) {
    //     Optional<User> existingUser = userRepository.findByEmail(email);
    //     return existingUser.isPresent();
    // }

    // Check if phone number already exists
    public boolean existsByPhoneNumber(String phoneNumber) {
        Optional<User> existingUser = userRepository.findByPhoneNumber(phoneNumber);
        return existingUser.isPresent();
    }

       
    @Override
    public List<User> getAllUsers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllUsers'");
    }


    @Override
    public User getUserById(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserById'");
    }

    // @Override
    // public User getUserByEmail(String email) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'getUserByEmail'");
    // }


    // @Override
    // public User getUserByPhoneNumber(String phoneNumber) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'getUserByPhoneNumber'");
    // }

    
    // @Override
    // public Boolean existsByEmail(String email) {
        //     Optional<User> existingUser = userRepository.findByEmail(email);
        //     return existingUser.isPresent();
        // }
        
        
     

}

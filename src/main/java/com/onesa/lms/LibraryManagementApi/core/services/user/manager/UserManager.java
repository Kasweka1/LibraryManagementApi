package com.onesa.lms.LibraryManagementApi.core.services.user.manager;

import java.util.List;
import java.util.Optional;

import org.apache.tomcat.jni.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.onesa.lms.LibraryManagementApi.core.services.security.service.JWTService;
import com.onesa.lms.LibraryManagementApi.core.services.user.constants.RoleType;
import com.onesa.lms.LibraryManagementApi.core.services.user.constants.UserStatus;
import com.onesa.lms.LibraryManagementApi.core.services.user.manager.utilities.LibraryIdGenerator;
import com.onesa.lms.LibraryManagementApi.core.services.user.manager.utilities.UserValidation;
import com.onesa.lms.LibraryManagementApi.core.services.user.models.User;
import com.onesa.lms.LibraryManagementApi.core.services.user.repository.UserRepository;
import com.onesa.lms.LibraryManagementApi.core.services.user.service.UserService;
import com.onesa.lms.LibraryManagementApi.core.utils.email.service.MailService;

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

    @Autowired
    private UserValidation userValidation;

    @Autowired
    private MailService mailService;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    @Override
    public User registerMember(User user) {

        User registeredMember =  saveUserWithLibraryId(user, RoleType.MEMBER);
        mailService.updateUserOnSuccessfulAccountCreationAndActivation(registeredMember);   
        return registeredMember;
    }

    @Override
    public User createUser(User user) {

        if (user.getRole() != RoleType.ADMIN && user.getRole() != RoleType.LIBRARIAN) {
            throw new IllegalArgumentException("Only ADMIN or LIBRARIAN roles are allowed for this operation");
        }
        User createdUser = saveUserWithLibraryId(user, user.getRole());
        mailService.alertLibrarianOrAdminOnAccountCreation(createdUser);
        return createdUser;
    }

    // method to save user with library ID for Regitration and Create User method
    private User saveUserWithLibraryId(User user, RoleType roleType) {
        // Validate user information
        userValidation.validateUserInfo(user.getEmail(), user.getPhoneNumber(), user.getUsername());
        userValidation.validateUserCredentials(user.getEmail(), user.getPassword());

        // Set role and status
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(roleType);
        user.setUserStatus(UserStatus.INACTIVE);

        // Save user to generate ID
        User savedUser = userRepository.save(user);

        // Generate library ID
        String libraryId = LibraryIdGenerator.generateLibraryId(savedUser.getRole().name(), savedUser.getId());
        savedUser.setLibraryIdNumber(libraryId);

        // Save updated user with library ID
        return userRepository.save(savedUser);
    }

    @Override
    public String verify(User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if (authentication.isAuthenticated())
            return jwtService.generateToken(user.getUsername());

        return "User not logged in";
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public User updateUser(User user, Long id) {

        User existingUser = userRepository.findUserById(id);

        if (existingUser == null) {
            throw new IllegalArgumentException("User not found");
        }

        // Check if phone number exists for another user
        Optional<User> userWithPhoneNumber = userRepository.findByPhoneNumber(user.getPhoneNumber());
        if (userWithPhoneNumber.isPresent() && !userWithPhoneNumber.get().getId().equals(id)) {
            throw new IllegalArgumentException("Phone number already exists");
        }

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        existingUser.setResidentialAddress(user.getResidentialAddress());
        existingUser.setProfilePictureUrl(user.getProfilePictureUrl());

        return userRepository.save(existingUser);
    }

    @Override
    public User changeUserStatus(Long userId, UserStatus newStatus) {
        User user = userRepository.findUserById(userId);

        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        if (newStatus == null) {
            throw new IllegalArgumentException("Invalid user status");
        }
        user.setUserStatus(newStatus);

        return userRepository.save(user);
    }

}

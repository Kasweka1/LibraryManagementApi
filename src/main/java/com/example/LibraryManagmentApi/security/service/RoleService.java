package com.example.LibraryManagmentApi.security.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LibraryManagmentApi.security.model.Role;
import com.example.LibraryManagmentApi.security.model.User;
import com.example.LibraryManagmentApi.security.repository.RoleRepository;
import com.example.LibraryManagmentApi.security.repository.UserRepository;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    //Get All Roles
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    //Get Role By Id
    public Role findById(int id) {
        return roleRepository.findById(id).orElse(null);
    }

    // //Delete Role
    // public void delete(int id) {
    //     roleRepository.deleteById(id);
    // }

    // //Update Role
    // public void save(Role role) {
    //     roleRepository.save(role);
    // }

    //Assign Role to User
    public void assignUserRole(Integer userId, Integer roleId) {
        User user = userRepository.findById(userId).orElse(null);
        Role role = roleRepository.findById(roleId).orElse(null);
        Set<Role> userRoles = user.getRoles();
        userRoles.add(role);
        user.setRoles(userRoles);
        userRepository.save(user);
    }

    //Unassign Role to User
    public void unassignUserRole(Integer userId, Integer roleId) {
        User user = userRepository.findById(userId).orElse(null);
        user.getRoles().removeIf(x -> x.getId() == roleId);
        userRepository.save(user);
    }

    public Set<Role> getUserRoles(User user) {
        return user.getRoles();
    }

    public Set<Role> geUserRoles(User user) {
        return user.getRoles();
    }

    public List<Role> getUserNotRoles(User user) {
        return roleRepository.getUserNotRoles(user.getId());
    }

    public Optional<Role> findByDescription(String description)
    {
        return roleRepository.findByDescription(description);
        
    } 
}

package com.example.LibraryManagmentApi.security.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.LibraryManagmentApi.security.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    
    @Query(
            value = "SELECT * FROM role WHERE id NOT IN (SELECT role_id FROM user_role WHERE user_id = ?1)",
            nativeQuery = true
    )
    List<Role> getUserNotRoles(Integer userId);

    Optional<Role> findByDescription(String description);
}

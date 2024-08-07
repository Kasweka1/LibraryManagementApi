package com.example.LibraryManagmentApi.security.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;
import com.example.LibraryManagmentApi.security.restapi.token.Token;

import jakarta.persistence.CascadeType;
import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable{

    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
    private String phoneNumber;
    private String email;
    private String address;
    private String password;
    private boolean loggedIn;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Token> tokens;
    
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    @Builder.Default // add this annotation
    private Set<Role> roles = new HashSet<>();

    public void removeRole(Role role) {
        roles.remove(role);
    }

    public Object orElseThrow(Object object){
        return null;
    }

    //////////////////RESET PASSWORD///////////////
    private String resetPasswordToken;

    public void generateResetPasswordToken() {
        String token = UUID.randomUUID().toString();
        setResetPasswordToken(token);
    }
    
    public void resetPassword(String token, String newPassword) {
        if (resetPasswordToken != null && resetPasswordToken.equals(token)) {
            setPassword(newPassword);
            setResetPasswordToken(null);
        }
    }
    //////////////////REST PASSWORD///////////////  

    

}

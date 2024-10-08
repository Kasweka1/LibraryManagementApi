package com.example.LibraryManagmentApi.security.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrinciple implements UserDetails {
    
  private User user;

  public UserPrinciple(User user){
    this.user = user;
  }

  @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorities = new ArrayList<>();
    for (Role role : user.getRoles()) {
        authorities.add(new SimpleGrantedAuthority(role.getDescription()));
    }

    return authorities;
}


@Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return user.getEmail();
    }
    

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
}

package com.junioroffers.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.junioroffers.security.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


public class UserDetailsImp implements UserDetails {

    private String username;

    @JsonIgnore
    private String password;


    public UserDetailsImp(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserDetailsImp() {
    }

    public static UserDetailsImp build(User user) {

        return new UserDetailsImp(
                user.getUsername(),
                user.getPassword());
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

package com.gcantera.gl.earthquakeapi.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Value("${jwt.token.user}")
    private String user;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (user.equals(username)) {
            return new User(user, "$2a$12$12HcnW2UnQBGl55pnJME1.KxXjAhsoHkADAnrz/Sb0HQxXyrGGAya", new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}

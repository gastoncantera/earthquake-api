package com.gcantera.gl.earthquakeapi.controller;

import com.gcantera.gl.earthquakeapi.helper.JwtTokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtLoginController {

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        // TODO: Authenticate the user and get the authorities
        return jwtTokenHelper.buildToken(username, "ROLE_USER");
    }
}

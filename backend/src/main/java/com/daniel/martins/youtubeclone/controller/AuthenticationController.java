package com.daniel.martins.youtubeclone.controller;

import com.daniel.martins.youtubeclone.dto.ResponseDto;
import com.daniel.martins.youtubeclone.dto.UserDto;
import com.daniel.martins.youtubeclone.session.InMemorySessionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    public AuthenticationManager manager;
    @Autowired
    public InMemorySessionRegistry sessionRegistry;


    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody UserDto user) {
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        final String sessionId  = sessionRegistry.registerSession(user.getUsername());
        ResponseDto response = new ResponseDto();
        response.setSessionId(sessionId);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/login")
    public String test() {
        return "teste";
    }
}

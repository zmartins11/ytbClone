package com.daniel.martins.youtubeclone.controller;

import com.daniel.martins.youtubeclone.dto.ResponseDto;
import com.daniel.martins.youtubeclone.dto.UserDto;
import com.daniel.martins.youtubeclone.model.User;
import com.daniel.martins.youtubeclone.service.UserService;
import com.daniel.martins.youtubeclone.session.SessionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    public AuthenticationManager manager;
    @Autowired
    public SessionRegistry sessionRegistry;

    @Autowired
    UserService userService;


    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody UserDto userDto) {
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));

        final String sessionId  = sessionRegistry.registerSession(userDto.getUsername());
        User user = new User();
        ResponseDto response = new ResponseDto();
        if(sessionId != null) {
            user = userService.getCurrentUser(userDto.getUsername());

            response.setSessionId(sessionId);
            response.setUserId(user.getId());
            response.setUsername(user.getUsername());
        }

        return ResponseEntity.ok(response);
    }

}

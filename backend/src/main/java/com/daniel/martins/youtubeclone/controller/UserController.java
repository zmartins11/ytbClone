package com.daniel.martins.youtubeclone.controller;

import com.daniel.martins.youtubeclone.dto.CommentDto;
import com.daniel.martins.youtubeclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("subscribe/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public boolean subscribeUser(@PathVariable String userId, @RequestHeader String sessionId) {
        userService.subscribeUser(userId, sessionId);
        return true;
    }

    @PostMapping("unSubscribe/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public boolean unSubscribe(@PathVariable String userId, @RequestHeader String sessionId) {
        userService.unsubscribeUser(userId, sessionId);
        return true;
    }

    @GetMapping("/{userId}/history")
    @ResponseStatus(HttpStatus.OK)
    public Set<String> userHistory(@PathVariable String userId, @RequestHeader String sessionId) {
        return userService.userHistory(userId, sessionId);
    }



}

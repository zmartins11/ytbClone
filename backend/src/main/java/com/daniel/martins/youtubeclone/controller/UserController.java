package com.daniel.martins.youtubeclone.controller;

import com.daniel.martins.youtubeclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("subscribe/{userId}")
    public boolean subscribeUser(@PathVariable String userId, @RequestHeader String sessionId) {
        userService.subscribeUser(userId, sessionId);
        return true;
    }

    @PostMapping("unSubscribe/{userId}")
    public boolean unSubscribe(@PathVariable String userId, @RequestHeader String sessionId) {
        userService.unsubscribeUser(userId, sessionId);
        return true;
    }

}

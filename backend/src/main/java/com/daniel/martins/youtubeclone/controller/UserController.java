package com.daniel.martins.youtubeclone.controller;

import com.daniel.martins.youtubeclone.dto.VideoDto;
import com.daniel.martins.youtubeclone.service.UserService;
import com.daniel.martins.youtubeclone.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final VideoService videoService;

    @Autowired
    public UserController(UserService userService, VideoService videoService) {
        this.userService = userService;
        this.videoService = videoService;
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
    public List<VideoDto> userHistory(@PathVariable String userId, @RequestHeader String sessionId) {
        Set<String> listVideoIds = userService.userHistory(userId, sessionId);
        List<VideoDto> listVideos = new ArrayList<>();
        for(String videoId : listVideoIds) {
            listVideos.add(videoService.mapToVideoDto(videoService.getVideoById(videoId)));
        }

        return listVideos;

    }

}

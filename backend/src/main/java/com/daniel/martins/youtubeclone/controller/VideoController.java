package com.daniel.martins.youtubeclone.controller;

import com.daniel.martins.youtubeclone.dto.CommentDto;
import com.daniel.martins.youtubeclone.dto.UploadVideoResponse;
import com.daniel.martins.youtubeclone.dto.VideoDto;
import com.daniel.martins.youtubeclone.model.Comment;
import com.daniel.martins.youtubeclone.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true", allowedHeaders = "true")
public class VideoController {

    private final VideoService videoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UploadVideoResponse uploadVideo(@RequestParam("file")MultipartFile file) {
        return videoService.uploadVideo(file);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public VideoDto editVideoMetadata(@RequestBody VideoDto videoDto) {
        return videoService.editVideo(videoDto);
    }

    @PostMapping("/thumbnail")
    @ResponseStatus(HttpStatus.CREATED)
    public String uploadThumbnail(@RequestParam("file")MultipartFile file, @RequestParam("videoId") String videoId) {
        return videoService.uploadThumbnail(file, videoId);
    }

    @GetMapping("/{videoId}")
    @ResponseStatus(HttpStatus.OK)
    public VideoDto getVideoDetails(@PathVariable String videoId, @RequestHeader String sessionId) {
        return videoService.getVideoDetails(videoId, sessionId);
    }

    @PostMapping("/{videoId}/like")
    @ResponseStatus(HttpStatus.OK)
    public VideoDto likeVideo(@PathVariable String videoId, @RequestHeader String sessionId ) {
        return videoService.likeVideo(videoId, sessionId);
    }

    @PostMapping("/{videoId}/dislike")
    @ResponseStatus(HttpStatus.OK)
    public VideoDto dislikeVideo(@PathVariable String videoId, @RequestHeader String sessionId ) {
        return videoService.dislikeVideo(videoId, sessionId);
    }

    @PostMapping("/{videoId}/comment")
    @ResponseStatus(HttpStatus.OK)
    public void addComment(@PathVariable String videoId, @RequestBody CommentDto commentDto) {
        videoService.addComment(videoId, commentDto);
    }

    @GetMapping("/{videoId}/comment")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> getAllComments(@PathVariable String videoId) {
        return videoService.getAllComments(videoId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<VideoDto> getAllVideos() {
        return videoService.getAllVideos();
    }

    @PostMapping("/deleteComment")
    @ResponseStatus(HttpStatus.OK)
    public boolean deleteComment(@RequestBody CommentDto comment) {
        videoService.deleteComment(comment);
        return true;
    }
}

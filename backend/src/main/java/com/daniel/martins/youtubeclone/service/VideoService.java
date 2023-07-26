package com.daniel.martins.youtubeclone.service;

import com.daniel.martins.youtubeclone.dto.CommentDto;
import com.daniel.martins.youtubeclone.dto.UploadVideoResponse;
import com.daniel.martins.youtubeclone.dto.VideoDto;
import com.daniel.martins.youtubeclone.model.Comment;
import com.daniel.martins.youtubeclone.model.Video;
import com.daniel.martins.youtubeclone.repository.VideoRepository;
import com.daniel.martins.youtubeclone.session.SessionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class VideoService {

    private final S3Service s3Service;
    private final VideoRepository videoRepository;
    private final UserService userService;
    private final SessionRegistry sessionRegistry;

    @Autowired
    public VideoService(S3Service s3Service, VideoRepository videoRepository, UserService userService, SessionRegistry sessionRegistry) {
        this.s3Service = s3Service;
        this.videoRepository = videoRepository;
        this.userService = userService;
        this.sessionRegistry = sessionRegistry;
    }

    public UploadVideoResponse uploadVideo(MultipartFile multipartFile) {
        // uppload to aws s3
        //save to database

        String videoUrl = s3Service.uploadFile(multipartFile);

        var video = new Video();
        video.setVideoUrl(videoUrl);

        Video savedVideo = videoRepository.save(video);

        return new UploadVideoResponse(savedVideo.getId(), savedVideo.getVideoUrl());

    }

    public VideoDto editVideo(VideoDto videoDto) {
        //find the video by videoId
        Video savedVideo = getVideoById(videoDto.getId());


        //map the videoDto to video
        savedVideo.setTitle(videoDto.getTitle());
        savedVideo.setDescription(videoDto.getDescription());
        savedVideo.setTags(videoDto.getTags());
        if(savedVideo.getThumbnailUrl() == null) {
            savedVideo.setThumbnailUrl(videoDto.getThumbnailUrl());
        }
        savedVideo.setVideoStatus(videoDto.getVideoStatus());
        savedVideo.setUserId(videoDto.getUserId());

        //save video
        videoRepository.save(savedVideo);
        return videoDto;

    }

    public String uploadThumbnail(MultipartFile file, String videoId) {
        Video savedVideo = getVideoById(videoId);

        String thumbnailUrl = s3Service.uploadFile(file);

        savedVideo.setThumbnailUrl(thumbnailUrl);

        videoRepository.save(savedVideo);

        return thumbnailUrl;
    }

    public Video getVideoById(String videoId) {
        //find the video by videoId
        return  videoRepository.findById(videoId)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find video by Id -" + videoId));
    }

    public VideoDto getVideoDetails(String videoId, String sessionId) {
        Video savedVideo = getVideoById(videoId);
        String currentUser = sessionRegistry.getUsernameForSession(sessionId);

        increaseVideoCount(savedVideo);
        userService.addVideoToHistory(savedVideo.getId(), currentUser);

        return mapToVideoDto(savedVideo);
    }

    private void increaseVideoCount(Video savedVideo) {
        savedVideo.incrementVideoCount();
        videoRepository.save(savedVideo);
    }

    public VideoDto likeVideo(String videoId, String sessionId) {
        //get video by id
        Video videoById = getVideoById(videoId);

        //get user from sessionId
        String currentUser = sessionRegistry.getUsernameForSession(sessionId);

        if(userService.ifLikedVideo(videoId, currentUser)) {
            videoById.decrementLikes();
            userService.removeFromLikedVideos(videoId, currentUser);
        } else if(userService.ifDislikeVideo(videoId, currentUser)) {
            videoById.decrementDisLikes();
            userService.removeFromDislikesVideos(videoId, currentUser);
            videoById.incrementLikes();
            userService.addToLikeVideos(videoId, currentUser);
        } else {
            videoById.incrementLikes();
            userService.addToLikeVideos(videoId, currentUser);
        }

        videoRepository.save(videoById);

        return mapToVideoDto(videoById);

    }



    public VideoDto dislikeVideo(String videoId, String sessionId) {

        //get video by id
        Video videoById = getVideoById(videoId);

        String currentUser = sessionRegistry.getUsernameForSession(sessionId);

        if(userService.ifDislikeVideo(videoId, currentUser)) {
            videoById.decrementDisLikes();
            userService.removeFromDislikesVideos(videoId, currentUser);
        } else if(userService.ifLikedVideo(videoId, currentUser)) {
            videoById.decrementLikes();
            userService.removeFromLikedVideos(videoId, currentUser);
            videoById.incrementDisLikes();
            userService.addtoDislikeVideos(videoId, currentUser);
        } else {
            videoById.incrementDisLikes();
            userService.addtoDislikeVideos(videoId, currentUser);
        }

        videoRepository.save(videoById);

        return mapToVideoDto(videoById);
    }

    public static VideoDto mapToVideoDto(Video videoById) {
        VideoDto videoDto = new VideoDto();
        videoDto.setVideoUrl(videoById.getVideoUrl());
        videoDto.setThumbnailUrl(videoById.getThumbnailUrl());
        videoDto.setId(videoById.getId());
        videoDto.setTitle(videoById.getTitle());
        videoDto.setDescription(videoById.getDescription());
        videoDto.setTags(videoById.getTags());
        videoDto.setVideoStatus(videoById.getVideoStatus());
        videoDto.setLikeCount(videoById.getLikes().get());
        videoDto.setDislikeCount(videoById.getDislikes().get());
        videoDto.setViewCount(videoById.getViewCount().get());
        videoDto.setUserId(videoById.getUserId());
        return videoDto;
    }

    public void addComment(String videoId, CommentDto commentDto) {
        Video video = getVideoById(videoId);
        Comment comment = new Comment();
        comment.setText(commentDto.getCommentText());
        comment.setAuthorId(commentDto.getAuthorId());

        video.addComment(comment);
        videoRepository.save(video);

    }

    public List<CommentDto> getAllComments(String videoId) {
        Video video = getVideoById(videoId);
        List<Comment> commentList = video.getCommentList();

        return commentList.stream().map(this::mapToCommentDto).toList();

    }

    private CommentDto mapToCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setCommentText(comment.getText());
        commentDto.setAuthorId(comment.getAuthorId());
        return commentDto;

    }

    public List<VideoDto> getAllVideos() {
        return videoRepository.findAll().stream().map(VideoService::mapToVideoDto).toList();
    }
}

package com.daniel.martins.youtubeclone.service;

import com.daniel.martins.youtubeclone.dto.UploadVideoResponse;
import com.daniel.martins.youtubeclone.dto.VideoDto;
import com.daniel.martins.youtubeclone.model.Video;
import com.daniel.martins.youtubeclone.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final S3Service s3Service;
    private final VideoRepository videoRepository;

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
        savedVideo.setThumbnailUrl(videoDto.getThumbnailUrl());
        savedVideo.setVideoStatus(videoDto.getVideoStatus());

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

    Video getVideoById(String videoId) {
        //find the video by videoId
        return  videoRepository.findById(videoId)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find video by Id -" + videoId));
    }
}

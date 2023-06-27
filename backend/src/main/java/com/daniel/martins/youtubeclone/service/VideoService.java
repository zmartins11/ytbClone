package com.daniel.martins.youtubeclone.service;

import com.amazonaws.services.s3.AmazonS3;
import com.daniel.martins.youtubeclone.model.Video;
import com.daniel.martins.youtubeclone.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final S3Service s3Service;
    private final VideoRepository videoRepository;

    public void uploadVideo(MultipartFile multipartFile) {
        // uppload to aws s3
        //save to database

        String videoUrl = s3Service.uploadFile(multipartFile);

        var video = new Video();
        video.setVideoUrl(videoUrl);

        videoRepository.save(video);

    }
}

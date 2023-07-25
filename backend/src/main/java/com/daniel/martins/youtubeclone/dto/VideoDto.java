package com.daniel.martins.youtubeclone.dto;

import com.daniel.martins.youtubeclone.model.Comment;
import com.daniel.martins.youtubeclone.model.VideoStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoDto {

    private String id;
    private String sessionId;
    private String title;
    private String description;
    private String userId;
    private Set<String> tags;
    private String videoUrl;
    private VideoStatus videoStatus;
    private String thumbnailUrl;
    private Integer likeCount;
    private Integer dislikeCount;
    private Integer viewCount;

}

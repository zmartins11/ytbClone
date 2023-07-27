package com.daniel.martins.youtubeclone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(value = "Comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    private String id;
    private String text;
    private String authorId;
    private Integer likeCount;
    private Integer dislikeCount;
    private String videoId;
    private LocalDateTime createdAt;
}

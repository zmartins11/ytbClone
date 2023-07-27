package com.daniel.martins.youtubeclone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private String id;
    private String commentText;
    private String authorId;
    private String videoId;
    private LocalDateTime createdAt;
}

package com.daniel.martins.youtubeclone.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Document(value = "Video")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Video {

    @Id
    private String id;
    private String title;
    private String description;
    private String userId;
    private AtomicInteger likes = new AtomicInteger(0);
    private AtomicInteger dislikes = new AtomicInteger(0);
    private Set<String> tags;
    private String videoUrl;
    private VideoStatus videoStatus;
    private AtomicInteger viewCount = new AtomicInteger(0);
    private String thumbnailUrl;
    private List<Comment> commentList = new CopyOnWriteArrayList<>();
    @JsonFormat(pattern = "MMM dd, yyyy")
    private LocalDateTime createdAt;

    public void incrementLikes() {
        likes.incrementAndGet();
    }

    public void decrementLikes() {
        likes.decrementAndGet();
    }

    public void incrementDisLikes() {
        dislikes.incrementAndGet();
    }

    public void decrementDisLikes() {
        dislikes.decrementAndGet();
    }

    public void incrementVideoCount() {
        viewCount.incrementAndGet();
    }

    public void addComment(Comment comment) {
        commentList.add(comment);
    }
}

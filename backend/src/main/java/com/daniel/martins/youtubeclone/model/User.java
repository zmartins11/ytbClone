package com.daniel.martins.youtubeclone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Document(value = "User")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String fullName;
    private String emailAddress;
    private Set<String> subscribedToUsers = ConcurrentHashMap.newKeySet();;
    private Set<String> subscribers = ConcurrentHashMap.newKeySet();;
    private Set<String> videoHistory = ConcurrentHashMap.newKeySet();
    private Set<String> likeVideos = ConcurrentHashMap.newKeySet();
    private Set<String> dislikeVideos = ConcurrentHashMap.newKeySet();

    public void addToLikeVideos(String videoId) {
        likeVideos.add(videoId);
    }

    public void removeFromLikedVideos(String videoId) {
        likeVideos.remove(videoId);
    }

    public void removeFromDislikeVideos(String videoId) {
        dislikeVideos.remove(videoId);
    }

    public void addToDislikeVideos(String videoId) {
        dislikeVideos.add(videoId);
    }

    public void addToVideoHistory(String videoId) {
        videoHistory.add(videoId);
    }

    public void addToSubscribedToUsers(String userId) {
        subscribedToUsers.add(userId);
    }

    public void addToSubscribers(String userId) {
        subscribers.add(userId);
    }

    public void removeFromSubscribedToUsers(String userId) {
        subscribedToUsers.remove(userId);
    }

    public void removeFromSubscribers(String userId) {
        subscribers.remove(userId);
    }
}

package com.daniel.martins.youtubeclone.service;

import com.daniel.martins.youtubeclone.model.User;
import com.daniel.martins.youtubeclone.repository.UserRepository;
import com.daniel.martins.youtubeclone.session.SessionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final SessionRegistry sessionRegistry;

    @Autowired
    public UserService(UserRepository userRepository, SessionRegistry sessionRegistry) {
        this.userRepository = userRepository;
        this.sessionRegistry = sessionRegistry;
    }

    public User getCurrentUser(String username) {
        return userRepository.findByUsername(username);
    }

    public void addToLikeVideos(String videoId, String username) {
        User currentUser = getCurrentUser(username);
        currentUser.addToLikeVideos(videoId);
        userRepository.save(currentUser);
    }

    public boolean ifLikedVideo(String videoId, String username) {
        return getCurrentUser(username).getLikeVideos().stream().anyMatch(likeVideo -> likeVideo.equals(videoId));
    }

    public boolean ifDislikeVideo(String videoId, String username) {
        return getCurrentUser(username).getDislikeVideos().stream().anyMatch(likeVideo -> likeVideo.equals(videoId));
    }

    public void removeFromLikedVideos(String videoId, String username) {
        User currentUser = getCurrentUser(username);
        currentUser.removeFromLikedVideos(videoId);
        userRepository.save(currentUser);
    }

    public void removeFromDislikesVideos(String videoId, String user) {
        User currentUser = getCurrentUser(user);
        currentUser.removeFromDislikeVideos(videoId);
        userRepository.save(currentUser);
    }

    public void addtoDislikeVideos(String videoId, String user) {
        User currentUser = getCurrentUser(user);
        currentUser.addToDislikeVideos(videoId);
        userRepository.save(currentUser);
    }

    public void addVideoToHistory(String videoId, String user) {
        User currentUser = getCurrentUser(user);
        currentUser.addToVideoHistory(videoId);
        userRepository.save(currentUser);
    }

    public void subscribeUser(String userId, String sessionId) {
        String currentUsername = sessionRegistry.getUsernameForSession(sessionId);
        User currentUser = getCurrentUser(currentUsername);
        currentUser.addToSubscribedToUsers(userId);

        //find the user that has been subscribed
        User userSubscribed = userRepository.findByUsername(userId);
        userSubscribed.addToSubscribers(currentUser.getUsername());

        //save the users
        userRepository.save(currentUser);
        userRepository.save(userSubscribed);
    }

    public void unsubscribeUser(String userId, String sessionId) {
        String currentUsername = sessionRegistry.getUsernameForSession(sessionId);
        User currentUser = getCurrentUser(currentUsername);
        currentUser.removeFromSubscribedToUsers(userId);

        //find the user that has been subscribed
        User userSubscribed = userRepository.findByUsername(userId);
        userSubscribed.removeFromSubscribers(currentUser.getUsername());

        //save the users
        userRepository.save(currentUser);
        userRepository.save(userSubscribed);
    }

    public Set<String> userHistory(String userId, String sessionId) {
        String currentUsername = sessionRegistry.getUsernameForSession(sessionId);
        User currentUser = getCurrentUser(currentUsername);
        return currentUser.getVideoHistory();
    }


}

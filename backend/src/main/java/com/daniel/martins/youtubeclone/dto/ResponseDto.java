package com.daniel.martins.youtubeclone.dto;

public class ResponseDto {

    private String sessionId;
    private String username;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

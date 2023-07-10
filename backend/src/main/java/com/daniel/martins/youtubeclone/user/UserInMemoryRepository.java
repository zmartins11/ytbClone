package com.daniel.martins.youtubeclone.user;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class UserInMemoryRepository {

    private static final HashMap<String, CurrentUser> REGISTERD_USERS = new HashMap<>(2);

    @PostConstruct
    public void setupUsers() {
        REGISTERD_USERS.put("user1", buildCurrentUser(
                "user1",
                "$2a$10$4EvCE3wPMBPYEV/FA8B.3e1mrlCGaVuq.cO0x0fmrt198H61q/dFG"));
        REGISTERD_USERS.put("user2", buildCurrentUser(
                "user2",
                "$2a$10$hvOa9FAisXftunkgb/QmkO5FLTQCI123rKTY.yuWAv9DjOW43/cSi"));
    }

    public CurrentUser findUserByUsername(final String username) {
        return REGISTERD_USERS.get(username);
    }

    public static CurrentUser buildCurrentUser (final String username, final String password) {
        final CurrentUser currentUser = new CurrentUser();
        currentUser.setUsername(username);
        currentUser.setPassword(password);

        return currentUser;
    }
}

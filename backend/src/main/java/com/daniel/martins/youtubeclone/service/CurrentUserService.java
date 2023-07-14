package com.daniel.martins.youtubeclone.service;

import com.daniel.martins.youtubeclone.model.User;
import com.daniel.martins.youtubeclone.repository.UserRepository;
import com.daniel.martins.youtubeclone.user.CurrentUser;
import com.daniel.martins.youtubeclone.user.UserInMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserService implements UserDetailsService {

    private final UserInMemoryRepository inMemoryRepository;
    private final UserRepository repository;

    @Autowired
    public CurrentUserService(UserInMemoryRepository inMemoryRepositoryrepository, UserRepository repository) {
        this.inMemoryRepository = inMemoryRepositoryrepository;
        this.repository = repository;
    }
    @Override
    public CurrentUser loadUserByUsername(String username) throws UsernameNotFoundException {

        final User user = repository.findByUsername(username);

        if(user != null) {
            final CurrentUser currentUser = new CurrentUser();
            currentUser.setUsername(user.getUsername());
            currentUser.setPassword(user.getPassword());

            return currentUser;
        }

        final CurrentUser currentUser = inMemoryRepository.findUserByUsername(username);
        if(currentUser == null) {
            throw new UsernameNotFoundException("Failed to find user with username" + username);
        }
        return currentUser;
    }
}

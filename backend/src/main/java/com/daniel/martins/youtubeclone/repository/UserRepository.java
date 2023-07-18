package com.daniel.martins.youtubeclone.repository;

import com.daniel.martins.youtubeclone.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username);

}

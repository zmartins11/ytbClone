package com.daniel.martins.youtubeclone.repository;

import com.daniel.martins.youtubeclone.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, String> {
}

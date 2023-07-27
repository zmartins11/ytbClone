package com.daniel.martins.youtubeclone.repository;

import com.daniel.martins.youtubeclone.model.Video;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends MongoRepository<Video, String> {


    @Query(value = "{'_id': ?0}", delete = true)
    void deleteCommentById(String videoId, String commentId);

}

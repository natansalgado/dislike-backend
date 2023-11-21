package com.dislike.repository;

import com.dislike.model.Post;
import com.dislike.projection.post.PostProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p.id as id, p.content as content, p.postDate as postDate, p.answerTo as answerTo, u as user FROM Post p LEFT JOIN p.user u")
    List<PostProjection> findAllPosts();

    @Query("SELECT p.id as id, p.content as content, p.postDate as postDate, p.answerTo as answerTo, u as user FROM Post p LEFT JOIN p.user u")
    PostProjection findPostById(Long id);
}

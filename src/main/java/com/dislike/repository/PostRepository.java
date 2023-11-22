package com.dislike.repository;

import com.dislike.model.Post;
import com.dislike.projection.post.PostProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p.id as id, p.content as content, p.postDate as postDate, p.answerTo as answerTo, u as user " +
            "FROM Post p LEFT JOIN p.user u LEFT JOIN p.answerTo a")
    List<PostProjection> findAllPosts();

    @Query("SELECT p.id as id, p.content as content, p.postDate as postDate, p.answerTo as answerTo, u as user " +
            "FROM Post p LEFT JOIN p.user u LEFT JOIN p.answerTo a WHERE p.id = :id")
    PostProjection findPostById(@Param("id") Long id);

    @Query("SELECT p.id as id, p.content as content, p.postDate as postDate, p.answerTo as answerTo, u as user FROM Post p LEFT JOIN p.user u WHERE p.answerTo.id = :answerToId")
    Page<PostProjection> findAnswers(@Param("answerToId") Long answerToId, Pageable pageable);
}

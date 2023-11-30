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

    @Query("SELECT p.id as id, p.content as content, p.postDate as postDate, p.likes as likes, p.answers as answers, p.available as available, p.answerTo as answerTo, u as user " +
            "FROM Post p LEFT JOIN p.user u LEFT JOIN p.answerTo a ORDER BY p.postDate DESC")
    List<PostProjection> findAllPosts();

    @Query("SELECT p.id as id, p.content as content, p.postDate as postDate, p.likes as likes, p.answers as answers, p.available as available, p.answerTo as answerTo, u as user " +
            "FROM Post p LEFT JOIN p.user u LEFT JOIN p.answerTo a WHERE p.id = :id")
    PostProjection findPostById(@Param("id") Long id);

    @Query("SELECT p.id as id, p.content as content, p.postDate as postDate, p.likes as likes, p.answers as answers, p.available as available, p.answerTo as answerTo, u as user FROM Post p LEFT JOIN p.user u WHERE p.answerTo.id = :answerToId ORDER BY p.postDate DESC")
    Page<PostProjection> findAnswers(@Param("answerToId") Long answerToId, Pageable pageable);

    @Query("SELECT p FROM Post p WHERE p.user.id = :userId AND p.answerTo IS NULL ORDER BY p.postDate DESC")
    List<PostProjection> findUserPostsWhereAnswerToIsNull(Long userId);

    @Query("SELECT p FROM Post p WHERE p.user.id = :userId AND p.answerTo IS NOT NULL ORDER BY p.postDate DESC")
    List<PostProjection> findUserPostsWhereAnswerToIsNotNull(Long userId);

    @Query("SELECT u.liked FROM User u WHERE u.id = :userId")
    List<Post> findPostsLikedByUserId(@Param("userId") Long userId);
}

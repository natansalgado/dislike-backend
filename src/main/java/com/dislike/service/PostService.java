package com.dislike.service;

import com.dislike.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {

    Post save(Long userId, String content);

    List<Post> findAll();

    Optional<Post> findById(Long id);

    void deleteById(Long id);
}

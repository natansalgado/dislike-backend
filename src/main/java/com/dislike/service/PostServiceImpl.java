package com.dislike.service;

import com.dislike.dto.PostRequestDTO;
import com.dislike.model.Post;
import com.dislike.model.User;
import com.dislike.repository.PostRepository;
import com.dislike.repository.UserRepository;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Post save(Long userId, String content) {
       User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        Post post = new Post();

        post.setUser(user);
        post.setContent(content);
        return postRepository.save(post);
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }
}

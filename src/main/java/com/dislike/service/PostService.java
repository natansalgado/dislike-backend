package com.dislike.service;

import com.dislike.dto.PostWithoutPostsDTO;
import com.dislike.dto.UserWithoutPostsDTO;
import com.dislike.exception.NotFoundException;
import com.dislike.model.Post;
import com.dislike.model.User;
import com.dislike.repository.PostRepository;
import com.dislike.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public Object save(Long userId, String content) {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            throw new NotFoundException("User not found");
        }

        Post post = new Post();
        post.setUser(user);
        post.setContent(content);

        Post toReturn = postRepository.save(post);
        toReturn.setUser(null);

        return toReturn;
    }

    public List<PostWithoutPostsDTO> findAll() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(post -> new PostWithoutPostsDTO(
                        post.getId(),
                        post.getContent(),
                        post.getPostDate().toString(),
                        new UserWithoutPostsDTO(
                                post.getUser().getId(),
                                post.getUser().getName(),
                                post.getUser().getUsername()
                        )
                ))
                .collect(Collectors.toList());
    }

    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }
}

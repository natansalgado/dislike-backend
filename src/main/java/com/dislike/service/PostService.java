package com.dislike.service;

import com.dislike.dto.PostRequestDTO;
import com.dislike.exception.NotFoundException;
import com.dislike.model.Post;
import com.dislike.model.User;
import com.dislike.projection.post.PostProjection;
import com.dislike.repository.PostRepository;
import com.dislike.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public Object save(PostRequestDTO data) {
        User user = userRepository.findById(data.getUserId()).orElse(null);

        if (user == null) {
            throw new NotFoundException("User not found");
        }

        Post answerTo = postRepository.findById(data.getAnswerTo()).orElse(null);

        Post post = new Post();
        post.setUser(user);
        post.setContent(data.getContent());

        if (answerTo != null) {
            post.setAnswerTo(answerTo);
        }

        Post toReturn = postRepository.save(post);
        toReturn.setUser(null);

        return toReturn;
    }

    public List<PostProjection> findAll() {
        return postRepository.findAllPosts();
    }

    public PostProjection findById(Long id) {
        PostProjection post = postRepository.findPostById(id);

        if (post == null) {
            throw new NotFoundException("Post not found");
        }

        return post;
    }

    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    public Page<PostProjection> findAnswers(Long id, int offset, int limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        return postRepository.findAnswers(id, pageable);
    }
}

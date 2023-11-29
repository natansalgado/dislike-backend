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

        Post post = new Post();
        post.setUser(user);
        post.setContent(data.getContent());
        post.setLikes(0);
        post.setAnswers(0);
        post.setAvailable(true);

        if (data.getAnswerTo() != null) {
            Post answerTo = postRepository.findById(data.getAnswerTo()).orElse(null);
            post.setAnswerTo(answerTo);
            answerTo.setAnswers(answerTo.getAnswers() + 1);
            postRepository.save(answerTo);
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

    public boolean likePost(Long userId, Long postId) {
        Post postLiked = postRepository.findById(postId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);

        if (postLiked != null && user != null) {
            List<Post> likedPosts = user.getLiked();

            if (likedPosts.contains(postLiked)) {
                likedPosts.remove(postLiked);
                user.setLiked(likedPosts);
                postLiked.setLikes(postLiked.getLikes() - 1);
            } else {
                likedPosts.add(postLiked);
                user.setLiked(likedPosts);
                postLiked.setLikes(postLiked.getLikes() + 1);
            }

            userRepository.save(user);
            postRepository.save(postLiked);
            return true;
        }

        return false;
    }
}

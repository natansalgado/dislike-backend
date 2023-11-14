package com.dislike.controller;

import com.dislike.dto.PostRequestDTO;
import com.dislike.dto.PostWithoutPostsDTO;
import com.dislike.model.Post;
import com.dislike.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    private ResponseEntity<List<PostWithoutPostsDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(postService.findAll());
    }

    @GetMapping("/{id}")
    private ResponseEntity<Optional<Post>> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.findById(id));
    }

    @PostMapping
    private ResponseEntity<Object> create(@RequestBody PostRequestDTO postRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.save(postRequest.getUserId(), postRequest.getContent()));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

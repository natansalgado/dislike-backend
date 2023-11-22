package com.dislike.controller;

import com.dislike.dto.PostRequestDTO;
import com.dislike.model.Post;
import com.dislike.projection.post.PostProjection;
import com.dislike.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    private ResponseEntity<List<PostProjection>> findAll() {
        return ResponseEntity.ok(postService.findAll());
    }

    @GetMapping("/{id}")
    private ResponseEntity<PostProjection> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.findById(id));
    }

    @PostMapping
    private ResponseEntity<Object> create(@RequestBody PostRequestDTO postRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.save(postRequest));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/answers")
    private ResponseEntity<?> findAnswers(@RequestParam("id") Long id, @RequestParam("offset") int offset, @RequestParam("limit") int limit) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.findAnswers(id, offset, limit));
    }
}

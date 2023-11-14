package com.dislike.controller;

import com.dislike.model.User;
import com.dislike.projection.FindAllUsersProjection;
import com.dislike.projection.UserWithPostsProjection;
import com.dislike.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<FindAllUsersProjection>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserWithPostsProjection> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.save(user));
    }

    @PutMapping
    public ResponseEntity<User> update(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

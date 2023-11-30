package com.dislike.controller;

import com.dislike.model.User;
import com.dislike.projection.user.FindAllProjection;
import com.dislike.projection.user.FindByUsernameProjection;
import com.dislike.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<FindAllProjection>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{username}")
    public ResponseEntity<FindByUsernameProjection> findById(@PathVariable String username) {
        return ResponseEntity.ok(userService.findUserByUsername(username));
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @PutMapping
    public ResponseEntity<User> update(@RequestBody User user) {
        return ResponseEntity.ok(userService.update(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/loggedin")
    public ResponseEntity<Optional<User>> getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        if (username != null) {
            return ResponseEntity.ok(userService.findByUsername(username));
        }
        return null;
    }
}

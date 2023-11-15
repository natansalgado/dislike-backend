package com.dislike.controller;

import com.dislike.model.User;
import com.dislike.projection.post.FindAllProjection;
import com.dislike.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("login")
    private ResponseEntity<Boolean> findAll(@RequestParam String login, @RequestParam String password) {
        return ResponseEntity.ok(authService.login(login, password));
    }
}

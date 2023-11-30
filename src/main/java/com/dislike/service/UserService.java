package com.dislike.service;

import com.dislike.exception.ConflictException;
import com.dislike.model.User;
import com.dislike.projection.user.FindAllProjection;
import com.dislike.projection.user.FindByUsernameProjection;
import com.dislike.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    public User save(User user) {
        Optional<User> usernameInUse = userRepository.findByUsername(user.getUsername());

        if (usernameInUse.isPresent()) {
            throw new ConflictException("Username is already in use");
        }

        Optional<User> emailInUse = userRepository.findUserByEmail(user.getEmail());

        if (emailInUse.isPresent()) {
            throw new ConflictException("Email is already in use");
        }

        user.setPassword(encoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public List<FindAllProjection> findAll() {
        return userRepository.findAllUsers();
    }

    public FindByUsernameProjection findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}

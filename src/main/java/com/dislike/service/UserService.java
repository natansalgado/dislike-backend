package com.dislike.service;

import com.dislike.model.User;
import com.dislike.projection.FindAllUsersProjection;
import com.dislike.projection.UserWithPostsProjection;
import com.dislike.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<FindAllUsersProjection> findAll() {
        return userRepository.findAllUsersProjection();
    }

    public UserWithPostsProjection findById(Long userId) {
        return userRepository.findUserWithPostsById(userId);
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}

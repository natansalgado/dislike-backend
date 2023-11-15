package com.dislike.service;

import com.dislike.exception.UnauthorizedException;
import com.dislike.model.User;
import com.dislike.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    public Boolean login(String login, String password) {

        Optional<User> optUser = userRepository.findByUsername(login);
        if (!optUser.isPresent()) {
            optUser = userRepository.findUserByEmail(login);

            if(!optUser.isPresent()) {
                throw new UnauthorizedException("Not allowed");
            }
        }

        User user = optUser.get();
        boolean valid = encoder.matches(password, user.getPassword());

        if (!valid) {
            throw new UnauthorizedException("Not allowed");
        }

        return valid;
    }
}

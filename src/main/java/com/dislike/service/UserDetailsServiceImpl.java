package com.dislike.service;

import com.dislike.data.UserDetailsData;
import com.dislike.exception.NotFoundException;
import com.dislike.model.User;
import com.dislike.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        Optional<User> user;

       Optional<User> userByUsername = userRepository.findByUsername(login);
       Optional<User> userByEmail = userRepository.findUserByEmail(login);

       if (userByUsername.isPresent()) {
           user = userByUsername;
       } else if (userByEmail.isPresent()) {
           user = userByEmail;
       } else {
           throw new NotFoundException("Invalid username or password");
       }

        return new UserDetailsData(user);
    }
}

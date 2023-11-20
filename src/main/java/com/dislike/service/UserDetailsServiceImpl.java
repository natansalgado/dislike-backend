package com.dislike.service;

import com.dislike.data.UserDetailsData;
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
        Optional<User> userByUsername = userRepository.findByUsername(login);
        Optional<User> userByEmail = userRepository.findUserByEmail(login);

        User user = userByUsername.orElseGet(() -> userByEmail.orElseThrow(() -> new UsernameNotFoundException("Invalid username or password")));

        UserDetailsData userDetails = new UserDetailsData();
        userDetails.setUsername(user.getUsername());
        userDetails.setPassword(user.getPassword());

        return userDetails;
    }
}

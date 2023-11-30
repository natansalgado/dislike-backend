package com.dislike.repository;

import com.dislike.model.User;
import com.dislike.projection.user.FindAllProjection;
import com.dislike.projection.user.FindByUsernameProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findUserByEmail(String email);

    @Query("SELECT u.id AS id, u.name AS name, u.username AS username, u.email AS email FROM User u")
    List<FindAllProjection> findAllUsers();

    @Query("SELECT u FROM User u LEFT JOIN u.posts p WHERE u.username = :username")
    FindByUsernameProjection findUserByUsername(String username);
}

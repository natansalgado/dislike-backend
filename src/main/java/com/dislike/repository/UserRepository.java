package com.dislike.repository;

import com.dislike.model.User;
import com.dislike.projection.FindAllUsersProjection;
import com.dislike.projection.UserWithPostsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u.id AS id, u.name AS name, u.username AS userName, u.email AS email FROM User u")
    List<FindAllUsersProjection> findAllUsersProjection();

    @Query("SELECT u FROM User u LEFT JOIN u.posts p WHERE u.id = :userId")
    UserWithPostsProjection findUserWithPostsById(Long userId);
}

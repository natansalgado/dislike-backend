package com.dislike.projection.user;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({ "id", "name", "username", "email", "posts" })
public interface FindByIdProjection {

    Long getId();
    String getName();
    String getUsername();
    String getEmail();
    List<PostProjection> getPosts();

    interface PostProjection {

        Long getId();
        String getContent();
        String getPostDate();
    }
}
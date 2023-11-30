package com.dislike.projection.user;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({ "id", "name", "username", "email", "image", "bio" })
public interface FindByUsernameProjection {

    Long getId();
    String getName();
    String getUsername();
    String getEmail();
    String getImage();
    String getBio();

}
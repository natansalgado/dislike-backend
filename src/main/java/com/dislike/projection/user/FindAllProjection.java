package com.dislike.projection.user;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "name", "username", "email" })
public interface FindAllProjection {

    Long getId();
    String getName();
    String getUsername();
    String getEmail();
}
package com.dislike.projection.post;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "content", "postDate", "user" })
public interface FindAllProjection {

    Long getId();
    String getContent();
    String getPostDate();
    UserProjection getUser();


    interface UserProjection {

        Long getId();
        String getName();
        String getUsername();
    }
}

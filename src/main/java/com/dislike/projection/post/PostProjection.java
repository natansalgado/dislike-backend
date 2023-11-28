package com.dislike.projection.post;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "content", "postDate", "user", "likes", "answerTo" })
public interface PostProjection {

    Long getId();
    String getContent();
    String getPostDate();
    UserProjection getUser();
    int getLikes();
    AnswerToProjection getAnswerTo();

    @JsonPropertyOrder({ "id", "name", "username" })
    interface UserProjection {
        Long getId();
        String getName();
        String getUsername();
    }

    @JsonPropertyOrder({ "id", "content", "postDate", "likes", "user" })
    interface AnswerToProjection {
        Long getId();
        String getContent();
        String getPostDate();
        int getLikes();
        UserProjection getUser();
    }
}

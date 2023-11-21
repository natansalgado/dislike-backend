package com.dislike.projection.post;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "content", "postDate", "user", "answerTo" })
public interface PostProjection {

    Long getId();
    String getContent();
    String getPostDate();
    UserProjection getUser();
    AnswerToProjection getAnswerTo();

    @JsonPropertyOrder({ "id", "name", "username" })
    interface UserProjection {
        Long getId();
        String getName();
        String getUsername();
    }

    @JsonPropertyOrder({ "id", "content", "postDate", "user" })
    interface AnswerToProjection {
        Long getId();
        String getContent();
        String getPostDate();
        UserProjection getUser();
    }
}

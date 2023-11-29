package com.dislike.projection.post;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "content", "postDate", "user", "likes", "answers", "available", "answerTo" })
public interface PostProjection {

    Long getId();
    String getContent();
    String getPostDate();
    UserProjection getUser();
    int getLikes();
    int getAnswers();
    boolean getAvailable();
    AnswerToProjection getAnswerTo();

    @JsonPropertyOrder({ "id", "name", "username" })
    interface UserProjection {
        Long getId();
        String getName();
        String getUsername();
    }

    @JsonPropertyOrder({ "id", "content", "postDate", "likes", "answers", "available", "user" })
    interface AnswerToProjection {
        Long getId();
        String getContent();
        String getPostDate();
        int getLikes();
        int getAnswers();
        boolean getAvailable();
        UserProjection getUser();
    }
}

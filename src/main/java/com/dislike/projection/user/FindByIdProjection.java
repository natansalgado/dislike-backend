package com.dislike.projection.user;

import com.dislike.projection.post.PostProjection;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({ "id", "name", "username", "email", "posts" })
public interface FindByIdProjection {

    Long getId();
    String getName();
    String getUsername();
    String getEmail();
    List<PostProjection> getPosts();

    @JsonPropertyOrder({ "id", "content", "postDate", "answerTo" })
    interface PostProjection {

        Long getId();
        String getContent();
        String getPostDate();
        PostProjection2 getAnswerTo();

        @JsonPropertyOrder({ "id", "content", "postDate" })
        interface PostProjection2 {

            Long getId();
            String getContent();
            String getPostDate();
        }
    }
}
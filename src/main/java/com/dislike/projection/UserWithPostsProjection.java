package com.dislike.projection;

import java.util.List;

public interface UserWithPostsProjection {
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
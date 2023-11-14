package com.dislike.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostWithoutPostsDTO {

    private Long id;
    private String content;
    private String postDate;
    private UserWithoutPostsDTO user;

    public PostWithoutPostsDTO(Long id, String content, String postDate, UserWithoutPostsDTO user) {
        this.id = id;
        this.content = content;
        this.postDate = postDate;
        this.user = user;
    }
}

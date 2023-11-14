package com.dislike.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserWithoutPostsDTO {

    private Long id;
    private String name;
    private String username;

    public UserWithoutPostsDTO(Long id, String name, String username) {
        this.id = id;
        this.name = name;
        this.username = username;
    }
}

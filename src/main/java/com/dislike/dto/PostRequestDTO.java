package com.dislike.dto;

import lombok.Data;

@Data
public class PostRequestDTO {

    private Long userId;

    private Long answerTo;

    private String content;
}

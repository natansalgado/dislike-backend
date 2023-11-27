package com.dislike.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "tb_posts", schema = "dislike")
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 1000)
    private String content;

    @Temporal(TemporalType.DATE)
    @Column(name = "post_date")
    private Date postDate;

    @Column
    private int likes;

    @ManyToOne
    @JoinColumn(name = "answer_to")
    private Post answerTo;

    @PrePersist
    protected void onCreate(){
        postDate = new Date();
    }

    public void setAnswerTo(Post answerTo) {
        this.answerTo = answerTo;
    }
}

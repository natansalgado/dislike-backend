package com.dislike.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "tb_posts", schema = "dislike")
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 1000)
    private String content;

    @Temporal(TemporalType.DATE)
    @Column(name = "post_date")
    private Date postDate;

    @PrePersist
    protected void onCreate(){
        postDate = new Date();
    }

}

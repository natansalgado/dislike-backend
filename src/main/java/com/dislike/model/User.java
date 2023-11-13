package com.dislike.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "tb_users", schema = "dislike")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String username;

    private String email;

    private String password;
}

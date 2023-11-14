package com.dislike.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "tb_users", schema = "dislike")
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String username;

    private String email;

    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Post> posts;
}

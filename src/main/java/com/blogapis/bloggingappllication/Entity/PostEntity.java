package com.blogapis.bloggingappllication.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor

public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    @Column(name = "post_title", length = 30, nullable = false)
    private String postTitle;
    @Column(length = 1000)
    private String content;
    private String image;
    @Column(name = "createdAt")
    private Date addDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
    @ManyToOne 
    private UserEntity user;
}

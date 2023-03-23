package com.blogapis.bloggingappllication.DTO;

import com.blogapis.bloggingappllication.Entity.CategoryEntity;
import com.blogapis.bloggingappllication.Entity.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO {

    private Integer postId;

    private String postTitle;
    private String content;
    private String image;
    private Date date;

    private CategoryDTO category; // if we have used entities then it will have gone to infinite loop
    private UserDTO user; // vice versa

}

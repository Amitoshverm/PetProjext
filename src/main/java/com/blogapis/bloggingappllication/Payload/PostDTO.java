package com.blogapis.bloggingappllication.Payload;

import com.blogapis.bloggingappllication.Entity.CommentEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    private List<CommentDTO> commentEntities = new ArrayList<>();

}

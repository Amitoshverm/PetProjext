package com.blogapis.bloggingappllication.Payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter@NoArgsConstructor
public class CommentDTO {


    private Integer commentId;
    private String content;

}

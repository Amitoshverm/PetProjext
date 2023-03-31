package com.blogapis.bloggingappllication.Service.CommentService;

import com.blogapis.bloggingappllication.Payload.CommentDTO;

public interface CommentMeths {

    CommentDTO comment(CommentDTO comment, Integer postId);
    void deleteComment(Integer commentId);


}

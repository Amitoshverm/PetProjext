package com.blogapis.bloggingappllication.Service.CommentService;

import com.blogapis.bloggingappllication.Payload.CommentDTO;

import java.util.List;

public interface CommentServiceMeths {

    CommentDTO createComment(CommentDTO comment, Integer postId);
    void deleteComment(Integer commentId);

    List<CommentDTO> getAllComment();

    CommentDTO updateComment(CommentDTO comment, Integer commentId);


}

package com.blogapis.bloggingappllication.Service.CommentService;

import com.blogapis.bloggingappllication.Entity.Comment;
import com.blogapis.bloggingappllication.Payload.CommentDTO;
import com.blogapis.bloggingappllication.Payload.PostDTO;
import com.blogapis.bloggingappllication.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentImpl implements CommentMeths{

    @Autowired
    private CommentRepository commentRepository;
    @Override
    public CommentDTO comment(CommentDTO comment, Integer postId) {
        CommentDTO commentDTO = this.commentRepository.findById(postId);
    }

    @Override
    public void deleteComment(Integer commentId) {

    }
}

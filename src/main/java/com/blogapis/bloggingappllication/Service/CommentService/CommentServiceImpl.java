package com.blogapis.bloggingappllication.Service.CommentService;

import com.blogapis.bloggingappllication.CustomException.ResourceNotFoundException;
import com.blogapis.bloggingappllication.Entity.CommentEntity;
import com.blogapis.bloggingappllication.Entity.PostEntity;
import com.blogapis.bloggingappllication.Payload.CommentDTO;
import com.blogapis.bloggingappllication.Payload.PostDTO;
import com.blogapis.bloggingappllication.Repository.CommentRepository;
import com.blogapis.bloggingappllication.Repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.Comment;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentServiceMeths {


    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CommentDTO createComment(CommentDTO commentDTO, Integer postId) {
        PostEntity post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "postId", postId));
        CommentEntity comment = this.modelMapper.map(commentDTO, CommentEntity.class);
        comment.setPost(post);
        CommentEntity comment1 = this.commentRepository.save(comment);
        return this.modelMapper.map(comment1, CommentDTO.class);


    }

    @Override
    public void deleteComment(Integer commentId) {

        CommentEntity comment = this.commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comment", "commentId", commentId));
        this.commentRepository.delete(comment);


    }

    @Override
    public List<CommentDTO> getAllComment() {
        List<CommentEntity> entities = this.commentRepository.findAll();
        return entities.stream().map((comment)-> this.modelMapper.map(comment, CommentDTO.class)).collect(Collectors.toList());
    }

    @Override
    public CommentDTO updateComment(CommentDTO comment, Integer commentId) {
        CommentEntity commentEntity = this.commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment", "commentId", commentId));
        commentEntity.setContent(comment.getContent());
        CommentEntity updatedComment = this.commentRepository.save(commentEntity);
        return this.modelMapper.map(updatedComment, CommentDTO.class);
    }
}

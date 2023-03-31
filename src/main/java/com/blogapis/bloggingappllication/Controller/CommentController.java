package com.blogapis.bloggingappllication.Controller;

import com.blogapis.bloggingappllication.Entity.CommentEntity;
import com.blogapis.bloggingappllication.Payload.ApiResponse;
import com.blogapis.bloggingappllication.Payload.CommentDTO;
import com.blogapis.bloggingappllication.Service.CommentService.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    public CommentServiceImpl commentService;


    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO comment, @PathVariable Integer postId){
         CommentDTO createComment = this.commentService.createComment(comment, postId);

         return new ResponseEntity<CommentDTO>(createComment, HttpStatus.OK);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId) {
        this.commentService.deleteComment(commentId);

        return new ResponseEntity<ApiResponse>(new ApiResponse(new Date(),"deleted successfully", true), HttpStatus.OK);
    }
    @GetMapping("/comments")
    public ResponseEntity<List<CommentDTO>> getAllComments(){
        List<CommentDTO> comments = this.commentService.getAllComment();
        return ResponseEntity.ok(comments);
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentDTO> editComment(@RequestBody CommentDTO commentDTO, @PathVariable Integer commentId){
        CommentDTO commentDTO1 = this.commentService.updateComment(commentDTO, commentId);
        return ResponseEntity.ok(commentDTO1);
    }

}

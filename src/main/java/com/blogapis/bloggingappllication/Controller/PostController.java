package com.blogapis.bloggingappllication.Controller;

import com.blogapis.bloggingappllication.DTO.ApiResponse;
import com.blogapis.bloggingappllication.DTO.PostDTO;
import com.blogapis.bloggingappllication.Entity.CategoryEntity;
import com.blogapis.bloggingappllication.Entity.PostEntity;
import com.blogapis.bloggingappllication.Repository.PostRepository;
import com.blogapis.bloggingappllication.Service.PostServices.PostServiceImp;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostServiceImp postService;

    //create Post
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO,
                                              @PathVariable Integer userId,
                                              @PathVariable Integer categoryId) {
        PostDTO createdPost = this.postService.createPost(postDTO, userId, categoryId);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    // get posts by category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDTO>> getPostsByCategory(@PathVariable Integer categoryId) {
        List<PostDTO> postDTOS = this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<List<PostDTO>>(postDTOS, HttpStatus.OK);
    }

    // get by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDTO>> getPostsByUser(@PathVariable Integer userId) {
        List<PostDTO> posts = this.postService.getPostsByUser(userId);
        return new ResponseEntity<List<PostDTO>>(posts, HttpStatus.OK);
    }

    // get all posts
    @GetMapping("/posts")
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        List<PostDTO> posts = this.postService.getAllPosts();
        return new ResponseEntity<List<PostDTO>>(posts, HttpStatus.OK);
    }

    // delete Post
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {
        this.postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse(new Date(), "Post deleted successfully", true), HttpStatus.OK);
    }

    // post by id
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId) {
        PostDTO post = this.postService.getPostById(postId);
        return new ResponseEntity<PostDTO>(post, HttpStatus.OK);
    }

    // update post
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO post,@PathVariable Integer postId){
        PostDTO postDTO = this.postService.updatePost(post, postId);

        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }
}
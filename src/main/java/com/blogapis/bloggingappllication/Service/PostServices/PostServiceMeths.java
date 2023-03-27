package com.blogapis.bloggingappllication.Service.PostServices;

import com.blogapis.bloggingappllication.Payload.PostDTO;
import com.blogapis.bloggingappllication.Entity.PostEntity;
import com.blogapis.bloggingappllication.Payload.PostResponse;

import java.util.List;

public interface PostServiceMeths {

    // CREATE
    PostDTO createPost(PostDTO postDTO, Integer categoryId, Integer userId);

    //UPDATE
    PostDTO updatePost(PostDTO post, Integer postId);

    //DELETE
    void deletePost(Integer postId);

    //GET
    PostDTO getPostById(Integer postId);

    //GET ALL
//    List<PostDTO> getAllPosts();
    PostResponse getAllPosts(Integer pageNumber, Integer PageSize);  /** PAGINATION */

    //GET ALL POST BY CATEGORY
    List<PostDTO> getPostsByCategory(Integer categoryId);

    //GET ALL POST BY USER
    List<PostDTO> getPostsByUser(Integer userId);

    //SEARCH POSTS
    List<PostEntity> searchPosts(String keyword);

}

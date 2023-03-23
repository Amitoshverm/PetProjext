package com.blogapis.bloggingappllication.Service.PostServices;

import com.blogapis.bloggingappllication.DTO.PostDTO;
import com.blogapis.bloggingappllication.Entity.PostEntity;

import java.util.List;

public interface PostServiceMeths {

    // CREATE
    PostDTO createPost(PostDTO postDTO, Integer categoryId, Integer userId);

    //UPDATE
    PostEntity updatePost(PostEntity post, Integer postId);

    //DELETE
    void deletePost(Integer postId);

    //GET
    PostEntity getPostById(Integer postId);

    //GET ALL
    List<PostEntity> getAllPosts();

    //GET ALL POST BY CATEGORY
    List<PostDTO> getPostsByCategory(Integer categoryId);

    //GET ALL POST BY USER
    List<PostDTO> getPostsByUser(Integer userId);

    //SEARCH POSTS
    List<PostEntity> searchPosts(String keyword);

}

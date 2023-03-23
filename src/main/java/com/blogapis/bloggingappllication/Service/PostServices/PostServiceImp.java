package com.blogapis.bloggingappllication.Service.PostServices;

import com.blogapis.bloggingappllication.CustomException.ResourceNotFoundException;
import com.blogapis.bloggingappllication.DTO.PostDTO;
import com.blogapis.bloggingappllication.DTO.UserDTO;
import com.blogapis.bloggingappllication.Entity.CategoryEntity;
import com.blogapis.bloggingappllication.Entity.PostEntity;
import com.blogapis.bloggingappllication.Entity.UserEntity;
import com.blogapis.bloggingappllication.Repository.CategoryRepository;
import com.blogapis.bloggingappllication.Repository.PostRepository;
import com.blogapis.bloggingappllication.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImp implements PostServiceMeths{

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId) {
        UserEntity user = this.userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "userId", userId));

        CategoryEntity category = this.categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("category", "categoryId", categoryId));

        PostEntity postEntity1 = this.modelMapper.map(postDTO, PostEntity.class);
        postEntity1.setImage("default.png");
        postEntity1.setAddDate(new Date());
        postEntity1.setUser(user);
        postEntity1.setCategory(category);

        PostEntity savedPost = this.postRepository.save(postEntity1);
        return this.modelMapper.map(savedPost, PostDTO.class);
    }

    @Override
    public PostEntity updatePost(PostEntity post, Integer postId) {
        return null;
    }

    @Override
    public void deletePost(Integer postId) {

    }

    @Override
    public PostEntity getPostById(Integer postId) {
        return null;
    }

    @Override
    public List<PostEntity> getAllPosts() {
        return null;
    }

    @Override
    public List<PostDTO> getPostsByCategory(Integer categoryId) {
        CategoryEntity category = this.categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("category", "categoryId", categoryId));
        List<PostEntity> postEntities = this.postRepository.findByCategory(category);

        return postEntities
                .stream()
                .map((PostEntity)-> this.modelMapper.map(postEntities, PostDTO.class))
                .collect(Collectors.toList());

    }

    @Override
    public List<PostDTO> getPostsByUser(Integer userId) {
        UserEntity user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "userId", userId));
        List<PostEntity> postEntities = this.postRepository.findByUser(user);
        List<PostDTO> posts = postEntities
                .stream()
                .map((PostEntity)-> this.modelMapper.map(postEntities, PostDTO.class))
                .collect(Collectors.toList());

        return posts;
    }

    @Override
    public List<PostEntity> searchPosts(String keyword) {
        return null;
    }
}

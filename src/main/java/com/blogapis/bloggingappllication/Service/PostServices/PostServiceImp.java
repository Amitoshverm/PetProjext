package com.blogapis.bloggingappllication.Service.PostServices;

import com.blogapis.bloggingappllication.CustomException.ResourceNotFoundException;
import com.blogapis.bloggingappllication.DTO.PostDTO;
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
    public PostDTO updatePost(PostDTO post, Integer postId) {
        PostEntity postEntity = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "postId", postId));
        postEntity.setPostTitle(post.getPostTitle());
        postEntity.setContent(post.getContent());
        postEntity.setImage(post.getImage());

        PostEntity updatedPost = this.postRepository.save(postEntity);
        return this.modelMapper.map(updatedPost, PostDTO.class);
    }

    @Override
    public void deletePost(Integer postId) {
        PostEntity postEntity = this.postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("post", "postId", postId));

        this.postRepository.delete(postEntity);
    }

    @Override
    public PostDTO getPostById(Integer postId) {
        PostEntity postEntity = this.postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("post", "postId", postId));

        PostDTO post = this.modelMapper.map(postEntity, PostDTO.class);
        return post;

    }

    @Override
    public List<PostDTO> getAllPosts() {
        List<PostEntity> allPosts = this.postRepository.findAll();
        List<PostDTO> posts = allPosts.stream()
                .map((post) -> this.modelMapper.map(post, PostDTO.class))
                .collect(Collectors.toList()) ;
        return posts;
    }

    @Override
    public List<PostDTO> getPostsByCategory(Integer categoryId) {
        CategoryEntity category = this.categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("category", "categoryId", categoryId));
        List<PostEntity> postEntities = this.postRepository.findByCategory(category);

        List<PostDTO> postDTOS = postEntities.stream()
                .map((post)-> this.modelMapper.map(post, PostDTO.class))
                .collect(Collectors.toList());
        return postDTOS;
    }

    @Override
    public List<PostDTO> getPostsByUser(Integer userId) {
        UserEntity user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "userId", userId));
        List<PostEntity> postEntities = this.postRepository.findByUser(user);
        List<PostDTO> posts = postEntities
                .stream()
                .map((post)-> this.modelMapper.map(post, PostDTO.class))
                .collect(Collectors.toList());

        return posts;
    }

    @Override
    public List<PostEntity> searchPosts(String keyword) {
        return null;
    }
}

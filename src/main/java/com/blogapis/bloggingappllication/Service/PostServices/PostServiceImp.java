package com.blogapis.bloggingappllication.Service.PostServices;

import com.blogapis.bloggingappllication.CustomException.ResourceNotFoundException;
import com.blogapis.bloggingappllication.Payload.PostDTO;
import com.blogapis.bloggingappllication.Entity.CategoryEntity;
import com.blogapis.bloggingappllication.Entity.PostEntity;
import com.blogapis.bloggingappllication.Entity.UserEntity;
import com.blogapis.bloggingappllication.Payload.PostResponse;
import com.blogapis.bloggingappllication.Repository.CategoryRepository;
import com.blogapis.bloggingappllication.Repository.PostRepository;
import com.blogapis.bloggingappllication.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

//    @Override
//    public List<PostDTO> getAllPosts() {
//        List<PostEntity> allPosts = this.postRepository.findAll();
//        List<PostDTO> posts = allPosts.stream()
//                .map((post) -> this.modelMapper.map(post, PostDTO.class))
//                .collect(Collectors.toList()) ;
//        return posts;
//    }
    /** PAGINATION */
    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {


//       Sort sort = sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        //above is done using ternary operator to avoid if else
        Sort sort = null;
        if (sortDir.equals("asc")){
            sort = Sort.by(sortBy).ascending();
        }
        else {
            sort = Sort.by(sortBy).descending();
        }

        Pageable page = PageRequest.of(pageNumber, pageSize, sort);

        Page<PostEntity> pagePost = this.postRepository.findAll(page);
        List<PostEntity> allPost = pagePost.getContent();
        List<PostDTO> postDTOS = allPost
                .stream()
                .map((post)-> this.modelMapper.map(post, PostDTO.class))
                .collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDTOS);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElement(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
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
    public List<PostDTO> searchPosts(String keyword) {
        List<PostEntity> postEntities = this.postRepository.findBypostTitleContaining(keyword);
        List<PostDTO> searchedPosts = postEntities
                .stream().
                map((post)->this.modelMapper.map(post, PostDTO.class))
                .collect(Collectors.toList());
        return searchedPosts;
    }
}

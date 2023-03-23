package com.blogapis.bloggingappllication.Repository;

import com.blogapis.bloggingappllication.Entity.CategoryEntity;
import com.blogapis.bloggingappllication.Entity.PostEntity;
import com.blogapis.bloggingappllication.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Integer> {

    List<PostEntity> findByUser(UserEntity user);
    List<PostEntity> findByCategory(CategoryEntity category);
}

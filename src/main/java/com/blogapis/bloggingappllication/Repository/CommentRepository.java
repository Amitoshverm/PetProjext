package com.blogapis.bloggingappllication.Repository;

import com.blogapis.bloggingappllication.Entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
}

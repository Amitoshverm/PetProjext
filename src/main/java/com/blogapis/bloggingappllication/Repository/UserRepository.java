package com.blogapis.bloggingappllication.Repository;

import com.blogapis.bloggingappllication.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}

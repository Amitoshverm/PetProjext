package com.blogapis.bloggingappllication.Repository;

import com.blogapis.bloggingappllication.Entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
}

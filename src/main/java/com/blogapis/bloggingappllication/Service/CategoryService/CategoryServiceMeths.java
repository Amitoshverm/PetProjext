package com.blogapis.bloggingappllication.Service.CategoryService;

import com.blogapis.bloggingappllication.DTO.CategoryDTO;

import java.util.List;

public interface CategoryServiceMeth {

    // CREATE
     CategoryDTO createCategory(CategoryDTO categoryDTO);

    // UPDATE
    CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId);

    // DELETE
    void deleteCategory(Integer categoryId);

    // GET
    CategoryDTO getCategoryById(Integer categoryId);

    // GET ALL
    List<CategoryDTO> getAllCategory();
}

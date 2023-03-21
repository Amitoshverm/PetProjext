package com.blogapis.bloggingappllication.Service.CategoryService;

import com.blogapis.bloggingappllication.CustomException.ResourceNotFoundException;
import com.blogapis.bloggingappllication.DTO.CategoryDTO;
import com.blogapis.bloggingappllication.Entity.CategoryEntity;
import com.blogapis.bloggingappllication.Repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImp implements CategoryServiceMeths {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        CategoryEntity category = this.modelMapper.map(categoryDTO, CategoryEntity.class);
        CategoryEntity savedCategory = categoryRepository.save(category);
        return this.modelMapper.map(savedCategory, CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId) {
        CategoryEntity category = this.categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("category", "CategoryId", categoryId));
        category.setCategoryTitle(categoryDTO.getCategoryTitle());
        category.setCategoryDescription(categoryDTO.getCategoryDescription());

        CategoryEntity updatedCategory = this.categoryRepository.save(category);
        return this.modelMapper.map(updatedCategory, CategoryDTO.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        CategoryEntity category = this.categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("category", "CategoryId", categoryId));
        this.categoryRepository.delete(category);
    }

    @Override
    public CategoryDTO getCategoryById(Integer categoryId) {
        CategoryEntity category = this.categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("category", "CategoryId", categoryId));

        return this.modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        List<CategoryEntity> categoryEntities = this.categoryRepository.findAll();
        List<CategoryDTO> categoryDTOs = categoryEntities.stream()
                .map(categoryEntity -> this.modelMapper.map(categoryEntity, CategoryDTO.class))
                .collect(Collectors.toList()); 

        return categoryDTOs;
    }
}

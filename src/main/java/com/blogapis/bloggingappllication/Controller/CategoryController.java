package com.blogapis.bloggingappllication.Controller;

import com.blogapis.bloggingappllication.Payload.ApiResponse;
import com.blogapis.bloggingappllication.Payload.CategoryDTO;
import com.blogapis.bloggingappllication.Service.CategoryService.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryServiceImp categoryService;

    //CREATE
    @PostMapping("/")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        CategoryDTO categoryDTO1 = this.categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(categoryDTO1, HttpStatus.CREATED);
    }

    //UPDATE
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Integer categoryId){
        CategoryDTO categoryDTO1 = this.categoryService.updateCategory(categoryDTO, categoryId);
        return ResponseEntity.ok(categoryDTO1);
    }

    //DELETE
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId){
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(new ApiResponse(new Date(), "Category deleted successfully", true),HttpStatus.OK);
    }
    //GET
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> getById(@PathVariable Integer categoryId){
        CategoryDTO categorydto= this.categoryService.getCategoryById(categoryId);
        return ResponseEntity.ok(categorydto);
    }
    //GET ALL
    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> getAllCategory(){
        List<CategoryDTO> categoryDTOS = this.categoryService.getAllCategory();
        return ResponseEntity.ok(categoryDTOS);
    }

}

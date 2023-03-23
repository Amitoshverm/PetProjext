package com.blogapis.bloggingappllication.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {
    private Integer categoryId;

    @NotBlank
    @Size(min = 4)
    private String categoryTitle;

    @NotBlank
    @Size(min = 4)
    private String categoryDescription;
}

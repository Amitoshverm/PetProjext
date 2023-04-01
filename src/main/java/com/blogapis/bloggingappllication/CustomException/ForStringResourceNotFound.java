package com.blogapis.bloggingappllication.CustomException;

public class ForStringResourceNotFound extends RuntimeException {

    String resourceName;
    String fieldName;
    String param;

    public ForStringResourceNotFound(String resourceName, String fieldName, String param) {
        super(String.format("%s not found with %s : %s", resourceName, fieldName, param));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.param = param;

    }
}

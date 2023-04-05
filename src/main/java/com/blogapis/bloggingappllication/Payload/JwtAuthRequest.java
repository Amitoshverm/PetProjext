package com.blogapis.bloggingappllication.Payload;

import lombok.Data;

@Data
public class JwtAuthRequest {

    private String username;
    private String password;
}

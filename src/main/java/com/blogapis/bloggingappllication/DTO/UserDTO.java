package com.blogapis.bloggingappllication.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    Integer userId;
    String username;
    String email;
    String password;
    String about;
}

package com.blogapis.bloggingappllication.Payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private Integer userId;

    @NotEmpty
    @Size(min = 2, message = "name must be more than 2 character")
    private String username;

    @Email(message = "Email address not valid !!")
    private String email;

    @NotNull
    @Pattern(regexp = "^([a-zA-Z0-9]{8})", message = "Password should contain one UpperCase, number and should have length of 8")
    private String password;

    @NotNull
    private String about;
}

package com.blogapis.bloggingappllication.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer userId;
    @Column(name= "user_name", nullable = false)
    String username;
    @Column(name = "Email", nullable = false)
    String email;
    String password;
    String about;

}

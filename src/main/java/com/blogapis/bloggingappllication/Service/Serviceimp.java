package com.blogapis.bloggingappllication.Service;

import com.blogapis.bloggingappllication.DTO.UserDTO;

import java.util.List;

public interface Service {

    UserDTO createUser(UserDTO userDTO);
    List<UserDTO> getAllUsers();
    UserDTO updateUser(UserDTO userDTO, Integer userId);
    void deleteUser(Integer userId);
}

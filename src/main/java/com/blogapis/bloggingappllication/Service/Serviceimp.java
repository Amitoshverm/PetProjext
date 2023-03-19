package com.blogapis.bloggingappllication.Service;

import com.blogapis.bloggingappllication.DTO.UserDTO;

import java.util.List;

public interface Serviceimp {

    // This class is just basic operation for service this could be done directly without
    // creating this Serviceimp, just write these methods directly to UserService class without this imp class



    UserDTO createUser(UserDTO userDTO);
    List<UserDTO> getAllUsers();
    UserDTO updateUser(UserDTO userDTO, Integer userId);
    void deleteUser(Integer userId);
    UserDTO getById(Integer userId);
}

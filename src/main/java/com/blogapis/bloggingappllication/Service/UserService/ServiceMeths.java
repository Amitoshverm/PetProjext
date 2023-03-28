package com.blogapis.bloggingappllication.Service.UserService;

import com.blogapis.bloggingappllication.Payload.PostDTO;
import com.blogapis.bloggingappllication.Payload.UserDTO;

import java.util.List;

public interface ServiceMeths {

    // This class is just basic operation for service this could be done directly without
    // creating this Serviceimp, just write these methods directly to UserService class without this imp class

    UserDTO createUser(UserDTO userDTO);
    List<UserDTO> getAllUsers();
    UserDTO updateUser(UserDTO userDTO, Integer userId);
    void deleteUser(Integer userId);
    UserDTO getById(Integer userId);

}

package com.blogapis.bloggingappllication.Service;

import com.blogapis.bloggingappllication.Exception.ResourceNotFoundException
import com.blogapis.bloggingappllication.DTO.UserDTO;
import com.blogapis.bloggingappllication.Entity.UserEntity;
import com.blogapis.bloggingappllication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserDTO createUser(UserDTO userDTO){

        UserEntity user = this.dtoTOUserEntity(userDTO);
        UserEntity savedUser = this.userRepository.save(user);
        return this.userEntityToUserDTO(savedUser);
        /* This whole can be easily be done using model mapper */
        // To avoid having to write cumbersome/boilerplate code to map DTOs into entities and vice-versa,
        // we are going to use a library called ModelMapper.
        // The goal of ModelMapper is to make object mapping easy by automatically determining how one object model maps to another.
     }
    public List<UserDTO> getAllUsers(){
         List<UserEntity> users = this.userRepository.findAll();
         List<UserDTO> userDTOS = users.stream().map(user -> this.userEntityToUserDTO(user)).collect(Collectors.toList());
         return userDTOS;
    }

    public UserDTO getUserById(Integer userId){
        UserEntity user = this.userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("user", "id", userId));
        return this.userEntityToUserDTO(user);
    }
    public UserDTO updateUser(UserDTO userDTO, Integer userId){
        UserEntity user = this.userRepository.findById(userId).
                orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAbout(userDTO.getAbout());

        UserEntity UpdatedUser = this.userRepository.save(user);
        UserDTO userdto = this.userEntityToUserDTO(UpdatedUser);
        return userdto;
    }
    public void deleteUser(Integer userId){}
    public UserDTO getById(Integer userId){
        UserEntity user = this.userRepository.findById(userId).
                orElseThrow(()->new ResourceNotFoundException("user", "id", userId));
        UserDTO userDTOFound = this.userEntityToUserDTO(user);
        return userDTOFound;

    }



//    /**UserDTO --> UserEntity*/
    private UserEntity dtoTOUserEntity(UserDTO userDTO) {
        UserEntity user = new UserEntity();
        user.setUserId(userDTO.getUserId());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setAbout(userDTO.getAbout());
        return user;
    }


//    /**UserEntity --> UserDTO*/
    private UserDTO userEntityToUserDTO(UserEntity user){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword((user.getPassword()));
        userDTO.setAbout(user.getAbout());
        return userDTO;
    }
    /** Above two method conversion can be managed by model mapper
     * MODEL MAPPER - To avoid having to write cumbersome/boilerplate code to map DTOs into entities and vice-versa,
     * we are going to use a library called ModelMapper.
     * The goal of ModelMapper is to make object mapping easy by automatically determining how one object model maps to another.*/


}

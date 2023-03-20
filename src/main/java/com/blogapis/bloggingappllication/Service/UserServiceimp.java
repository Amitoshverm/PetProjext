package com.blogapis.bloggingappllication.Service;

import com.blogapis.bloggingappllication.CustomException.ResourceNotFoundException;
import com.blogapis.bloggingappllication.DTO.UserDTO;
import com.blogapis.bloggingappllication.Entity.UserEntity;
import com.blogapis.bloggingappllication.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceimp implements Serviceimp {
    // Here we could have directly use UserEntity but used DTO just because to make sure certain fields are exposed
    // like username and email, we would never want our password to be exposed directly, so we use DTOs

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    public UserDTO createUser(UserDTO userDTO){

        UserEntity user = this.dtoTOUserEntity(userDTO);
        UserEntity savedUser = this.userRepository.save(user);
        return this.userEntityToUserDTO(savedUser);
        /* This whole can be easily be done using model mapper */
        // To avoid having to write cumbersome/boilerplate code to map DTOs into entities and vice-versa,
        // we are going to use a library called ModelMapper.
        // The goal of ModelMapper is to make object mapping easy by automatically determining how one object model maps to another.
     }
     @Override
    public List<UserDTO> getAllUsers(){
         List<UserEntity> users = this.userRepository.findAll();
         List<UserDTO> userDTOS = users.stream().map(user -> this.userEntityToUserDTO(user)).collect(Collectors.toList());
         return userDTOS;
    }
    @Override
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
    @Override
    public void deleteUser(Integer userId){
        UserEntity user = this.userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("user", "id", userId));
        this.userRepository.delete(user);
    }
    @Override
    public UserDTO getById(Integer userId){
        UserEntity user = this.userRepository.findById(userId).
                orElseThrow(()->new ResourceNotFoundException("user", "id", userId));
        UserDTO userDTOFound = this.userEntityToUserDTO(user);
        return userDTOFound;

    }

//    /**UserDTO --> UserEntity*/
    private UserEntity dtoTOUserEntity(UserDTO userDTO) {
        //using modal mapper
        UserEntity user = this.modelMapper.map(userDTO, UserEntity.class);

//        user.setUserId(userDTO.getUserId());
//        user.setEmail(userDTO.getEmail());
//        user.setUsername(userDTO.getUsername());
//        user.setPassword(userDTO.getPassword());
//        user.setAbout(userDTO.getAbout());
        return user;
    }


//    /**UserEntity --> UserDTO*/
    private UserDTO userEntityToUserDTO(UserEntity user){
        //using modal mapper
        UserDTO userDTO = this.modelMapper.map(user,UserDTO.class);

//        userDTO.setUserId(user.getUserId());
//        userDTO.setUsername(user.getUsername());
//        userDTO.setEmail(user.getEmail());
//        userDTO.setPassword((user.getPassword()));
//        userDTO.setAbout(user.getAbout());
        return userDTO;
    }
    /* Above two method conversion can be managed by model mapper
     * MODEL MAPPER - To avoid having to write cumbersome/boilerplate code to map DTOs into entities and vice-versa,
     * we are going to use a library called ModelMapper.
     * The goal of ModelMapper is to make object mapping easy by automatically determining how one object model maps to another.*/


}

package com.blogapis.bloggingappllication.Controller;

import com.blogapis.bloggingappllication.DTO.UserDTO;
import com.blogapis.bloggingappllication.Entity.UserEntity;
import com.blogapis.bloggingappllication.Service.UserServiceimp;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserServiceimp userService;

    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
         UserDTO userCreated = this.userService.createUser(userDTO);
         return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
    }
    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getUserAllUsers(){
        List<UserDTO> users = this.userService.getAllUsers();
        return  ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getById(@PathVariable Integer userId){
        UserDTO user = this.userService.getById(userId);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable Integer userId){
        UserDTO updatedUser = this.userService.updateUser(userDTO, userId);
        return  ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId) {
        this.userService.deleteUser(userId);
        return new ResponseEntity<>(Map.of("message", "user deleted"), HttpStatus.OK);
    }
}

package com.blogapis.bloggingappllication;

import com.blogapis.bloggingappllication.CustomException.ForStringResourceNotFound;
import com.blogapis.bloggingappllication.CustomException.ResourceNotFoundException;
import com.blogapis.bloggingappllication.Entity.UserEntity;
import com.blogapis.bloggingappllication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //Load user from database by username
        UserEntity user =  this.userRepository.findByEmail(username)
                .orElseThrow(()-> new ResourceNotFoundException("user", "email "+ username, 0));

        return user;
    }
}

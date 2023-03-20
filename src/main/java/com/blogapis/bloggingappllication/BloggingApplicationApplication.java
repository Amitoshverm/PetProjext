package com.blogapis.bloggingappllication;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BloggingApplicationApplication {

    public static void main(String[] args) {

        SpringApplication.run(BloggingApplicationApplication.class, args);

    }
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }


}

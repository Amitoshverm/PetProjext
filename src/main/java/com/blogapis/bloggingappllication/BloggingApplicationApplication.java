package com.blogapis.bloggingappllication;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BloggingApplicationApplication implements CommandLineRunner {
    @Autowired
    private PasswordEncoder passwordEncoder;


    public static void main(String[] args) {

        SpringApplication.run(BloggingApplicationApplication.class, args);

    }
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println(this.passwordEncoder.encode("Sandeep9"));
    }
}

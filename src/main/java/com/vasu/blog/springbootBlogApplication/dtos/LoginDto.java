package com.vasu.blog.springbootBlogApplication.dtos;
import lombok.Data;

@Data
public class LoginDto {
    private String usernameOrEmail;
    private String password;
}

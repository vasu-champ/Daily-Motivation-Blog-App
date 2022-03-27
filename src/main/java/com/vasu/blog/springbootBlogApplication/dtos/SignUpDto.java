package com.vasu.blog.springbootBlogApplication.dtos;
import lombok.Data;

@Data
public class SignUpDto {
    private String name;
    private String username;
    private String email;
    private String password;
}

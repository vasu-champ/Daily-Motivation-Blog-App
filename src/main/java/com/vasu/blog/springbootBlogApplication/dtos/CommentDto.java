package com.vasu.blog.springbootBlogApplication.dtos;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
@Api(value = "Comment model information")
@Data
public class CommentDto {

    @ApiModelProperty(value = "Comment id")
    private long id;
    //name should not be null or empty
    @NotEmpty(message = "Name should not be null or empty")
    private String name;

    //email should not be null or empty
    //email field validation
    @ApiModelProperty(value = "Comment email")
    @NotEmpty(message = "Email should not be null or empty")
    @Email
    private String email;

    //comment should not be null or empty
    //comment body must be minimum 10 characters
    @NotEmpty
    @ApiModelProperty(value = "Comment body")
    @Size(min = 10, message = "Comment body must be minimum 10 characters")
    private String body;
}

package com.vasu.blog.springbootBlogApplication.service;
import com.vasu.blog.springbootBlogApplication.dtos.PostDto;
import com.vasu.blog.springbootBlogApplication.dtos.PostResponse;



public interface PostService {
    PostDto createPost(PostDto postDto);
    PostResponse getAllPosts(int pageNo, int pageSize , String sortBy, String sortDir);
    PostDto getPostById(long id);
    PostDto updatePost(PostDto postDto, long id);
    void deletePostById(long id);
}

package com.example.controller;

import com.example.domain.Post;
import com.example.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(value = "/multi-file")
public class PostController {

    private final PostService postService;

    PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("")
    public List<Post> getPosts(){
        return postService.getPost();
    }

    @PostMapping("")
    public List<Post> createPosts(@RequestBody Post post){

        postService.create(post);
        return postService.getPost();
    }

}

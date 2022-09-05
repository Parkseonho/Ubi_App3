package com.example.controller;

import com.example.Form.PostForm;
import com.example.domain.Post;
import com.example.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/multi-file")
    @ResponseBody
    public List<Post> getPost(){
        return postService.getPost();
    }

    @PostMapping("/multi-file")
    @ResponseBody
    public List<Post> createPost(PostForm postForm){

        postService.create(postForm.getContent());
        return postService.getPost();
    }
}

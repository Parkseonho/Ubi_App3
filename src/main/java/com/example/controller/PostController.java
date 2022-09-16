package com.example.controller;

import com.example.domain.Post;
import com.example.service.FilesService;
import com.example.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController()
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final FilesService filesService;

 /*   PostController(PostService postService, FilesService filesService) {
        this.postService = postService;
        this.filesService = filesService;
    }*/

    @GetMapping("/multi-file")
    public List<Post> getPosts(){
        return postService.getPost();
    }

    @PostMapping("/multi-file")
    public List<Post> createPosts(@RequestBody Post post, @RequestParam("multiFile")List<MultipartFile> multipartFileList)
    throws MultipartException {
        try{
            filesService.upload(post, multipartFileList);
        }catch (Exception e){
            postService.create(post);
        }

        return postService.getPost();
    }

    @PatchMapping("/multi-file/{id}")
    public List<Post> createPosts(@PathVariable Integer id) {
        postService.Like(id);

        return postService.getPost();
    }

  /*  @PostMapping("")
    public List<Post> createPosts(@RequestBody Post post,
                                  @RequestParam("multiFile") List<MultipartFile> multiFileList)
            throws InterruptedException{
        try{
            filesService.upload(post, multiFileList);
        }catch (Exception e){
            postService.create(post);
        }
        return postService.getPost();
    }*/

}

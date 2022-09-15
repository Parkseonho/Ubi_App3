package com.example.controller;

import com.example.domain.Post;
import com.example.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(value = "/multi-file")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
  /*  private final FilesService filesService;*/

 /*   PostController(PostService postService, FilesService filesService) {
        this.postService = postService;
        this.filesService = filesService;
    }*/

    @GetMapping("")
    public List<Post> getPosts(){
        return postService.getPost();
    }

    @PostMapping("")
    public List<Post> createPosts(@RequestBody Post post) {
            postService.create(post);

        return postService.getPost();
    }

    @PatchMapping("/{id}")
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

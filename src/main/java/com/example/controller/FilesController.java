package com.example.controller;

import com.example.Form.PostForm;
import com.example.service.FilesService;
import com.example.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequiredArgsConstructor
public class FilesController {

    private final FilesService filesService;

    private final PostService postService;

    @PostMapping("/multi-file")
    public String multiFileUpload(
            @RequestParam("multiFile") List<MultipartFile> multiFileList,
            PostForm postForm
             ) throws InterruptedException{
        try{
            filesService.upload(postForm, multiFileList);
        }catch (Exception e){
            postService.create(postForm.getContent());
        }
        return "redirect:/post/list";
    }
}

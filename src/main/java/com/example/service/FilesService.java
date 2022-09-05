package com.example.service;

import com.example.Form.PostForm;
import com.example.dao.FilesRepository;
import com.example.domain.Files;
import com.example.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class FilesService {
    private final FilesRepository filesRepository;
    private final PostService postService;

    /*
    public Files createTodo(Files todo) {
        Files newTodo = new Files();
        todoRepository.save(newTodo);

        return newTodo;
    }

    public List<Files> getTodos() {
        return todoRepository.findAll();
    }*/

    public void upload(PostForm postForm, List<MultipartFile> multiFileList) {
        String root = System.getProperty("user.dir")+
                "\\src\\main\\resources\\static\\uploadFiles";
        File fileCheck = new File(root);
        if(!fileCheck.exists()) fileCheck.mkdirs();

        List<Map<String, String>> fileList = new ArrayList<>();

        for(int i = 0; i < multiFileList.size();i++){
            String originFile = multiFileList.get(i).getOriginalFilename();
            String ext = originFile.substring(originFile.lastIndexOf("."));
            String changeFile = UUID.randomUUID().toString()+ext;

            Map<String, String> map = new HashMap<>();
            map.put("originFile", originFile);
            map.put("changeFile", changeFile);

            fileList.add(map);
        }

        try{
            for(int i = 0; i < multiFileList.size();i++){
                File uploadFile = new File(root + "\\"+fileList.get(i).get("changeFile"));
                multiFileList.get(i).transferTo(uploadFile);
            }
            uploadDB(fileList, postForm);
            System.out.printf("성공");
        } catch (IllegalStateException | IOException e) {
            System.out.printf("실패");
            for(int i = 0; i< multiFileList.size();i++){
                new File(root + "\\" + fileList.get(i).get("changeFile")).delete();
            }
            e.printStackTrace();
        }
    }

    private void uploadDB(List<Map<String, String>> fileList, PostForm postForm){
        Post post = postService.create(postForm.getContent());

        for(Map<String, String> file : fileList){
            Files files = new Files();
            files.setFilename(file.get("changeFile"));
            files.setPost(post);
            filesRepository.save(files);
        }
    }

}

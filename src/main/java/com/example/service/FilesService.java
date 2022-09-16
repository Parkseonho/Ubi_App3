package com.example.service;

import com.example.dao.FilesRepository;
import com.example.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class FilesService {
    private final FilesRepository filesRepository;
    private final PostService postService;

    @Async
    public void upload(Post post, List<MultipartFile> multiFileList) {
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
            uploadDB(fileList, post);
            System.out.printf("성공");
        } catch (IllegalStateException | IOException e) {
            System.out.printf("실패");
            for(int i = 0; i< multiFileList.size();i++){
                new File(root + "\\" + fileList.get(i).get("changeFile")).delete();
            }
            e.printStackTrace();
        }
    }

    private void uploadDB(List<Map<String, String>> fileList, @RequestBody Post post){
        Post posts = postService.create(post);

        for(Map<String, String> file : fileList){
            com.example.domain.Files files = new com.example.domain.Files();
            files.setFilename(file.get("changeFile"));
            files.setPost(posts);
            filesRepository.save(files);
        }
    }


}

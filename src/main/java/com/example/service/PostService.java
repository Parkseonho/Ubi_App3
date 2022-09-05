package com.example.service;

import com.example.dao.PostRepository;
import com.example.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post create(String content) {
        Post post = new Post();
    /*    post.setContent(content);
        post.setCreateDate(LocalDateTime.now());
        post.setModifyDate(LocalDateTime.now());
        post.setReplyLike(0);*/
        this.postRepository.save(post);
        return post;
    }
    public List<Post> getPost() {
        return postRepository.findAll();
    }
}
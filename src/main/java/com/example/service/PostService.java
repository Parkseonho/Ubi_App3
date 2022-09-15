package com.example.service;

import com.example.dao.PostRepository;
import com.example.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post create(Post post) {
        Post newPost = new Post();
        post.setContent(post.getContent());
        post.setCreateDate(LocalDateTime.now());
        post.setModifyDate(LocalDateTime.now());
        post.setReplyLike(false);
        postRepository.save(post);
        return post;
    }
    public List<Post> getPost() {
        return postRepository.findAll();
    }

    public void Like(Integer id) {
        Post post = postRepository.findById(id).get();
        if(post.getReplyLike() == true) {
            post.setReplyLike(false);
        } else {
            post.setReplyLike(true);
        }
        this.postRepository.save(post);
    }
}
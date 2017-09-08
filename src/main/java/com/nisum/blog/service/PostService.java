package com.nisum.blog.service;

import com.nisum.blog.domain.Post;

import java.util.ArrayList;
import java.util.List;

public class PostService {
    private List<Post> postList;

    public PostService() {
        postList = new ArrayList<>();
    }

    public void add(Post post) {
        postList.add(post);
    }

    public List<Post> findAll() {
        return postList;
    }
}

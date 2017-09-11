package com.nisum.blog.dao;

import com.nisum.blog.domain.Post;
import com.nisum.blog.domain.User;

import java.util.List;

public interface PostDAO {
    int create(Post post);

    Post findById(int id);

    List<Post> findAll();

    void update(Post Post);

    void delete(int id);
}

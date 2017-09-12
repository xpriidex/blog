package com.nisum.blog.service;

import com.nisum.blog.dao.PostDAO;
import com.nisum.blog.dao.PostDAOImpl;
import com.nisum.blog.domain.Post;

import java.util.ArrayList;
import java.util.List;

public class PostService {

    private PostDAO postDAO = new PostDAOImpl();
    private List<Post> postList;

    public PostService() {
        postList = new ArrayList<>();
    }

    public List<Post> findAll() {
        return postDAO.findAll();
    }

    public List<Post> findAllByTitle(String title) {
        return postDAO.findAllByTitle(title);
    }

    public List<Post> findAllByAuthorsAlias(String alias) {
        return postDAO.findAllByAuthorsAlias(alias);
    }

    public List<Post> findAllByContent(String content) {
        return postDAO.findAllByContent(content);
    }

    public Post findById(int id) {
        return postDAO.findById(id);
    }

    public int create(Post post) {
        return postDAO.create(post);

    }
}

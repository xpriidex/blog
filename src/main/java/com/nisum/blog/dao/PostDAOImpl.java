package com.nisum.blog.dao;

import com.nisum.blog.domain.Post;
import com.nisum.blog.domain.User;

import java.util.ArrayList;
import java.util.List;

public class PostDAOImpl implements PostDAO{
    private List<Post> postList;

    public PostDAOImpl() {
        init();
    }

    private void init(){
        postList = new ArrayList<>();

        Post post1;
        Post post2;
        User user;
        post1 = new Post();
        post2 = new Post();
        user = new User();

        user.setAlias("Felipe");
        post1.setId(1);
        post1.setTitle("Narnia");
        post1.setAuthor(user);
        post1.setBody("I am post about ANDROID");

        post2.setId(2);
        post2.setTitle("Papelucho");
        post2.setAuthor(user);
        post2.setBody("I am post about AnImAlS");

        postList.add(post1);
        postList.add(post2);
    }

    @Override
    public int create(Post post) {
        return 0;
    }

    @Override
    public Post findById(int id) {
        return null;
    }

    @Override
    public List<Post> findAll() {
        return postList;
    }

    @Override
    public void update(Post post) {

    }

    @Override
    public void delete(int id) {

    }
}

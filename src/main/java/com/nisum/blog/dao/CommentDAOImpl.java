package com.nisum.blog.dao;

import com.nisum.blog.domain.Comment;
import com.sun.org.apache.xml.internal.security.Init;

import java.util.List;

public class CommentDAOImpl implements CommentDAO{

    public CommentDAOImpl() {
        init();
    }

    private void init() {
    }

    @Override
    public int create(Comment comment) {
        return 0;
    }

    @Override
    public List<Comment> findByAuthorId(int id) {
        return null;
    }

    @Override
    public List<Comment> findByAlias(String alias) {
        return null;
    }

    @Override
    public List<Comment> findByPostId(int id) {
        return null;
    }
}


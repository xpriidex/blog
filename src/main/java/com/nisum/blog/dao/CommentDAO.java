package com.nisum.blog.dao;

import com.nisum.blog.domain.Comment;

import java.util.List;

public interface CommentDAO {

    int void create(Comment comment);

    Comment findById(int id);
    List<Comment> findByAlias();


    void update(Comment comment);

    void delete(int id);
}

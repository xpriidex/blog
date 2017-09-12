package com.nisum.blog.dao;

import com.nisum.blog.domain.Comment;

import java.util.List;

public interface CommentDAO {

    int create(Comment comment);

    List<Comment> findByAuthorId(int id);

    List<Comment> findByAuthorAlias(String alias);

    List<Comment> findByPostId(int id);


    void update(Comment comment);

    void delete(int id);
}

package com.nisum.blog.service;

import com.nisum.blog.dao.CommentDAO;
import com.nisum.blog.dao.CommentDAOImpl;
import com.nisum.blog.domain.Comment;

import java.util.ArrayList;
import java.util.List;

public class CommentService {

    private CommentDAO commentDAO = new CommentDAOImpl();

    public CommentService()
    {

    }

    public List<Comment> findByAuthorId(int id) {

        List<Comment> commentByAuthor = commentDAO.findByAuthorId(id);

        return commentByAuthor;
    }

    public List<Comment> findByAuthorAlias(String alias) {

        List<Comment> commentByAuthorAlias = commentDAO.findByAuthorAlias(alias);

        return commentByAuthorAlias;

    }

    public List<Comment> findByPostId(int id) {

        List<Comment> commentByPost = commentDAO.findByPostId(id);

        return commentByPost;
    }
}

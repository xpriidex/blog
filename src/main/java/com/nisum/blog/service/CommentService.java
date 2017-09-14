package com.nisum.blog.service;

import com.nisum.blog.dao.CommentDAO;
import com.nisum.blog.dao.CommentDAOImpl;
import com.nisum.blog.domain.Comment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    private CommentDAO commentDAO = new CommentDAOImpl();

    public int create(Comment comment){

        return commentDAO.create(comment);
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

    public int deleteByAuthorId(int authorId){
        int deletedByAuthor = commentDAO.deleteByAuthorId(authorId);

        return deletedByAuthor;
    }

    public int deleteByPostId(int postId){
        int deletedByPost = commentDAO.deleteByPostId(postId);

        return deletedByPost;
    }

    public List<Comment> findAll() {

        List<Comment> comments = commentDAO.findAll();

        return comments;
    }

}

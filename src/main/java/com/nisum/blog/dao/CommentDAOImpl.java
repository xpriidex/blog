package com.nisum.blog.dao;

import com.nisum.blog.domain.Comment;
import com.nisum.blog.domain.User;
import org.joda.time.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.lang.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class CommentDAOImpl implements CommentDAO{

    private List<Comment> commentList;
    private DateTime dateTime1, dateTime2, dateTime3;

    @Autowired
    private UserDAO userDAO;

    public CommentDAOImpl() { init();}

    private void init() {
        commentList = new ArrayList<>();


        Comment comment1 = new Comment();
        comment1.setAuthorId(1);
        comment1.setPostId(1);
        comment1.setBody("Interesante");

        Comment comment2 = new Comment();
        comment2.setAuthorId(2);
        comment2.setPostId(1);
        comment2.setBody("Lindo Post");

        Comment comment3 = new Comment();
        comment3.setAuthorId(3);
        comment3.setPostId(2);
        comment3.setBody("Escribe otro");

        create(comment1);
        create(comment2);
        create(comment3);
    }

    @Override
    public int create(Comment comment)
    {
        DateTime nowLocal = DateTime.now();
        comment.setId(comment.nextAvailableId++);
        comment.setPublicationDate(nowLocal);
        commentList.add(comment);
        return comment.getId();
    }

    @Override
    public List<Comment> findByAuthorId(int id) {
        List<Comment> commentByAuthor = new ArrayList<>();

        for (int i = 0; i < commentList.size(); i++) {

            if (commentList.get(i).getAuthorId() == id) {

                commentByAuthor.add(commentList.get(i));

            }
        }
        return commentByAuthor;
    }

    @Override
    public List<Comment> findByAuthorAlias(String alias) {
        List<Comment> commentsByAuthorAlias = new ArrayList<>();
        User userFound =  userDAO.findByAlias(alias);

        if (userFound==null)
            return commentsByAuthorAlias;

        for (int i = 0; i < commentList.size(); i++) {
            if (commentList.get(i).getAuthorId()==userFound.getId())
                commentsByAuthorAlias.add(commentList.get(i));
        }

        return commentsByAuthorAlias;
    }

    @Override
    public List<Comment> findByPostId(int id) {
        List<Comment> commentByPost = new ArrayList<>();

        for (int i = 0; i < commentList.size(); i++) {

            if (commentList.get(i).getPostId() == id) {

                commentByPost.add(commentList.get(i));

            }
        }
        return commentByPost;
    }


    @Override
    public int deleteByAuthorId(int authorId){
        int deletedByAuthor = 0;

        for (int i = 0; i< commentList.size();i++){
            if(commentList.get(i).getAuthorId() == authorId){
                commentList.remove(i);
                deletedByAuthor ++;
            }
        }

        return deletedByAuthor;
    }

    @Override
    public int deleteByPostId(int postId){
        int deletedByPost = 0;

        for (int i = 0; i< commentList.size();i++){
            if(commentList.get(i).getPostId() == postId){
                commentList.remove(i);
                deletedByPost ++;
            }
        }

        return deletedByPost;
    }

    @Override
    public List<Comment> findAll() {
        return commentList;
    }

}


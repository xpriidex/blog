package com.nisum.blog.service;

import com.nisum.blog.domain.Comment;
import java.util.ArrayList;
import java.util.List;

public class CommentService {


    private List<Comment> commentList;

    public CommentService()
    {
        UserService userService = new UserService();

        commentList = new ArrayList<>();

        Comment comment1 = new Comment();
        comment1.setAuthor(userService.findById(1));
        commentList.add(comment1);
    }

    public List<Comment> findAll(){

        return commentList;
    }

    public List<Comment> findByAuthorId(int id) {

        List<Comment> commentByAuthor = new ArrayList<>();

        for (int i = 0; i < commentList.size(); i++) {

            if (commentList.get(i).getAuthor().getId() == id) {

                commentByAuthor.add(commentList.get(i));

            }
        }

        return commentByAuthor;
    }

    public List<Comment> findByAuthorAlias(String alias) {

        List<Comment> commentByAuthor = new ArrayList<>();

        for (int i = 0; i < commentList.size(); i++) {

            if (commentList.get(i).getAuthor().getAlias() == alias) {

                commentByAuthor.add(commentList.get(i));

            }
        }

        return commentByAuthor;
    }

    /*public List<Comment> findByPostId(int id) {

        List<Comment> commentByPost = new ArrayList<>();

        for (int i = 0; i < commentList.size(); i++) {

            if (commentList.get(i).getOriginalPost().) == alias) {

                commentByPost.add(commentList.get(i));

            }
        }

        return commentByAuthor;
    }*/
}

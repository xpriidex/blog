package com.nisum.blog.service;

import com.nisum.blog.domain.Comment;
import com.nisum.blog.domain.Post;

import java.util.ArrayList;
import java.util.List;

public class CommentService {


    private List<Comment> commentList;

    public CommentService()
    {
        commentList = new ArrayList<>();
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

            if (commentList.get(i).getAuthor().getAlias().equalsIgnoreCase(alias)) {

                commentByAuthor.add(commentList.get(i));

            }
        }

        return commentByAuthor;
    }

    public void add(Comment comment) {

        commentList.add(comment);
    }

    public List<Comment> findByPostId(int id) {

        List<Comment> commentByPost = new ArrayList<>();

        for (int i = 0; i < commentList.size(); i++) {

            if (commentList.get(i).getOriginalPost().getId() == id) {

                commentByPost.add(commentList.get(i));

            }
        }

        return commentByPost;
    }
}

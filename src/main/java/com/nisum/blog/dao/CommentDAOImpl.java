package com.nisum.blog.dao;

import com.nisum.blog.domain.Comment;

import java.util.ArrayList;
import java.util.List;

public class CommentDAOImpl implements CommentDAO{

    private List<Comment> commentList;

    public CommentDAOImpl() { init();}

    private void init() {
        commentList = new ArrayList<>();

        Comment comment1 = new Comment();
        comment1.setAuthorId(1);
        comment1.setPostId(1);

        Comment comment2 = new Comment();
        comment2.setAuthorId(2);
        comment2.setPostId(1);

        Comment comment3 = new Comment();
        comment3.setAuthorId(3);
        comment3.setPostId(2);

        commentList.add(comment1);
        commentList.add(comment2);
        commentList.add(comment3);
    }

    @Override
    public int create(Comment comment) {
        return 0;
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
    public List<Comment> findByAlias(String alias) {
        return null;
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
    public void update(Comment comment) {

    }

    @Override
    public void delete(int id) {

    }

}


package com.nisum.blog.service;

import com.nisum.blog.domain.Comment;
import com.nisum.blog.domain.Post;
import com.nisum.blog.domain.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsEqual.*;

public class CommentServiceTest {


    private CommentService commentService;
    private User user1;

    @Before
    public void setUp() throws Exception {
        commentService = new CommentService();
        Comment comment1 = new Comment();
        Post post1 = new Post();

        user1 = new User();
        user1.setId(1);
        user1.setAlias("Miki");

        comment1.setAuthor(user1);
        comment1.setOriginalPost(post1);
        commentService.add(comment1);

        post1.setId(1);
    }

    @Test
    public void itShouldReturnAllCommentByAuthorId() throws Exception {

        assertThat(commentService.findByAuthorId(1).size(), is(equalTo(1)));
    }

    @Test
    public void itShouldReturnAllCommentByAuthorAlias() throws Exception {

        assertThat(commentService.findByAuthorAlias("Miki").size(), is(equalTo(1)));
    }

    @Test
    public void itShouldReturnAllCommentByPostId() throws Exception {

        assertThat(commentService.findByPostId(1).size(), is(equalTo(1)));
    }

}
package com.nisum.blog.service;

import com.nisum.blog.domain.Comment;
import com.nisum.blog.domain.Post;
import com.nisum.blog.domain.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsEqual.*;

public class CommentServiceTest {

    private CommentService commentService;
    private User user1;

    @Before
    public void setUp() throws Exception {
        commentService = new CommentService();
    }

    @Test
    public void itShouldReturnAllCommentByAuthorId() throws Exception {

        List<Comment> commentList = commentService.findByAuthorId(1);
        int commentListSize = commentList.size();
        assertThat(commentListSize, is(equalTo(1)));
    }

    /*@Test
    public void itShouldReturnAllCommentByAuthorAlias() throws Exception {

        int commentListSIze = commentService.findByAuthorAlias("Miki").size();
        assertThat(commentListSIze, is(equalTo(1)));
    }

    @Test
    public void itShouldReturnAllCommentByPostId() throws Exception {

        int commentListSize = commentService.findByPostId(1).size();
        assertThat(commentListSize, is(equalTo(1)));
    }*/

}
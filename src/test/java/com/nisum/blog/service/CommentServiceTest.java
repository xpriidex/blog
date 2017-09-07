package com.nisum.blog.service;

import com.nisum.blog.domain.Comment;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsEqual.*;

public class CommentServiceTest {
    @Test
    public void itShouldReturnAllCommentByAuthorId() throws Exception {
        CommentService commentService = new CommentService();
        assertThat(commentService.findByAuthorId(1).size(), is(equalTo(1)));
    }

    @Test
    public void itShouldReturnAllCommentByAuthorAlias() throws Exception {
        CommentService commentService = new CommentService();
        assertThat(commentService.findByAuthorAlias("Miki").size(), is(equalTo(1)));
    }

    /*@Test
    public void itShouldReturnAllCommentByPostId() throws Exception {
        CommentService commentService = new CommentService();
        assertThat(commentService.findAll().size(), is(equalTo(5)));
    }*/

}
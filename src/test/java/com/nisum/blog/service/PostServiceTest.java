package com.nisum.blog.service;

import com.nisum.blog.domain.Post;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class PostServiceTest {
    private PostService postService;
    private Post post1;
    private Post post2;

    @Before
    public void setUp() throws Exception {
        postService = new PostService();
        post1 = new Post();
        post2 = new Post();

        post1.setId(1);
        post2.setId(1);

        postService.add(post1);
        postService.add(post2);
    }

    @Test
    public void itShouldReturnAllPosts() throws Exception {
        assertThat(postService.findAll().size(), is(equalTo(2)));
    }






}
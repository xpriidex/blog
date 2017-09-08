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
        //ARRENGE
        postService = new PostService();
        post1 = new Post();
        post2 = new Post();

        post1.setId(1);
        post1.setTitle("Narnia");
        post2.setId(2);
        post2.setTitle("Papelucho");

        postService.add(post1);
        postService.add(post2);
    }

    @Test
    public void itShouldReturnAllPosts() throws Exception {

        //ACT
        List<Post> result = postService.findAll();

        //ASSERT
        assertThat(result.size(), is(equalTo(2)));
        assertEquals(result.get(0).getId(),1);
        assertEquals(result.get(1).getId(),2);
    }

    @Test
    public void itShouldReturnAllPostsByTitle(){
        //ACT
        List<Post> result = postService.findAllByTitle("NaRNIA");

        //ASSERT
        assertThat(result.size(), is(equalTo(1)));
        assertEquals(result.get(0).getTitle(),"Narnia");

    }


}
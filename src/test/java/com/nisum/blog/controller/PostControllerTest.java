package com.nisum.blog.controller;

import com.nisum.blog.domain.Post;
import com.nisum.blog.service.PostService;
import com.nisum.blog.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.codehaus.groovy.runtime.DefaultGroovyMethods.any;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class PostControllerTest {
    private MockMvc mockMvc;
    private Post post;
    private List<Post> postList;

    @Mock
    private PostService postService;

    @Mock
    private UserService userService;

    @InjectMocks
    private PostController postController;

    @Before
    public void setUp() throws Exception {
        post = new Post();
        postList = new ArrayList<>();

        post.setId(1);
        post.setTitle("Narnia");
        post.setAuthorId(1);
        post.setBody("Narnia");

        postList.add(post);

        mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
    }

    @Test
    public void checkFindAllPost() throws Exception {
        when(postService.findAll()).thenReturn(postList);

        mockMvc.perform(get("/posts/"))
                .andExpect(status().isOk())
                .andExpect(view().name("posts/list"))
                .andExpect(model().attribute("posts",postList));

        verify(postService).findAll();

    }

    @Test
    public void createView() throws Exception {
        Post post1 = new Post();

        mockMvc.perform(get("/posts/create/"))
                .andExpect(status().isOk())
                .andExpect(view().name("posts/create"))
                .andExpect(model().attribute("post",post1));
    }

    @Test
    public void checkCreate() throws Exception {

        mockMvc.perform(
                post("/posts/create/")).
                andExpect(redirectedUrl("/posts/"));

        //verify(postService).create(any(Post.class));


    }

    @Test
    public void checkRead() throws Exception {
        when(postService.findById(1)).thenReturn(post);

        mockMvc.perform(get("/posts/read/{id}",1))
                .andExpect(status().isOk())
                .andExpect(view().name("posts/detail"))
                .andExpect(model().attribute("post",post));

        verify(postService).findById(1);
    }

    @Test
    public void checkUpdateView() throws Exception {
        when(postService.findById(1)).thenReturn(post);

        mockMvc.perform(get("/posts/update/{id}",1))
                .andExpect(status().isOk())
                .andExpect(view().name("posts/update"))
                .andExpect(model().attribute("post",post));

        verify(postService).findById(1);

    }

    /*@Test
    public void checkFindAllPostByAlias() throws Exception {
        when(postService.findAllByAuthorsAlias("pali")).thenReturn(postList);

        mockMvc.perform(post("/posts/findbyalias"))
                .andExpect(status().isOk())
                .andExpect(view().name("posts/list"))
                .andExpect(model().attribute("posts",postList));

        verify(postService).findAllByAuthorsAlias(anyString());
    }*/

    @Test
    public void checkDelete() throws Exception {
        mockMvc.perform(get("/posts/delete/{id}",1))
                .andExpect(redirectedUrl("/posts/"));

        verify(postService).delete(1);
    }

}
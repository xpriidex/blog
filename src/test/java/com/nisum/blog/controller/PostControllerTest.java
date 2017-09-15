package com.nisum.blog.controller;

import com.nisum.blog.domain.Post;
import com.nisum.blog.service.PostService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.ResponseEntity.status;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(MockitoJUnitRunner.class)
public class PostControllerTest {
    private MockMvc mockMvc;
    private Post post1;
    private Post post2;
    private List<Post> postList;

    @Mock
    private PostService postService;

    @InjectMocks
    private PostController postController;

    @Before
    public void setUp() throws Exception {
        postList = new ArrayList<>();
        post1 = new Post();
        post2 = new Post();

        post1.setId(1);
        post1.setTitle("Narnia");
        post1.setAuthorId(1);
        post1.setBody("I am post about ANDROID");

        post2.setId(2);
        post2.setTitle("Papelucho");
        post1.setAuthorId(1);
        post2.setBody("I am post about AnImAlS");

        postList.add(post1);
        postList.add(post2);

        mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
    }

    @Test
    public void shouldReturnAllPost() throws Exception {
       // when(postService.findAll()).thenReturn(postList);


    }

}
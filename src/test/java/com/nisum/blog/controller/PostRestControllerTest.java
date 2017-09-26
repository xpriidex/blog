package com.nisum.blog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nisum.blog.domain.Post;
import com.nisum.blog.service.PostService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class PostRestControllerTest {
    private ObjectMapper mapper = new ObjectMapper();
    private MockMvc mockMvc;
    private Post post1;
    private Post post2;
    private List<Post> postList;

    @Mock
    private PostService postService;

    @InjectMocks
    private PostRestController postController;

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
        post2.setAuthorId(1);
        post2.setBody("I am post about AnImAlS");

        postList.add(post1);
        postList.add(post2);

        mockMvc = MockMvcBuilders
                .standaloneSetup(postController)
                .build();
    }

    @Test
    public void shouldReturnAllPost() throws Exception {
        when(postService.findAll()).thenReturn(postList);

        mockMvc.perform(get("/api/posts/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Narnia")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].title", is("Papelucho")));

        verify(postService, times(1)).findAll();
        verifyNoMoreInteractions(postService);
    }

    @Test
    public void shouldReturnAPostById() throws Exception {
        when(postService.findById(1)).thenReturn(post1);

        mockMvc.perform(get("/api/posts/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Narnia")));

        verify(postService, times(1)).findById(1);
        verifyNoMoreInteractions(postService);
    }

    @Test
    public void shouldReturnIdWhenCreateANewPost() throws Exception {
        // when(postService.exists(post1)).thenReturn(false);
        //doNothing().when(postService).create(post1)
        when(postService.create(post1)).thenReturn(1);
        String json = mapper.writer().writeValueAsString(post1);

        mockMvc.perform(
                post("/api/posts/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        //verify(postService, times(1)).exists(user);
        verify(postService, times(1)).create(post1);
        verifyNoMoreInteractions(postService);
    }

    // TODO: 21-09-17 gitk
    @Test
    public void shouldReturnIdWhenUpdateAPost() throws Exception {
        when(postService.update(post1)).thenReturn(1);
        //when(postService.findById(post1.getId())).thenReturn(post1);

        String json = mapper.writer().writeValueAsString(post1);


        mockMvc.perform(
                put("/api/posts/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        //verify(postService, times(1)).findById(post1.getId());
        verify(postService, times(1)).update(post1);
        verifyNoMoreInteractions(postService);
    }

    @Test
    public void checkDeleteAPost() throws Exception {
        //when(.postService(post1.getId())).thenReturn(post1);
        doNothing().when(postService).delete(post1.getId());

        mockMvc.perform(
                delete("/api/posts/{id}", post1.getId()))
                .andExpect(status().isOk());
// TODO: 21-09-17 revisar delete 
        //verify(postService, times(1)).findById(post1.getId());
        verify(postService, times(1)).delete(post1.getId());
        verifyNoMoreInteractions(postService);
    }

    @Test
    public void shouldReturnAllPostByContent() throws Exception {
        List<Post> posts = new ArrayList<>();
        posts.add(post1);
        when(postService.findAllByContent("ANDroid")).thenReturn(posts);

        mockMvc.perform(get("/api/posts/content/{content}","ANDroid"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Narnia")))
                .andExpect(jsonPath("$[0].body", is("I am post about ANDROID")));

        verify(postService, times(1)).findAllByContent("ANDroid");
        verifyNoMoreInteractions(postService);
    }

    @Test
    public void shouldReturnAllPostByTitle() throws Exception {
        List<Post> posts = new ArrayList<>();
        posts.add(post1);
        when(postService.findAllByTitle("NarniA")).thenReturn(posts);

        mockMvc.perform(get("/api/posts/title/{title}","NarniA"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Narnia")));

        verify(postService, times(1)).findAllByTitle("NarniA");
        verifyNoMoreInteractions(postService);
    }

    @Test
    public void shouldReturnAllPostByAlias() throws Exception {
        List<Post> posts = new ArrayList<>();
        posts.add(post1);
        when(postService.findAllByAuthorsAlias("Pali")).thenReturn(posts);

        mockMvc.perform(get("/api/posts/alias/{alias}","Pali"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Narnia")));

        verify(postService, times(1)).findAllByAuthorsAlias("Pali");
        verifyNoMoreInteractions(postService);
    }

}
package com.nisum.blog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nisum.blog.domain.Comment;
import com.nisum.blog.service.CommentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class CommentRestControllerTest {
    private ObjectMapper mapper = new ObjectMapper();
    private MockMvc mockMvc;
    private Comment comment1;
    private Comment comment2;
    private List<Comment> commentList;

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentRestController commentRestController;

    @Before
    public void setUp() throws Exception {
        commentList = new ArrayList<>();
        comment1 = new Comment();
        comment2 = new Comment();

        comment1.setId(1);
        comment1.setBody("comment about this post");
        comment1.setAuthorId(1);
        comment1.setPostId(1);

        comment2.setId(2);
        comment2.setBody("another comment about this post");
        comment2.setAuthorId(2);
        comment2.setPostId(2);

        commentList.add(comment1);
        commentList.add(comment2);

        mockMvc = MockMvcBuilders.standaloneSetup(commentRestController).build();
    }

    @Test
    public void shouldReturnAllComments() throws Exception {
        when(commentService.findAll()).thenReturn(commentList);

        mockMvc.perform(get("/api/comments/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].body", is("comment about this post")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].body", is("another comment about this post")));

        verify(commentService, times(1)).findAll();
        verifyNoMoreInteractions(commentService);
    }

    /*@Test
    public void shouldReturnCommentByAuthorId() throws Exception {
        List<Comment> commentList = new ArrayList<>();
        commentList.add(comment1);

        when(commentService.findByAuthorId(1)).thenReturn(commentList);

        mockMvc.perform(get("/api/comments/findbyauthorid/{authorId}",1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.authorId", is(1)))
                .andExpect(jsonPath("$.body", is("comment about this post")));

        verify(commentService, times(1)).findByAuthorId(1);
        verifyNoMoreInteractions(commentService);
    }*/
}
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class CommentRestControllerTest {
    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentRestController commentRestController;

    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(commentRestController).build();
    }

    @Test
    public void shouldFindAllComments() throws Exception {
        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment());
        comments.add(new Comment());
        comments.add(new Comment());

        when(commentService.findAll()).thenReturn(comments);

        MvcResult commentsResponse = mockMvc.perform(get("/api/comments/"))
                .andExpect(status().isOk())
                .andReturn();

        String contentAsString = commentsResponse.getResponse().getContentAsString();
        System.out.println(contentAsString);

        List commentsList = mapper.readValue(contentAsString, List.class);
        assertThat(commentsList.size(),is(comments.size()));
        verify(commentService).findAll();

        System.out.println(commentsList.get(0).getClass().getName());
    }
}
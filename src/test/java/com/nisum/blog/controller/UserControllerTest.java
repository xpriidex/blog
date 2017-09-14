package com.nisum.blog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.nisum.blog.domain.User;
import com.nisum.blog.service.CommentService;
import com.nisum.blog.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc controllerMockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
        controllerMockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void shouldFindAllUsers() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());

        when(userService.findAll()).thenReturn(users);

        MvcResult usersResponse = controllerMockMvc.perform(get("/api/users/"))
                .andExpect(status().isOk())
                .andReturn();

        String contentAsString = usersResponse.getResponse().getContentAsString();
        System.out.println(contentAsString);

        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, User.class);
        List<User> usersList = mapper.readValue(contentAsString, collectionType);
        System.out.println(usersList.get(0).getClass().getName());
        //parametizedType
        System.out.println(usersList.size());

        assertThat(usersList.size(),is(users.size()));

        verify(userService).findAll();
    }

    @Test
    public void shouldFindById() throws Exception {
        User user = new User();
        user.setId(1);
        user.setFirstName("Mari");

        when(userService.findById(1)).thenReturn(user);

        MvcResult usersResponse = controllerMockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andReturn();

        String contentAsString = usersResponse.getResponse().getContentAsString();
        System.out.println(contentAsString);

        User userFound = mapper.readValue(contentAsString, User.class);
        System.out.println(userFound.getId());
        System.out.println(userFound.getFirstName());

        assertThat(userFound.getId(),is(1));

        verify(userService).findById(1);
    }

    @Test
    public void shouldDeleteById() throws Exception {

        controllerMockMvc.perform(delete("/api/users/5"))
                .andExpect(status().isOk());

        verify(userService).delete(5);
    }
}
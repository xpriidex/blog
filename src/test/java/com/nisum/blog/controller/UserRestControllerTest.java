package com.nisum.blog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.nisum.blog.domain.User;
import com.nisum.blog.service.UserService;
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
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class UserRestControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserRestController userRestController;

    private MockMvc userRestControllerMockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
        userRestControllerMockMvc = MockMvcBuilders.standaloneSetup(userRestController).build();
    }

    @Test
    public void shouldFindAllUsers() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());

        when(userService.findAll()).thenReturn(users);

        MvcResult usersResponse = userRestControllerMockMvc.perform(get("/api/users/"))
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

        MvcResult usersResponse = userRestControllerMockMvc.perform(get("/api/users/1"))
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
    public void shouldFindByAlias() throws Exception {
        User user = new User();
        user.setId(4);
        user.setAlias("JuneSky");

        when(userService.findByAlias("junesky")).thenReturn(user);

        MvcResult usersResponse = userRestControllerMockMvc.perform(get("/api/users/alias/junesky"))
                .andExpect(status().isOk())
                .andReturn();

        String contentAsString = usersResponse.getResponse().getContentAsString();
        System.out.println(contentAsString);

        User userFound = mapper.readValue(contentAsString, User.class);
        System.out.println(userFound.getId());
        System.out.println(userFound.getAlias());

        assertThat(userFound.getId(),is(4));

        verify(userService).findByAlias("junesky");
    }

    @Test
    public void shouldFindByEmail() throws Exception {
        User user = new User();
        user.setId(4);
        user.setAlias("Miki");
        user.setEmail("myurjevic@gmail.com");

        when(userService.findByEmail("myurjevic@gmail.com")).thenReturn(user);

        MvcResult usersResponse = userRestControllerMockMvc.perform(get("/api/users/email/myurjevic@gmail.com/"))
                .andExpect(status().isOk())
                .andReturn();

        String contentAsString = usersResponse.getResponse().getContentAsString();
        System.out.println("CONTENT: " + contentAsString);

        User userFound = mapper.readValue(contentAsString, User.class);
        System.out.println(userFound.getId());
        System.out.println(userFound.getAlias());

        assertThat(userFound.getId(),is(4));

        verify(userService).findByEmail("myurjevic@gmail.com");
    }

    @Test
    public void shouldFindByFirstName() throws Exception {
        User user = new User();
        user.setId(4);
        user.setFirstName("June");
        List users = new ArrayList();
        users.add(user);

        when(userService.findByFirstName("June")).thenReturn(users);

        MvcResult usersResponse = userRestControllerMockMvc.perform(get("/api/users/firstname/June"))
                .andExpect(status().isOk())
                .andReturn();

        String contentAsString = usersResponse.getResponse().getContentAsString();
        System.out.println(contentAsString);

        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, User.class);
        List<User> usersFound = mapper.readValue(contentAsString, collectionType);
        System.out.println(usersFound.get(0).getFirstName());

        assertThat(usersFound.get(0).getId(),is(4));

        verify(userService).findByFirstName("June");
    }

    @Test
    public void shouldFindByLastName() throws Exception {
        User user = new User();
        user.setId(4);
        user.setLastName("Jennings");
        List users = new ArrayList();
        users.add(user);

        when(userService.findByLastName("Jennings")).thenReturn(users);

        MvcResult usersResponse = userRestControllerMockMvc.perform(get("/api/users/lastname/Jennings"))
                .andExpect(status().isOk())
                .andReturn();

        String contentAsString = usersResponse.getResponse().getContentAsString();
        System.out.println(contentAsString);

        CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, User.class);
        List<User> usersFound = mapper.readValue(contentAsString, collectionType);
        System.out.println(usersFound.get(0).getLastName());

        assertThat(usersFound.get(0).getId(),is(4));

        verify(userService).findByLastName("Jennings");
    }

//    @Test
//    public void shouldUpdateUserByFirstName() throws Exception {
//        User user = new User();
//        user.setId(1);
//        user.setFirstName("Mona");
//
//        String json = mapper.writer().writeValueAsString(user);
//
//        userRestControllerMockMvc.perform(put("/api/users/first_name")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json))
//                .andExpect(status().isOk());
//
//        verify(userService).updateFirstName(1, "Mona");
//    }

//    @Test
//    public void shouldUpdateUserByLastName() throws Exception {
//        User user = new User();
//        user.setId(1);
//        user.setLastName("WhatWhat");
//
//        String json = mapper.writer().writeValueAsString(user);
//
//        userRestControllerMockMvc.perform(put("/api/users/last_name")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json))
//                .andExpect(status().isOk());
//
//        verify(userService).updateLastName(1, "WhatWhat");
//    }

//    @Test
//    public void shouldUpdateUserByAlias() throws Exception {
//        User user = new User();
//        user.setId(1);
//        user.setAlias("Marin");
//
//        String json = mapper.writer().writeValueAsString(user);
//
//        userRestControllerMockMvc.perform(put("/api/users/alias")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json))
//                .andExpect(status().isOk());
//
//        verify(userService).updateAlias(1, "Marin");
//    }

//    @Test
//    public void shouldUpdateUserByEmail() throws Exception {
//        User user = new User();
//        user.setId(1);
//        user.setEmail("strink@gmail.com");
//
//        String json = mapper.writer().writeValueAsString(user);
//
//        userRestControllerMockMvc.perform(put("/api/users/email")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json))
//                .andExpect(status().isOk());
//
//        verify(userService).updateEmail(1, "strink@gmail.com");
//    }

    @Test
    public void shouldCreateNewUser() throws Exception {
        User user = new User();
        user.setEmail("min@gmail.com");
        user.setAlias("Tintin");

        when(userService.create(user)).thenReturn(4);

        String json = mapper.writer().writeValueAsString(user);

        userRestControllerMockMvc.perform(post("/api/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());

        verify(userService).create(user);
    }

    @Test
    public void shouldDeleteById() throws Exception {
        userRestControllerMockMvc.perform(delete("/api/users/5"))
                .andExpect(status().isOk())
                .andExpect(content().string("deleted"));

        verify(userService).delete(5);
    }
}
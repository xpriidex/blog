package com.nisum.blog.service;

import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsEqual.*;
import static org.junit.Assert.*;

import com.nisum.blog.domain.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UserServiceTest {

    //Todos los usuarios
    @Test
    public void itShouldReturnAllUsers() throws Exception {
        UserService userService = new UserService();
        assertThat(userService.findAll().size(), is(equalTo(3)));
    }

    //User by Id
    @Test
    public void itShouldReturnOneUserById() throws Exception {
        UserService userService = new UserService();
        assertThat(userService.findById(3).getId(), is(equalTo(3)));
    }

    //Users by Alias
    @Test
    public void itShouldReturnUserByAlias() throws Exception {
        UserService userService = new UserService();
        assertThat(userService.findByAlias("Miki").getId(), is(equalTo(1)));
    }

    //Users by Name
    @Test
    public void itShouldReturnUsersByFirstName() throws Exception {
        UserService userService = new UserService();
        List<User> usersFound = userService.findByFirstName("Juan");
        assertThat(usersFound.size(), is(equalTo(1)));
    }

    //Users by LastName
    @Test
    public void itShouldReturnUsersByLastName() throws Exception {
        UserService userService = new UserService();
        List<User> usersFound = userService.findByLastName("Yurjevic");
        assertThat(usersFound.size(), is(equalTo(1)));
    }

    //User by email
    @Test
    public void itShouldReturnUsersByEmail() throws Exception {
        UserService userService = new UserService();
        User user = userService.findByEmail("myurjevic@gmail.com");
        assertThat(user.getId(), is(equalTo(1)));
    }






}
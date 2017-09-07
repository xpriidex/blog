package com.nisum.blog.service;

import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsEqual.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class UserServiceTest {

    @Test
    public void itShouldReturnAllUsers() throws Exception {
        UserService userService = new UserService();
        assertThat(userService.findAll().size(), is(equalTo(5)));
    }

    @Test
    public void itShouldReturnOneUserById() throws Exception {
        UserService userService = new UserService();
        assertThat(userService.findById("3").getId(), is(equalTo("3")));
    }

}
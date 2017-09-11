package com.nisum.blog.service;

import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsEqual.*;
import static org.junit.Assert.*;

import com.nisum.blog.domain.Comment;
import com.nisum.blog.domain.Post;
import com.nisum.blog.domain.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UserServiceTest {

    private static final String MIKI = "Miki";
    private UserService userService;
    private User user1;
    private User user2;
    private User user3;

    @Before
    public void setUp() throws Exception {
        userService = new UserService();

        User user1 = new User();
        user1.setId(1);
        user1.setFirstName("Macarena");
        user1.setLastName("Yurjevic");
        user1.setAlias(MIKI);
        user1.setBio("Wazuup");
        user1.setEmail("myurjevic@gmail.com");
        user1.setImage("http://miFotoFeliz");
        user1.setMyPosts( new ArrayList<Post>());
        user1.setMyComments(new ArrayList<Comment>());

        User user2 = new User();
        user2.setId(2);
        user2.setFirstName("Juan");
        user2.setLastName("Lopez");
        user2.setAlias("Juanes");
        user2.setBio("Programar, comer, dormir, repeat");
        user2.setEmail("juan4eva@gmail.com");
        user2.setImage("http://fotoViaje");
        user2.setMyPosts( new ArrayList<Post>());
        user2.setMyComments(new ArrayList<Comment>());

        User user3 = new User();
        user3.setId(3);
        user3.setFirstName("Paulina");
        user3.setLastName("Gamboa");
        user3.setAlias("Pali");
        user3.setBio("Dispersion");
        user3.setEmail("pgamboa@nisum.com");
        user3.setImage("http://selfieTrekking");
        user3.setMyPosts( new ArrayList<Post>());
        user3.setMyComments(new ArrayList<Comment>());;

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
    }

    //Todos los usuarios
    @Test
    public void shouldReturnAllUsers() throws Exception {
        int userListSize = userService.findAll().size();

        assertThat(userListSize, is(equalTo(3)));
    }

    //User by Id
    @Test
    public void shouldReturnOneUserById() throws Exception {
        int userId = userService.findById(3).getId();

        assertThat(userId, is(equalTo(3)));
    }

    //Users by Alias
    @Test
    public void shouldReturnUserByAlias() throws Exception {
        int userId = userService.findByAlias(MIKI).getId();

        assertThat(userId, is(equalTo(1)));
    }

    //Users by Name
    @Test
    public void shouldReturnUsersByFirstName() throws Exception {
        List<User> usersFound = userService.findByFirstName("Juan");

        assertThat(usersFound.size(), is(equalTo(1)));
    }

    //Users by LastName. Case insensitive search
    @Test
    public void shouldReturnUsersByLastName() throws Exception {
        List<User> usersFound = userService.findByLastName("YURJEVIC");

        assertThat(usersFound.size(), is(equalTo(1)));
    }

    //User by email. Case insensitive search
    @Test
    public void shouldReturnUsersByEmail() throws Exception {
        User user = userService.findByEmail("myurjevic@gmail.COM");

        assertThat(user.getId(), is(equalTo(1)));
    }

    //shouldFailOn___ or shouldFailW
}
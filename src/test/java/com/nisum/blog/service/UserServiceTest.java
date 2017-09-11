package com.nisum.blog.service;

import com.nisum.blog.dao.UserDAO;
import com.nisum.blog.domain.Comment;
import com.nisum.blog.domain.Post;
import com.nisum.blog.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private static final String MIKI = "Miki";
    private User user1;
    private User user2;
    private User user3;
    private List<User> users;

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserService userService;


    @Before
    public void setUp() throws Exception {
        //userService = new UserService();
        users = new ArrayList<>();


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

        users.add(user1);
        users.add(user2);
        users.add(user3);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
    }

    //Todos los usuarios
    @Test
    public void shouldReturnAllUsers() throws Exception {
        when(userDAO.findAll()).thenReturn(users);

        int userListSize = userService.findAll().size();

        assertThat(userListSize, is(equalTo(3)));
        verify(userDAO,times(1)).findAll();
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
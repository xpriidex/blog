package com.nisum.blog.service;

import com.nisum.blog.dao.UserDAO;
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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

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
        users = new ArrayList<>();

        user1 = new User();
        user1.setId(1);
        user1.setFirstName("Macarena");
        user1.setLastName("Yurjevic");
        user1.setAlias("Miki");
        user1.setBio("Wazuup");
        user1.setEmail("myurjevic@gmail.com");
        user1.setImage("http://miFotoFeliz");

        user2 = new User();
        user2.setId(2);
        user2.setFirstName("Juan");
        user2.setLastName("Lopez");
        user2.setAlias("Juanes");
        user2.setBio("Programar, comer, dormir, repeat");
        user2.setEmail("juan4eva@gmail.com");
        user2.setImage("http://fotoViaje");

        user3 = new User();
        user3.setId(3);
        user3.setFirstName("Paulina");
        user3.setLastName("Gamboa");
        user3.setAlias("Pali");
        user3.setBio("Dispersion");
        user3.setEmail("pgamboa@nisum.com");
        user3.setImage("http://selfieTrekking");

        users.add(user1);
        users.add(user2);
        users.add(user3);
    }

    @Test
    public void shouldReturnAllUsers() throws Exception {
        when(userDAO.findAll()).thenReturn(users);
        String expectedAlias = "Miki";

        List<User> result = userService.findAll();

        assertThat(result.size(), is(equalTo(3)));
        assertEquals(expectedAlias.toLowerCase(), result.get(0).getAlias().toLowerCase());
        verify(userDAO).findAll();
    }

    @Test
    public void shouldReturnUserById() throws Exception {
        when(userDAO.findById(0)).thenReturn(users.get(0));
        String expectedAlias = "Miki";

        String userAlias = userService.findById(0).getAlias();

        assertThat(userAlias, is(equalTo(expectedAlias)));
        verify(userDAO).findById(0);
    }

    @Test
    public void shouldReturnUserByAlias() throws Exception {
        String MIKI = "Miki";
        when(userDAO.findByAlias(MIKI)).thenReturn(user1);

        int userId = userService.findByAlias(MIKI).getId();

        assertThat(userId, is(equalTo(1)));
        verify(userDAO).findByAlias(MIKI);
    }

    @Test
    public void shouldReturnUsersByFirstName() throws Exception {
        String JUAN = "Juan";
        List<User> juanUsers = new ArrayList<>();
        juanUsers.add(user2);
        when(userDAO.findByFirstName(JUAN)).thenReturn(juanUsers);

        List<User> usersFound = userService.findByFirstName(JUAN);
        int userListSize = usersFound.size();

        assertThat(userListSize, is(equalTo(1)));
        assertEquals(usersFound.get(0).getFirstName().toLowerCase(), JUAN.toLowerCase());
        verify(userDAO).findByFirstName(JUAN);
    }

    @Test
    public void shouldReturnUsersByLastName() throws Exception {
        String YURJEVIC = "YURJEVIC";
        List<User> yurjevicUsers = new ArrayList<>();
        yurjevicUsers.add(user1);
        when(userDAO.findByLastName(YURJEVIC)).thenReturn(yurjevicUsers);

        List<User> usersFound = userService.findByLastName(YURJEVIC);

        assertThat(usersFound.size(), is(equalTo(1)));
        assertEquals(usersFound.get(0).getLastName().toLowerCase(), YURJEVIC.toLowerCase());
        verify(userDAO).findByLastName(YURJEVIC);
    }

    @Test
    public void shouldReturnUserByEmail() throws Exception {
        String email = "myurjevic@gmail.COM";
        when(userDAO.findByEmail(email)).thenReturn(user1);

        User userFound = userService.findByEmail(email);

        assertThat(userFound.getId(), is(equalTo(1)));
        assertEquals(userFound.getEmail().toLowerCase(), email.toLowerCase());
        verify(userDAO).findByEmail(email);
    }

    @Test
    public void shouldReturnIdWhenCreatingNewUser() throws Exception{

        User newUser = new User();
        newUser.setId(4);
        newUser.setAlias("Honey");
        newUser.setEmail("joni@gmail.com");
        newUser.setFirstName("Jana");
        newUser.setLastName("Jensen");
        newUser.setBio("Jamming to name of the Lord");
        newUser.setImage("honi.jpg");

        //Arrange
        when(userDAO.findByAlias("Honey")).thenReturn(null);
        when(userDAO.findByEmail("joni@gmail.com")).thenReturn(null);
        when(userDAO.create(newUser)).thenReturn(3);

        //Act
        int result = userService.create(newUser);

        //Assert
        assertEquals(result, 3);
        verify(userDAO).create(newUser);
    }

    @Test
    public void shouldReturnDeleteUser() throws Exception{
        int userId = 1;
        //Arrange
        when(userDAO.delete(userId)).thenReturn(1);

        //Act
        int result = userService.delete(userId);

        //Assert
        assertEquals(result, 1);
        verify(userDAO).delete(userId);
    }

    //shouldFailOn___ or shouldFailW
}
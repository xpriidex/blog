package com.nisum.blog.dao;

import com.nisum.blog.domain.Comment;
import com.nisum.blog.domain.Post;
import com.nisum.blog.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private List<User> userList;

    public UserDAOImpl() {
        init();
    }

    private void init() {
        userList = new ArrayList<>();

        User user1 = new User();
        user1.setId(1);
        user1.setFirstName("Macarena");
        user1.setLastName("Yurjevic");
        user1.setAlias("Miki");
        user1.setBio("Wazuup");
        user1.setEmail("myurjevic@gmail.com");
        user1.setImage("http://miFotoFeliz");
        user1.setMyPosts(new ArrayList<Post>());
        user1.setMyComments(new ArrayList<Comment>());

        User user2 = new User();
        user2.setId(2);
        user2.setFirstName("Juan");
        user2.setLastName("Lopez");
        user2.setAlias("Juanes");
        user2.setBio("Programar, comer, dormir, repeat");
        user2.setEmail("juan4eva@gmail.com");
        user2.setImage("http://fotoViaje");
        user2.setMyPosts(new ArrayList<Post>());
        user2.setMyComments(new ArrayList<Comment>());

        User user3 = new User();
        user3.setId(3);
        user3.setFirstName("Paulina");
        user3.setLastName("Gamboa");
        user3.setAlias("Pali");
        user3.setBio("Dispersion");
        user3.setEmail("pgamboa@nisum.com");
        user3.setImage("http://selfieTrekking");
        user3.setMyPosts(new ArrayList<Post>());
        user3.setMyComments(new ArrayList<Comment>());

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
    }

    @Override
    public int create(User user) {
        return 0;
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return userList;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(int id) {

    }
}

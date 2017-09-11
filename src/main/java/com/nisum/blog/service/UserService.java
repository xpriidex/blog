package com.nisum.blog.service;

import com.nisum.blog.dao.UserDAO;
import com.nisum.blog.dao.UserDAOImpl;
import com.nisum.blog.domain.Comment;
import com.nisum.blog.domain.Post;
import com.nisum.blog.domain.User;

import java.util.*;

public class UserService {

    private List<User> userList;

    private UserDAO userDAO = new UserDAOImpl();

    public UserService() {
        userList = new ArrayList<>();
    }

    public List<User> findAll() {
        userList = userDAO.findAll();

        return userList;
    }

    public User findById(int id) {
        User userFound = userDAO.findById(id);

        return userFound;
    }

    public User findByAlias(String alias) {
        User userFound = userDAO.findByAlias(alias);

        return userFound;
    }

    public List<User> findByFirstName(String firstName) {
        List<User> usersFound = userDAO.findByFirstName(firstName);

        return usersFound;
    }

    public List<User> findByLastName(String lastName) {
        List<User> usersFound = userDAO.findByLastName(lastName);

        return usersFound;
    }

    public User findByEmail(String email) {
        User userFound = userDAO.findByEmail(email);

        return userFound;
    }

    public void add(User user) {
        userList.add(user);
    }
}

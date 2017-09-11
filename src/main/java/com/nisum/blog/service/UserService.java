package com.nisum.blog.service;

import com.nisum.blog.dao.UserDAO;
import com.nisum.blog.dao.UserDAOImpl;
import com.nisum.blog.domain.Comment;
import com.nisum.blog.domain.Post;
import com.nisum.blog.domain.User;
import com.nisum.blog.service.exceptions.UserNotFoundException;

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

    public User findById(int id) throws UserNotFoundException {
        User userFound = null;

        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId() == id) {
                userFound = userList.get(i);
                break;
            }
        }

        if (userFound == null) {
            throw new UserNotFoundException();
        }

        return userFound;
    }

    public User findByAlias(String alias) throws UserNotFoundException {
        User userFound = null;

        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getAlias().equalsIgnoreCase(alias)) {
                userFound = userList.get(i);
                break;
            }
        }

        if (userFound == null) {
            throw new UserNotFoundException();
        }

        return userFound;
    }

    public List<User> findByFirstName(String firstName) throws UserNotFoundException {
        List<User> usersFound = new ArrayList<>();

        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getFirstName() == firstName) {
                usersFound.add(userList.get(i));
            }
        }

        if (usersFound.isEmpty()) {
            throw new UserNotFoundException();
        }

        return usersFound;
    }

    public List<User> findByLastName(String lastName) throws UserNotFoundException {
        List<User> usersFound = new ArrayList<>();

        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getLastName().equalsIgnoreCase(lastName)) {
                usersFound.add(userList.get(i));
            }
        }

        if (usersFound.isEmpty()) {
            throw new UserNotFoundException();
        }

        return usersFound;
    }

    public User findByEmail(String email) throws UserNotFoundException {
        User userFound = null;

        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getEmail().equalsIgnoreCase(email)) {
                userFound = userList.get(i);
                break;
            }
        }

        if (userFound == null) {
            throw new UserNotFoundException();
        }

        return userFound;
    }

    public void add(User user) {
        userList.add(user);
    }
}

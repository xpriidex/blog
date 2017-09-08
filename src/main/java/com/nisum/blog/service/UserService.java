package com.nisum.blog.service;

import com.nisum.blog.domain.Comment;
import com.nisum.blog.domain.Post;
import com.nisum.blog.domain.User;

import java.util.*;

public class UserService {

    private List<User> userList;

    public UserService() {
        userList = new ArrayList<>();
    }

    public List<User> findAll() {
        return userList;
    }

    public User findById(int id) {
        User userFound = new User();

        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId() == id) {
                userFound = userList.get(i);
                break;
            }
        }

        return userFound;
    }

    public User findByAlias(String alias) {
        User userFound = new User();

        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getAlias() == alias) {
                userFound = userList.get(i);
                break;
            }
        }

        return userFound;
    }

    public List<User> findByFirstName(String firstName) {
        List<User> usersFound = new ArrayList<>();

        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getFirstName() == firstName) {
                usersFound.add(userList.get(i));
            }
        }

        return usersFound;
    }

    public List<User> findByLastName(String lastName) {
        List<User> usersFound = new ArrayList<>();

        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getLastName() == lastName) {
                usersFound.add(userList.get(i));
            }
        }

        return usersFound;
    }

    public User findByEmail(String email) {
        User user = new User();

        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getEmail() == email) {
                user = userList.get(i);
                break;
            }
        }

        return user;
    }

    public void add(User user) {
        userList.add(user);
    }
}

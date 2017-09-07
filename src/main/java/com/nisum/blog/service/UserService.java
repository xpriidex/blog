package com.nisum.blog.service;

import com.nisum.blog.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private List<User> userList;

    public UserService()
    {
        userList = new ArrayList<>();
    }

    public List<User> findAll(){
        return userList;
    }

    public User findById(String id)
    {
        User userFound = new User();

        for (int i = 0; i < userList.size(); i++)
        {
            if (userList.get(i).getId() == id)
            {
                userFound = userList.get(i);
                break;
            }
        }

        return userFound;
    }
}

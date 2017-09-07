package com.nisum.blog.service;

import com.nisum.blog.domain.Comment;
import com.nisum.blog.domain.Post;
import com.nisum.blog.domain.User;

import java.util.*;

public class UserService {

    private List<User> userList;

    public UserService()
    {
        userList = new ArrayList<>();
        userList.add(new User(1, "Macarena", "Yurjevic", "Miki", "Wazuuup", "myurjevic@gmail.com", "http://miFotoFeliz", new ArrayList<Post>(),new ArrayList<Comment>()));
        userList.add(new User(2, "Juan", "Lopez", "Juanes", "Programar, comer, dormir, repeat", "juan4eva@gmail.com", "http://fotoViaje", new ArrayList<Post>(),new ArrayList<Comment>()));
        userList.add(new User(3, "Paulina", "Gamboa", "Pali", "Dispersion", "pgamboa@nisum.com", "http://selfieTrekking", new ArrayList<Post>(),new ArrayList<Comment>()));
    }

    public List<User> findAll(){
        return userList;
    }

    public User findById(int id)
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

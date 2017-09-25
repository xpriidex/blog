package com.nisum.blog.service;

import com.nisum.blog.dao.UserDAO;
import com.nisum.blog.dao.UserDAOImpl;
import com.nisum.blog.domain.Comment;
import com.nisum.blog.domain.Post;
import com.nisum.blog.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private List<User> userList;

    @Autowired
    @Qualifier("userDAOJdbc")
    private UserDAO userDAO;

    public UserService() {
        userList = new ArrayList<>();
    }

    public int create(User user) {
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            return -1;
        }

        if (userDAO.findByAlias(user.getAlias()) != null) {
            return -1;
        }

        if (userDAO.findByEmail(user.getEmail()) != null) {
            return -1;
        }

        return userDAO.create(user);
    }

    public int delete(int userId) {
        // TODO: 21-09-17 implementar delete de comentarios y posts.
        int deleted = userDAO.delete(userId);
        return deleted;
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

    public void update(User user)
    {
        if (user == null) return;

        if (user.getFirstName().isEmpty()) return;

        if (user.getLastName().isEmpty()) return;

        if (user.getEmail().isEmpty()) return;

        if (user.getAlias().isEmpty()) return;

        if (user.getBio().isEmpty()) return;

        if(findByAlias(user.getAlias())!= null) return;

        if(findByEmail(user.getEmail()) != null) return;

        userDAO.update(user);
    }
}

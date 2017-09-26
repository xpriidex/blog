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

    public int update(User updatedUser)
    {
        if (updatedUser == null) return -1;

        if (updatedUser.getFirstName().isEmpty()) return -2;

        if (updatedUser.getLastName().isEmpty()) return -2;

        if (updatedUser.getEmail().isEmpty()) return -2;

        if (updatedUser.getAlias().isEmpty()) return -2;

        if (updatedUser.getBio().isEmpty()) return -2;

        User userByAlias = findByAlias(updatedUser.getAlias());

        if(userByAlias != null)
        {
            if(userByAlias.getId() != updatedUser.getId())
            {
                return -3;
            }
        }

        User userByEmail = findByEmail(updatedUser.getEmail());

        if(userByEmail != null)
        {
            if(userByEmail.getId() != updatedUser.getId())
            {
                return -4;
            }
        }

        return userDAO.update(updatedUser);
    }
}

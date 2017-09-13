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

        User user2 = new User();
        user2.setId(2);
        user2.setFirstName("Juan");
        user2.setLastName("Lopez");
        user2.setAlias("Juanes");
        user2.setBio("Programar, comer, dormir, repeat");
        user2.setEmail("juan4eva@gmail.com");
        user2.setImage("http://fotoViaje");

        User user3 = new User();
        user3.setId(3);
        user3.setFirstName("Paulina");
        user3.setLastName("Gamboa");
        user3.setAlias("Pali");
        user3.setBio("Dispersion");
        user3.setEmail("pgamboa@nisum.com");
        user3.setImage("http://selfieTrekking");

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
    }

    @Override
    public int create(User user) {
        user.setId(User.nextAvailableId++);
        userList.add(user);
        return user.getId();
    }

    @Override
    public List<User> findAll() {
        return userList;
    }

    @Override
    public User findById(int id) {
        User userFound = null;

        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId() == id) {
                userFound = userList.get(i);
                break;
            }
        }

        return userFound;
    }

    @Override
    public User findByAlias(String alias) {
        User userFound = null;

        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getAlias().equalsIgnoreCase(alias)) {
                userFound = userList.get(i);
                break;
            }
        }

        return userFound;
    }

    @Override
    public List<User> findByFirstName(String firstName) {

        List<User> usersFound = new ArrayList<>();

        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getFirstName() == firstName) {
                usersFound.add(userList.get(i));
            }
        }

        return null;
    }

    @Override
    public List<User> findByLastName(String lastName) {
        List<User> usersFound = new ArrayList<>();

        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getLastName().equalsIgnoreCase(lastName)) {
                usersFound.add(userList.get(i));
            }
        }
        return usersFound;
    }

    @Override
    public User findByEmail(String email) {
        User userFound = null;

        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getEmail().equalsIgnoreCase(email)) {
                userFound = userList.get(i);
                break;
            }
        }

        return userFound;
    }

    @Override
    public void update(User user) {

        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId() == user.getId())
            {
                userList.set(i, user);
                break;
            }
        }
    }

    @Override
    public int delete(int id) {
        int deleted = 0;

        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId() == id)
            {
                userList.remove(i);
                deleted ++;
            }
        }

        return deleted;
    }
}

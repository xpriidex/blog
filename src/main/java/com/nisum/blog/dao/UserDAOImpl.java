package com.nisum.blog.dao;

import com.nisum.blog.domain.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAOImpl implements UserDAO {

    private List<User> userList;

    public UserDAOImpl() {
        init();
    }

    private void init() {
        userList = new ArrayList<>();

        User user1 = new User();
        user1.setFirstName("Macarena");
        user1.setLastName("Yurjevic");
        user1.setAlias("Miki");
        user1.setBio("Wazuup");
        user1.setEmail("myurjevic@gmail.com");
        user1.setImage("http://miFotoFeliz");

        User user2 = new User();
        user2.setFirstName("Juan");
        user2.setLastName("Lopez");
        user2.setAlias("Juanes");
        user2.setBio("Programar, comer, dormir, repeat");
        user2.setEmail("juan4eva@gmail.com");
        user2.setImage("http://fotoViaje");

        User user3 = new User();
        user3.setFirstName("Paulina");
        user3.setLastName("Gamboa");
        user3.setAlias("Pali");
        user3.setBio("Dispersion");
        user3.setEmail("pgamboa@nisum.com");
        user3.setImage("http://selfieTrekking");

        User user4 = new User();
        user4.setFirstName("Domi");
        user4.setLastName("Sings");
        user4.setAlias("JuneSky");
        user4.setBio("I like flying");
        user4.setEmail("juneskydiamond@yahoo.com");
        user4.setImage("http://miPrimerDiaDeClases");


        create(user1);
        create(user2);
        create(user3);
        create(user4);
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

        return usersFound;
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
    public int update(User user) {
        int updatedRows = 0;

        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId() == user.getId())
            {
                updatedRows++;
                userList.set(i, user);
                break;
            }
        }

        return updatedRows;
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

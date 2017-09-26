package com.nisum.blog.dao;

import com.nisum.blog.domain.User;

import java.util.List;

public interface UserDAO {

    int create(User user);

    List<User> findAll();

    User findById(int id);

    User findByAlias(String alias);

    List<User> findByFirstName(String firstName);

    List<User> findByLastName(String lastName);

    User findByEmail(String email);

    int update(User user);

    int delete(int id);
}

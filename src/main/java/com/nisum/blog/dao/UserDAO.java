package com.nisum.blog.dao;

import com.nisum.blog.domain.User;

import java.util.List;

public interface UserDAO {

    int create(User user);

    User findById(int id);

    List<User> findAll();

    void update(User user);

    void delete(int id);
}

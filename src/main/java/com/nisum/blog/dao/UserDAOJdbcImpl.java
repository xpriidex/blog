package com.nisum.blog.dao;

import com.nisum.blog.dao.rowMapper.UserRowMapper;
import com.nisum.blog.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Qualifier("userDAOJdbc")
public class UserDAOJdbcImpl implements UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int create(User user) {
        return 0;
    }

    @Override
    public List<User> findAll() {
        List<User> userList =jdbcTemplate.query("select * from blog_user desc", new UserRowMapper());
        return userList;
    }

    @Override
    @Transactional
    public User findById(int id) {
        User user =  jdbcTemplate.queryForObject("select * from blog_user where id_user = ?", new Object[]{id}, new UserRowMapper());
        return user;
    }

    @Override
    public User findByAlias(String alias) {
        return null;
    }

    @Override
    public List<User> findByFirstName(String firstName) {
        return null;
    }

    @Override
    public List<User> findByLastName(String lastName) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public int delete(int id) {
        return 0;
    }
}

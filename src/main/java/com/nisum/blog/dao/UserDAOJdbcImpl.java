package com.nisum.blog.dao;

import com.nisum.blog.dao.rowMapper.UserRowMapper;
import com.nisum.blog.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
@Qualifier("userDAOJdbc")

@Primary
public class UserDAOJdbcImpl implements UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int create(User user) {
        try {
            String sql = "insert into blog_user (first_name, last_name, email, bio, alias) values(?,?,?,?,?)";

            KeyHolder holder = new GeneratedKeyHolder();
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, user.getFirstName());
                    ps.setString(2, user.getLastName());
                    ps.setString(3, user.getEmail());
                    ps.setString(4, user.getBio());
                    ps.setString(5, user.getAlias());
                    return ps;
                }
            }, holder);

            //holder.getKey().intValue(); This is the whole object

            int newUserId = (int) holder.getKeys().get("id_user");
            System.out.println(newUserId);
            user.setId(newUserId);
            return user.getId();
        } catch (EmptyResultDataAccessException e) {
            return -1;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        try {
            String sql = "select * from blog_user ORDER BY id_user;";

            List<User> userList = jdbcTemplate.query(sql, new UserRowMapper());
            return userList;
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<User>();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(int id) {
        try {
            String sql = "SELECT * FROM blog_user WHERE id_user = ?;";

            User user = jdbcTemplate.queryForObject(sql, new Object[]{id}, new UserRowMapper());
            return user;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public User findByAlias(String alias) {
        try {
            String sql = "select * from blog_user where UPPER(alias) = UPPER(?);";

            User user = jdbcTemplate.queryForObject(sql, new Object[]{alias}, new UserRowMapper());
            return user;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findByFirstName(String firstName) {
        try {
            String sql = "select * from blog_user where UPPER(first_name) = UPPER(?);";

            List<User> userList = jdbcTemplate.query(sql, new Object[]{firstName}, new UserRowMapper());
            return userList;
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<User>();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findByLastName(String lastName) {
        try {
            String sql = "select * from blog_user where UPPER(last_name) = UPPPER(?);";

            List<User> userList = jdbcTemplate.query(sql, new Object[]{lastName}, new UserRowMapper());
            return userList;
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<User>();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        try {
            String sql = "select * from blog_user where UPPER(email) = (?);";

            User user = jdbcTemplate.queryForObject(sql, new Object[]{email}, new UserRowMapper());
            return user;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void update(User user) {
        //final String updateSql = "insert into blog_user (first_name, last_name, email, bio, alias) values(?,?,?,?,?)";
        //jdbcTemplate.update(updateSql, new Object[]{user.getFirstName(), user.getLastName(), user.getEmail(), user.getBio(), user.getAlias()}, types);
        /*String updateStatement = " Update blog_user "
                + " SET first_name=?, "
                + " last_name=?, "
                + " email=?, "
                + " bio=?, "
                + " alias=?, "
                + " image=? "
                + " WHERE id_user=?";
        jdbcTemplate.update(updateStatement, new Object[] {"workstation77", ftpEvent.getId()});*/

        String updateSql = " Update blog_user"
                + " SET first_name=?,"
                + " last_name=?,"
                + " email=?,"
                + " bio=?,"
                + " alias=?,"
                + " image=?"
                + " WHERE id_user=?;";

        //This returns number of rows updated
        jdbcTemplate.update(updateSql, new Object[]{user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getBio(),
                user.getAlias(),
                user.getImage(),
                user.getId()});
    }

    @Override
    public int delete(int id) {
        String deleteStatement = "DELETE FROM blog_user WHERE id_user=?;";

        jdbcTemplate.update(deleteStatement, id);
        return 0;
    }
}

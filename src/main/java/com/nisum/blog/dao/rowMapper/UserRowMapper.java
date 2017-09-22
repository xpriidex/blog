package com.nisum.blog.dao.rowMapper;

import com.nisum.blog.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User>{

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id_user"));
        user.setAlias(rs.getString("alias"));
        user.setEmail(rs.getString("email"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setBio(rs.getString("bio"));
        user.setImage(rs.getString("image"));
        return user;
    }
}

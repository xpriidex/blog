package com.nisum.blog.dao.rowMapper;

import com.nisum.blog.domain.Post;
import org.joda.time.DateTime;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class PostRowMapper implements RowMapper<Post> {


    @Override
    public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
        Post post = new Post();


        post.setId(rs.getInt("id_post"));
        post.setTitle(rs.getString("title"));
        post.setBody(rs.getString("body"));
        post.setAuthorId(rs.getInt("id_user"));
        post.setPublicationDate(new DateTime(rs.getTimestamp("publication_date")));

        return post;
    }
}

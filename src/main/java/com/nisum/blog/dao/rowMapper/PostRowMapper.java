package com.nisum.blog.dao.rowMapper;

import com.nisum.blog.domain.Post;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
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
        Log log = LogFactory.getLog(PostRowMapper.class);
        log.info(rs.getTimestamp("publication_date"));
        log.info(new DateTime(rs.getTimestamp("publication_date"), DateTimeZone.UTC));
        log.info(new DateTime(rs.getTimestamp("publication_date").getTime()));

        log.info(new LocalDateTime(rs.getTimestamp("publication_date")));

        post.setPublicationDate(new LocalDateTime(rs.getTimestamp("publication_date")).toDateTime());

        return post;
    }
}

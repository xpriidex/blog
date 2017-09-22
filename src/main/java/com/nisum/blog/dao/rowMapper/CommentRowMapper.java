package com.nisum.blog.dao.rowMapper;

import com.nisum.blog.domain.Comment;
import org.joda.time.DateTime;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class CommentRowMapper implements RowMapper<Comment>{

    @Override
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Comment comment = new Comment();

        comment.setId(rs.getInt("id_comment"));
        comment.setBody("body");
        comment.setPublicationDate(new DateTime(rs.getTimestamp("publication_date")));
        comment.setPostId(rs.getInt("id_post"));
        comment.setAuthorId(rs.getInt("id_user"));

        return comment;
    }
}

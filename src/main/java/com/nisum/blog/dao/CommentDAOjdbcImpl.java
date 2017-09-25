package com.nisum.blog.dao;

import com.nisum.blog.dao.rowMapper.CommentRowMapper;
import com.nisum.blog.domain.Comment;
import com.nisum.blog.domain.Post;
import com.nisum.blog.domain.User;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository("commmentDAOJdbc")
public class CommentDAOjdbcImpl implements CommentDAO {

    private List<Comment> commentList;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("userDAOJdbc")
    private UserDAO userDAO;


    @Override
    @Transactional
    public int create(Comment comment) {
        try {
            String sql = "insert into comment (id_post, body, id_user) values(?,?,?)";

            KeyHolder holder = new GeneratedKeyHolder();
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, comment.getPostId());
                    ps.setString(2, comment.getBody());
                    ps.setInt(3, comment.getAuthorId());
                    return ps;
                }
            }, holder);

            int newCommentId = (int) holder.getKeys().get("id_user");
            System.out.println(newCommentId);
            comment.setId(newCommentId);
            return comment.getId();
        } catch (EmptyResultDataAccessException e) {
            return -1;
        }
    }


    @Override
    @Transactional
    public List<Comment> findByAuthorId(int id) {
        try {
            String sql = "SELECT * FROM comment WHERE id_user = ?";

            List<Comment> commentsByAuthor = jdbcTemplate.query(sql, new Object[]{id}, new CommentRowMapper());
            return commentsByAuthor;
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    @Override
    @Transactional
    public List<Comment> findByAuthorAlias(String alias) {
        List<Comment> commentsByAuthor = new ArrayList<>();
        int id = userDAO.findByAlias(alias).getId();
        try {
            String sql = "SELECT * FROM comment WHERE id_user = ?";

            commentsByAuthor = jdbcTemplate.query(sql, new Object[]{id}, new CommentRowMapper());
            return commentsByAuthor;
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    @Override
    @Transactional
    public List<Comment> findByPostId(int id) {
        try {
            String sql = "SELECT * FROM comment WHERE id_post = ?";

            List<Comment> commentsByPost = jdbcTemplate.query(sql, new Object[]{id}, new CommentRowMapper());
            return commentsByPost;
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    @Override
    @Transactional
    public List<Comment> findAll() {
        try {
            String sql = "SELECT * FROM comment";

            List<Comment> commentList = jdbcTemplate.query(sql, new CommentRowMapper());
            return commentList;
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }

    @Override
    @Transactional
    public Comment findById(int id) {
        try {
            String sql = "SELECT * FROM comment WHERE id_comment = ?;";

            Comment comment = jdbcTemplate.queryForObject(sql, new Object[]{id}, new CommentRowMapper());
            return comment;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public int deleteByCommentId(int id) {
        try {
            String sql = "delete from comment where id_comment = ?";

            int deleteById = jdbcTemplate.update(sql, id);
            return deleteById;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    @Override
    @Transactional
    public int deleteByAuthorId(int authorId) {
        try {
            String sql = "delete from comment where id_user = ?";

            int deleteCommentByAuthor = jdbcTemplate.update(sql, authorId);
            return deleteCommentByAuthor;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    @Override
    @Transactional
    public int deleteByPostId(int postId) {
        try {
            String sql = "delete from comment where id_post = ?";

            int deleteCommentByPost = jdbcTemplate.update(sql, postId);
            return deleteCommentByPost;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

}


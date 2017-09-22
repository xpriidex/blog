package com.nisum.blog.dao;

import com.nisum.blog.dao.rowMapper.PostRowMapper;
import com.nisum.blog.domain.Post;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Qualifier("postDAOJdbc")
public class PostDAOJdbcImpl implements PostDAO{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int create(Post post) {
return 0;
    }

    @Override
    @Transactional
    public Post findById(int id) {
        Post post = jdbcTemplate.queryForObject("select * from post where id_post=?",new Object[]{id},new PostRowMapper());

        return post;
    }

    @Override
    public List<Post> findAll() {
        List<Post> posts = jdbcTemplate.query("select * from post order by id_post desc",new PostRowMapper());

        return posts;
    }

    @Override
    public List<Post> findAllByTitle(String title) {
        return null;
    }

    @Override
    public List<Post> findAllByAuthorsAlias(String alias) {
        return null;
    }

    @Override
    public List<Post> findAllByContent(String content) {
        return null;
    }

    @Override
    public Post findByDate(DateTime queryDate) {
        return null;
    }

    @Override
    public Post findByByDateRange(DateTime queryDate1, DateTime queryDate2) {
        return null;
    }

    @Override
    public int update(Post Post) {
        return 0;
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("delete from post where id_post = ?", id);
    }

    @Override
    public int deleteByUserId(int id) {
        return 0;
    }
}

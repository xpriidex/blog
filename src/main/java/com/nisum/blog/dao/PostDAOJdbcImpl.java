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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("postDAOJdbc")
public class PostDAOJdbcImpl implements PostDAO{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("userDAOJdbc")
    private UserDAO userDAO;

    @Override
    @Transactional
    public int create(Post post) {

        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("post").usingGeneratedKeyColumns("id_post");
        Map<String, Object> parameters = new HashMap<String, Object>(4);

        parameters.put("title", post.getTitle());
        parameters.put("body", post.getBody());
        parameters.put("id_user", post.getAuthorId());

        Number insertedId = simpleJdbcInsert.executeAndReturnKey(parameters);
        return insertedId.intValue();
    }

    @Override
    @Transactional
    public Post findById(int id) {
        Post post = jdbcTemplate.queryForObject("select * from post where id_post=?",new Object[]{id},new PostRowMapper());

        return post;
    }

    @Override
    @Transactional
    public List<Post> findAll() {
        List<Post> posts = jdbcTemplate.query("select * from post order by id_post desc",new PostRowMapper());

        return posts;
    }

    @Override
    @Transactional
    public List<Post> findAllByTitle(String title) {

        List<Post> posts = jdbcTemplate.query("select * from post where title=?",new PostRowMapper());

        return posts;
    }

    @Override
    @Transactional
    public List<Post> findAllByAuthorsAlias(String alias) {
        List<Post> postsByAuthorAlias = new ArrayList<>();
        int id = userDAO.findByAlias(alias).getId();

        postsByAuthorAlias = jdbcTemplate.query("select * from post where id_user=?",new PostRowMapper());

        return postsByAuthorAlias;
    }

    @Override
    @Transactional
    public List<Post> findAllByContent(String content) {
        return null;
    }

    @Override
    @Transactional
    public List<Post> findByDate(DateTime queryDate) {
        return null;
    }

    @Override
    @Transactional
    public List<Post> findByByDateRange(DateTime queryDate1, DateTime queryDate2) {
        return null;
    }

    @Override
    @Transactional
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

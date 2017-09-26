package com.nisum.blog.dao;

import com.nisum.blog.dao.rowMapper.PostRowMapper;
import com.nisum.blog.dao.rowMapper.UserRowMapper;
import com.nisum.blog.domain.Post;
import com.nisum.blog.domain.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("postDAOJdbc")
public class PostDAOJdbcImpl implements PostDAO {
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
        Map<String, Object> parameters = new HashMap<String, Object>();

        parameters.put("title", post.getTitle());
        parameters.put("body", post.getBody());
        parameters.put("id_user", post.getAuthorId());

        Number insertedId = simpleJdbcInsert
                .usingColumns("title", "body", "id_user")
                .executeAndReturnKey(parameters);
        return insertedId.intValue();
    }

    @Override
    @Transactional
    public Post findById(int id) {
        Post post = jdbcTemplate.queryForObject("select * from post where id_post=?", new Object[]{id}, new PostRowMapper());

        return post;
    }

    @Override
    @Transactional
    public List<Post> findAll() {
        List<Post> posts = jdbcTemplate.query("select * from post order by id_post desc", new PostRowMapper());

        return posts;
    }

    @Override
    @Transactional
    public List<Post> findAllByTitle(String title) {

        title = title.toUpperCase();

        try {
            String sql = "SELECT * FROM post WHERE UPPER(title) LIKE ?;";

            List<Post> posList = jdbcTemplate.query(sql, new Object[]{"%" + title + "%"}, new PostRowMapper());
            return posList;

        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<Post>();
        }
    }

    @Override
    @Transactional
    public List<Post> findAllByAuthorsAlias(String alias) {
        try {
            int id = userDAO.findByAlias(alias).getId();
            String sql = "select * from post where id_user=?;";

            List<Post> posList = jdbcTemplate.query(sql, new Object[]{id}, new PostRowMapper());
            return posList;

        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<Post>();
        }
    }

    @Override
    @Transactional
    public List<Post> findAllByContent(String content) {

        content = content.toUpperCase();

        try {
            String sql = "SELECT * FROM post WHERE UPPER(body) LIKE ?;";

            List<Post> posList = jdbcTemplate.query(sql, new Object[]{"%" + content + "%"}, new PostRowMapper());
            return posList;

        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<Post>();
        }
    }

    @Override
    @Transactional
    public List<Post> findByDate(DateTime queryDate) {

        try {
            String sql = "select * from post where date_trunc('day', publication_date) = date_trunc('day', ?::timestamp);";

            List<Post> post = jdbcTemplate.query(sql,new Object[] {new Timestamp(queryDate.getMillis())}, new PostRowMapper());

            return post;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public List<Post> findByByDateRange(DateTime queryDate1, DateTime queryDate2) {
        String sql = "select * from post as p where " +
                "date_trunc('day', p.publication_date) between date_trunc('day', ?::timestamp) and date_trunc('day', ?::timestamp);";

        try {
            List<Post> post = jdbcTemplate.query(sql,new Object[] {new Timestamp(queryDate1.getMillis()), new Timestamp(queryDate2.getMillis())}, new PostRowMapper());

            return post;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public int update(Post post) {
        DateTime nowLocal = DateTime.now();
        Timestamp timeStamp = new Timestamp(nowLocal.getMillis());

        String sql = "update post set title = ?, body = ?, publication_date = ?  where id_post = ?";

        jdbcTemplate.update(sql, new Object[]{post.getTitle(), post.getBody(),timeStamp ,post.getId()});
        return post.getId();
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("delete from post where id_post = ?", id);
    }

    @Override
    public int deleteByUserId(int id) {
        try {
            String sql = "delete from post where id_user = ?";

            int countRawr = jdbcTemplate.update(sql, id);
            return countRawr;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }
}
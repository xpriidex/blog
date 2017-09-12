package com.nisum.blog.dao;

import com.nisum.blog.domain.Post;
import org.joda.time.DateTime;

import java.util.List;

public interface PostDAO {
    int create(Post post);

    Post findById(int id);

    List<Post> findAll();

    List<Post> findAllByTitle(String title);

    List<Post> findAllByAuthorsAlias(String alias);

    List<Post> findAllByContent(String content);

    Post findByDate(DateTime queryDate);

    Post findByByDateRange(DateTime queryDate1, DateTime queryDate2);

    void update(Post Post);

    void delete(int id);
}

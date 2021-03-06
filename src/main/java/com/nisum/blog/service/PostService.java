package com.nisum.blog.service;

import com.nisum.blog.dao.PostDAO;
import com.nisum.blog.dao.PostDAOImpl;
import com.nisum.blog.domain.Post;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    @Qualifier("postDAOJdbc")
    private PostDAO postDAO;

    public List<Post> findAll() {
        return postDAO.findAll();
    }

    public List<Post> findAllByTitle(String title) {
        return postDAO.findAllByTitle(title);
    }

    public List<Post> findAllByAuthorsAlias(String alias) {
        return postDAO.findAllByAuthorsAlias(alias);
    }

    public List<Post> findAllByContent(String content) {
        return postDAO.findAllByContent(content);
    }

    public Post findById(int id) {
        return postDAO.findById(id);
    }

    public int create(Post post) {
        DateTime nowLocal = DateTime.now();
        post.setPublicationDate(nowLocal);

        return postDAO.create(post);
    }
    public int update(Post post){
        DateTime nowLocal = DateTime.now();

        Post postToUpdate = findById(post.getId());

        postToUpdate.setPublicationDate(nowLocal);
        postToUpdate.setBody(post.getBody());
        postToUpdate.setTitle(post.getTitle());

        return postDAO.update(postToUpdate);

    }

    public void delete(int id) {
        postDAO.delete(id);
    }

    public int deleteByUserId(int id) {
        return postDAO.deleteByUserId(id);
    }

    public List<Post> findByDate(DateTime dateTime) {
        return postDAO.findByDate(dateTime);
    }

    public List<Post> findByDateRange(DateTime dateQuery1, DateTime dateQuery2) {
        return postDAO.findByByDateRange(dateQuery1, dateQuery2);

    }
}

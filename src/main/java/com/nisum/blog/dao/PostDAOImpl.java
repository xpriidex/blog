package com.nisum.blog.dao;

import com.nisum.blog.domain.Post;
import com.nisum.blog.domain.User;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class PostDAOImpl implements PostDAO {
    private UserDAO userDAO;
    private List<Post> postList;

    public PostDAOImpl() {
        init();
    }

    private void init() {
        postList = new ArrayList<>();
        userDAO = new UserDAOImpl();

        Post post1;
        Post post2;

        post1 = new Post();
        post2 = new Post();

        post1.setId(1);
        post1.setTitle("Narnia");
        post1.setAuthorId(1);
        post1.setBody("I am post about ANDROID");

        post2.setId(2);
        post2.setTitle("Papelucho");
        post1.setAuthorId(1);
        post2.setBody("I am post about AnImAlS");

        postList.add(post1);
        postList.add(post2);
    }

    @Override
    public int create(Post post) {
        post.setId(Post.nextAviableId++);
        postList.add(post);

        return post.getId();
    }

    @Override
    public Post findById(int id) {
        Post output = null;
        for (int i = 0; i < postList.size(); i++) {
            if (postList.get(i).getId() == id) {
                output = postList.get(i);
                break;
            }
        }

        return output;
    }

    @Override
    public List<Post> findAll() {
        return postList;
    }

    @Override
    public List<Post> findAllByTitle(String title) {
        List<Post> postsByTitle = new ArrayList<>();

        for (int i = 0; i < postList.size(); i++) {
            if (postList.get(i).getTitle().toLowerCase().contains(title.toLowerCase()))
                postsByTitle.add(postList.get(i));
        }

        return postsByTitle;
    }

    @Override
    public List<Post> findAllByAuthorsAlias(String alias) {
        List<Post> postsByAuthorAlias = new ArrayList<>();
        User userFound = userDAO.findByAlias(alias);

        if (userFound == null)
            return postsByAuthorAlias;

        for (int i = 0; i < postList.size(); i++) {
            if (postList.get(i).getAuthorId() == userFound.getId())
                postsByAuthorAlias.add(postList.get(i));
        }

        return postsByAuthorAlias;
    }

    @Override
    public List<Post> findAllByContent(String content) {
        List<Post> postsByContent = new ArrayList<>();

        for (int i = 0; i < postList.size(); i++) {
            if (postList.get(i).getBody().toLowerCase().contains(content.toLowerCase()))
                postsByContent.add(postList.get(i));
        }

        return postsByContent;
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
    public int update(Post post) {
        int id = -1;

        for (int i = 0; i < postList.size(); i++) {
            if (postList.get(i).getId() == post.getId()) {
                id = post.getId();
                postList.set(i,post);
                break;
            }
        }

        return id;
    }

    @Override
    public void delete(int id) {

    }
}

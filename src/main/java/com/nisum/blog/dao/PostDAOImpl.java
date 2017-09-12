package com.nisum.blog.dao;

import com.nisum.blog.domain.Post;
import com.nisum.blog.domain.User;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class PostDAOImpl implements PostDAO{
    private List<Post> postList;

    public PostDAOImpl() {
        init();
    }

    private void init(){
        postList = new ArrayList<>();

        Post post1;
        Post post2;
        User user;
        post1 = new Post();
        post2 = new Post();
        user = new User();

        user.setAlias("Felipe");
        post1.setId(1);
        post1.setTitle("Narnia");
        post1.setAuthor(user);
        post1.setBody("I am post about ANDROID");

        post2.setId(2);
        post2.setTitle("Papelucho");
        post2.setAuthor(user);
        post2.setBody("I am post about AnImAlS");

        postList.add(post1);
        postList.add(post2);
    }

    @Override
    public int create(Post post) {
        return 0;
    }

    @Override
    public Post findById(int id) {
        Post output = null;
        for (int i =0; i<postList.size(); i++){
            if(postList.get(i).getId()==id){
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

        for (int i = 0; i < postList.size(); i++) {
            if (postList.get(i).getAuthor().getAlias().equalsIgnoreCase(alias))
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
    public void update(Post post) {

    }

    @Override
    public void delete(int id) {

    }
}

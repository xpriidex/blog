package com.nisum.blog.service;

import com.nisum.blog.domain.Post;
import com.nisum.blog.service.exceptions.PostNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class PostService {
    private List<Post> postList;

    public PostService() {
        postList = new ArrayList<>();
    }

    public void add(Post post) {
        postList.add(post);
    }

    public void empty(){
        postList.clear();
    }

    public List<Post> findAll() throws PostNotFoundException {
        if (postList.isEmpty())
            throw new PostNotFoundException();

        return postList;
    }

    public List<Post> findAllByTitle(String title) throws PostNotFoundException {
        List<Post> postsByTitle = new ArrayList<>();

        for (int i = 0; i < postList.size(); i++) {
            if (postList.get(i).getTitle().toLowerCase().contains(title.toLowerCase()))
                postsByTitle.add(postList.get(i));
        }

        if (postsByTitle.isEmpty())
            throw new PostNotFoundException();

        return postsByTitle;
    }

    public List<Post> findAllByAuthorsAlias(String alias) throws PostNotFoundException {
        List<Post> postsByAuthorAlias = new ArrayList<>();

        for (int i = 0; i < postList.size(); i++) {
            if (postList.get(i).getAuthor().getAlias().equalsIgnoreCase(alias))
                postsByAuthorAlias.add(postList.get(i));
        }

        if (postsByAuthorAlias.isEmpty())
            throw new PostNotFoundException();

        return postsByAuthorAlias;
    }

    public List<Post> findAllByContent(String content) throws PostNotFoundException {
        List<Post> postsByContent = new ArrayList<>();

        for (int i = 0; i < postList.size(); i++) {
            if (postList.get(i).getBody().toLowerCase().contains(content.toLowerCase()))
                postsByContent.add(postList.get(i));
        }

        if (postsByContent.isEmpty())
            throw new PostNotFoundException();

        return postsByContent;
    }
}

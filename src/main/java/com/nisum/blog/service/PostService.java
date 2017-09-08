package com.nisum.blog.service;

import com.nisum.blog.domain.Post;
import com.nisum.blog.service.exceptions.AliasNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PostService {
    private List<Post> postList;

    public PostService() {
        postList = new ArrayList<>();
    }

    public void add(Post post) {
        postList.add(post);
    }

    public List<Post> findAll() {
        return postList;
    }

    public List<Post> findAllByTitle(String title) {
        List<Post> postsByTitle = new ArrayList<>();

        for (int i=0; i< postList.size();i++){
            if(postList.get(i).getTitle().toLowerCase().contains(title.toLowerCase()))
                postsByTitle.add(postList.get(i));
        }

        return postsByTitle;
    }

    public List<Post> findAllByAuthorsAlias(String alias) throws AliasNotFoundException {
        List<Post> postsByAuthorAlias = new ArrayList<>();

        for (int i=0; i< postList.size();i++){
            if(postList.get(i).getAuthor().getAlias().toLowerCase().contains(alias.toLowerCase()))
                postsByAuthorAlias.add(postList.get(i));
        }

        if(postsByAuthorAlias.size()==0)
            throw new AliasNotFoundException();

        return postsByAuthorAlias;
    }
}

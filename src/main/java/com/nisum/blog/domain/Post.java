package com.nisum.blog.domain;

import java.util.Date;
import java.util.List;

public class Post {
    private int id;
    private User author;
    private String title;
    private String body;
    private Date publicationDate;
    private List<Comment> myComments;

    public Post() {
    }

    public Post(int id, User author, String title, String body, Date publicationDate, List<Comment> myComments) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.body = body;
        this.publicationDate = publicationDate;
        this.myComments = myComments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public List<Comment> getMyComments() {
        return myComments;
    }

    public void setMyComments(List<Comment> myComments) {
        this.myComments = myComments;
    }
}

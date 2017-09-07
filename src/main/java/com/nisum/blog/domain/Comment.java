package com.nisum.blog.domain;

import java.util.Date;

public class Comment {
    private int id;
    private User author;
    private Date publicationDate;
    private String body;
    private Post originalPost;

    public Comment() {
    }

    public Comment(int id, User author, Date publicationDate, String body, Post originalPost) {
        this.id = id;
        this.author = author;
        this.publicationDate = publicationDate;
        this.body = body;
        this.originalPost = originalPost;
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

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Post getOriginalPost() {
        return originalPost;
    }

    public void setOriginalPost(Post originalPost) {
        this.originalPost = originalPost;
    }
}

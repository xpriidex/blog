package com.nisum.blog.domain;

import java.util.Date;

public class Post {
    public static int nextAviableId = 3;
    private int id;
    private int authorId;
    private String title;
    private String body;
    private Date publicationDate;

    public Post() {
    }

    public Post(int id, int authorId, String title, String body, Date publicationDate) {
        this.id = id;
        this.authorId = authorId;
        this.title = title;
        this.body = body;
        this.publicationDate = publicationDate;
    }

    public static int getNextAviableId() {
        return nextAviableId;
    }

    public static void setNextAviableId(int nextAviableId) {
        Post.nextAviableId = nextAviableId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
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
}

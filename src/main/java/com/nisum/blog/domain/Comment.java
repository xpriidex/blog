package com.nisum.blog.domain;

import java.util.Date;

public class Comment {
    public static int nextAvailableId = 4;

    private int id;
    private int authorId;
    private Date publicationDate;
    private String body;
    private int postId;

    public Comment() {
    }

    public Comment(int id, int authorId, Date publicationDate, String body, int postId) {
        this.id = id;
        this.authorId = authorId;
        this.publicationDate = publicationDate;
        this.body = body;
        this.postId = postId;
    }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
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

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}

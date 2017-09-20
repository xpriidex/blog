package com.nisum.blog.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.joda.time.DateTime;


public class Comment {
    public static int nextAvailableId = 1;

    private int id;
    private int authorId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private DateTime publicationDate;
    private String body;
    private int postId;

    public Comment() {
    }

    public Comment(int id, int authorId, DateTime publicationDate, String body, int postId) {
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

    public DateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(DateTime publicationDate) {
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

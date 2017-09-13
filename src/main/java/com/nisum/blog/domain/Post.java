package com.nisum.blog.domain;

import org.joda.time.DateTime;

import java.util.Date;

public class Post {
    public static int nextAviableId = 3;
    private int id;
    private int authorId;
    private String title;
    private String body;
    private DateTime publicationDate;

    public Post() {
    }

    public Post(int id, int authorId, String title, String body, DateTime publicationDate) {
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

    public DateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(DateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (id != post.id) return false;
        if (authorId != post.authorId) return false;
        if (title != null ? !title.equals(post.title) : post.title != null) return false;
        if (body != null ? !body.equals(post.body) : post.body != null) return false;
        return publicationDate != null ? publicationDate.equals(post.publicationDate) : post.publicationDate == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + authorId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (publicationDate != null ? publicationDate.hashCode() : 0);
        return result;
    }
}

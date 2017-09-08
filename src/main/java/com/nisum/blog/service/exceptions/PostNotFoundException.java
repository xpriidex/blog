package com.nisum.blog.service.exceptions;

public class PostNotFoundException extends Exception {
    public PostNotFoundException() {
    }

    public PostNotFoundException(String message) {
        super(message);
    }
}

package com.nisum.blog.service.exceptions;

public class AliasNotFoundException extends Exception{

    public AliasNotFoundException() {
    }

    public AliasNotFoundException(String message) {
        super(message);
    }
}

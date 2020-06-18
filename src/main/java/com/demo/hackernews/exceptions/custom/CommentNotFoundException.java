package com.demo.hackernews.exceptions.custom;

import com.demo.hackernews.exceptions.base.CustomerBaseException;

public class CommentNotFoundException extends CustomerBaseException {

    public CommentNotFoundException(String message) {
        super(message);
    }
}

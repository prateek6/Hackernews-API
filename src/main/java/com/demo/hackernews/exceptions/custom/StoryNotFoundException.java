package com.demo.hackernews.exceptions.custom;

import com.demo.hackernews.exceptions.base.CustomerBaseException;

public class StoryNotFoundException extends CustomerBaseException {

    public StoryNotFoundException(String message) {
        super(message);
    }
}

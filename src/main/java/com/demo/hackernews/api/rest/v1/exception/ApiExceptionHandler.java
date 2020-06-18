package com.demo.hackernews.api.rest.v1.exception;

import com.demo.hackernews.api.rest.v1.messages.ApiMessage;
import com.demo.hackernews.api.rest.v1.messages.ApiMessageProvider;
import com.demo.hackernews.exceptions.custom.CommentNotFoundException;
import com.demo.hackernews.exceptions.custom.StoryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {



    @ExceptionHandler(StoryNotFoundException.class)
    public ApiMessage storyNotFoundException(StoryNotFoundException e) {
        return  ApiMessageProvider.provides(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
    }


    @ExceptionHandler(CommentNotFoundException.class)
    public ApiMessage commentNotFoundException(CommentNotFoundException e) {
        return  ApiMessageProvider.provides(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
    }



}

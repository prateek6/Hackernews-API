package com.demo.hackernews.exceptions.base;

public class CustomerBaseException extends RuntimeException {

    private  String message;

    public CustomerBaseException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}

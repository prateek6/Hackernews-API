package com.demo.hackernews.api.rest.v1.messages;

import org.springframework.http.HttpStatus;

public class ApiMessageProvider{

    private static final String API_VERSION = "v1.0";

    public static ApiMessage provides(Object data, HttpStatus httpStatus){
        ApiMessage apiMessage = new ApiMessage();
        apiMessage.setApiVersion(API_VERSION);
        apiMessage.setResult(data);
        apiMessage.setHttpStatus(httpStatus);
        return apiMessage;
    }

    private ApiMessageProvider(){

    }
}

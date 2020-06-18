package com.demo.hackernews.api.rest.v1.messages;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class ApiMessage implements Serializable {


    private String apiVersion;
    private Object result;
    private HttpStatus httpStatus;

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}

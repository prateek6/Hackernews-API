package com.demo.hackernews.infrastructure.dto;

public class CommentsDTO {
    private String comment;
    private String userHandle;
    private int userHackerNewsAge;


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserHandle() {
        return userHandle;
    }

    public void setUserHandle(String userHandle) {
        this.userHandle = userHandle;
    }

    public int getUserHackerNewsAge() {
        return userHackerNewsAge;
    }

    public void setUserHackerNewsAge(int userHackerNewsAge) {
        this.userHackerNewsAge = userHackerNewsAge;
    }
}


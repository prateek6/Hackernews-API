package com.demo.hackernews.api.rest.v1.dto;

public class CommentsDto {

    private String comment;
    private String  userHandle;
    private int totalNumberOfComments;
    private long userAccountAgeInYears;


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

    public int getTotalNumberOfComments() {
        return totalNumberOfComments;
    }

    public void setTotalNumberOfComments(int totalNumberOfComments) {
        this.totalNumberOfComments = totalNumberOfComments;
    }

    public long getUserAccountAgeInYears() {
        return userAccountAgeInYears;
    }

    public void setUserAccountAgeInYears(long userAccountAgeInYears) {
        this.userAccountAgeInYears = userAccountAgeInYears;
    }
}





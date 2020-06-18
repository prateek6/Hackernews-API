package com.demo.hackernews.domain.stories;

import com.demo.hackernews.domain.users.User;

public class Comment {

    private String comment;
    private int totalNumberOfComments;
    private Story story;
    private User user;


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getTotalNumberOfComments() {
        return totalNumberOfComments;
    }

    public void setTotalNumberOfComments(int totalNumberOfComments) {
        this.totalNumberOfComments = totalNumberOfComments;
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

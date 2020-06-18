package com.demo.hackernews.domain.stories;

import com.demo.hackernews.domain.users.User;

import java.time.LocalDateTime;

public class Story {

    private int storyId;
    private String title;
    private  int score;
    private String url;
    private LocalDateTime timeOfSubmission;
    private User user;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public LocalDateTime getTimeOfSubmission() {
        return timeOfSubmission;
    }

    public void setTimeOfSubmission(LocalDateTime timeOfSubmission) {
        this.timeOfSubmission = timeOfSubmission;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
    }
}

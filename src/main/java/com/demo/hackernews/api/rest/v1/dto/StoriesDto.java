package com.demo.hackernews.api.rest.v1.dto;


public class StoriesDto {

    private String title;
    private String url;
    private int score;
    private String timeOfSubmission;
    private String userHandle;
    private int storyId;


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

    public String getTimeOfSubmission() {
        return timeOfSubmission;
    }

    public void setTimeOfSubmission(String timeOfSubmission) {
        this.timeOfSubmission = timeOfSubmission;
    }

    public String getUserHandle() {
        return userHandle;
    }

    public void setUserHandle(String userHandle) {
        this.userHandle = userHandle;
    }

    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
    }
}

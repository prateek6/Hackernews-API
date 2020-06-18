package com.demo.hackernews.infrastructure.dto;

import java.util.List;

public class HackerNewsCommentDTO {
    private int parent;
    private String by;
    private int id;
    private String text;
    private int time;
    private String type;
    private List<Long> kids;
    private boolean deleted;
    private int totalNumberOfComments;


    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Long> getKids() {
        return kids;
    }

    public void setKids(List<Long> kids) {
        this.kids = kids;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }


    public int getTotalNumberOfComments() {
        return totalNumberOfComments;
    }

    public void setTotalNumberOfComments(int totalNumberOfComments) {
        this.totalNumberOfComments = totalNumberOfComments;
    }

    @Override
    public String toString() {
        return "HackerNewsCommentDTO{" +
                "parent=" + parent +
                ", by='" + by + '\'' +
                ", id=" + id +
                ", text='" + text + '\'' +
                ", time=" + time +
                ", type='" + type + '\'' +
                ", kids=" + kids +
                ", deleted=" + deleted +
                ", totalNumberOfComments=" + totalNumberOfComments +
                '}';
    }
}

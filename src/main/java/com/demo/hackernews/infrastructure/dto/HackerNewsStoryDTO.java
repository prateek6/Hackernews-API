package com.demo.hackernews.infrastructure.dto;

import java.util.List;

public class HackerNewsStoryDTO {
    private Integer score;
    private String by;
    private int id;
    private Long time;
    private String title;
    private String type;
    private Integer descendants;
    private String url;
    private List<Long> kids;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
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

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getDescendants() {
        return descendants;
    }

    public void setDescendants(Integer descendants) {
        this.descendants = descendants;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Long> getKids() {
        return kids;
    }

    public void setKids(List<Long> kids) {
        this.kids = kids;
    }

    @Override
    public String toString() {
        return "HackerNewsStoryDTO{" +
                "score=" + score +
                ", by='" + by + '\'' +
                ", id=" + id +
                ", time=" + time +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", descendants=" + descendants +
                ", url='" + url + '\'' +
                ", kids=" + kids +
                '}';
    }
}

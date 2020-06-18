package com.demo.hackernews.infrastructure.enties.mysql;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Id;

import javax.persistence.*;

@Entity(name = "story")
@Table(name = "story")
public class StoryEntity {

    @Id
    private int id;

    @Column(name = "score")
    private int score;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsersEntity user;


    @Column(name = "time")
    private long time;

    @Column(name = "title")
    private String title;

    @Column(name = "type")
    private String type;

    @Column(name = "descendants")
    private int descendants;

    @Column(name = "url")
    private String url;


    @Column(name = "is_top_story")
    private boolean isTopStory;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public long getTime() {
        return time;
    }

    public void setTime(long time) {
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

    public int getDescendants() {
        return descendants;
    }

    public void setDescendants(int descendants) {
        this.descendants = descendants;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }

    public boolean isTopStory() {
        return isTopStory;
    }

    public void setTopStory(boolean topStory) {
        isTopStory = topStory;
    }



}

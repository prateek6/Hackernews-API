package com.demo.hackernews.infrastructure.enties.mysql;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Id;

import javax.persistence.*;

@Entity(name = "comments")
@Table(name = "comments")
public class CommentEntity {

    @Id
    private int id;

    @Column(name = "parent")
    private long parent;

    @Column(name = "comment")
    @Type(type="text")
    private String text;

    @Column(name = "time")
    private long time;

    @Column(name = "type")
    private String type;

    @Column(name = "total_comments")
    private int totalComments;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsersEntity user;

    @ManyToOne
    @JoinColumn(name = "story_id")
    private StoryEntity story;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getParent() {
        return parent;
    }

    public void setParent(long parent) {
        this.parent = parent;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTotalComments() {
        return totalComments;
    }

    public void setTotalComments(int totalComments) {
        this.totalComments = totalComments;
    }

    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }

    public StoryEntity getStory() {
        return story;
    }

    public void setStory(StoryEntity story) {
        this.story = story;
    }


}

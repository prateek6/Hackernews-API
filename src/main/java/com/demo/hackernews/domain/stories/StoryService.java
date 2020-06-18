package com.demo.hackernews.domain.stories;

import java.util.List;

public interface StoryService {

    public List<Story>  getTopStories();

    public List<Comment> getComments(String storyId);

    public List<Story> getPastStories();


    public void saveTopStories();

    public void saveComments(List<Long> commentIds);

}

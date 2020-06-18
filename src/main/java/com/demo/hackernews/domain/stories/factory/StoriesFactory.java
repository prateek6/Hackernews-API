package com.demo.hackernews.domain.stories.factory;

import com.demo.hackernews.domain.stories.StoryService;
import com.demo.hackernews.domain.stories.impl.HackerNewsStoryServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.demo.hackernews.commons.constants.Constants.STORY_TYPE_HACKER_NEWS;

@Component
public class StoriesFactory {

    @Autowired
    private HackerNewsStoryServiceService hackerNewsStoryService;

    public StoryService getStory(String storyType) {
        if (STORY_TYPE_HACKER_NEWS.equalsIgnoreCase(storyType)) {
            return hackerNewsStoryService;
        }
        throw new IllegalArgumentException("Not a valid story type : " + storyType);
    }
}

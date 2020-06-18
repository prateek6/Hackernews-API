package com.demo.hackernews.commons.utils;

import static com.demo.hackernews.commons.constants.Constants.*;

public class HackerNewsUtil {

    public static String getHackerNewTopStoriesUrl() {
        return HACKER_NEWS_TOP_STORIES_ENDPOINT;
    }

    public static String getHackerNewStoryUrl(Integer storyId) {
        return getHackerNewsItemUrl(storyId);
    }

    public static String getHackerNewsItemUrl(Long itemId){
        return HACKER_NEWS_ITEM_ENDPOINT+"/"+itemId+".json";
    }

    public static String getHackerNewsItemUrl(Integer itemId){
        return HACKER_NEWS_ITEM_ENDPOINT+"/"+itemId+".json";
    }

    public static String getHackerNewsCommentUrl(Long storyId) {
        return getHackerNewsItemUrl(storyId);
    }


    public static String getHackerNewsUserUrl(String userHandle) {
        return HACKER_NEWS_USER_ENDPOINT+"/"+userHandle+".json";
    }
}

package com.demo.hackernews.controller;

import com.demo.hackernews.api.rest.v1.dto.CommentsDto;
import com.demo.hackernews.api.rest.v1.dto.StoriesDto;
import com.demo.hackernews.commons.utils.DateUtil;
import com.demo.hackernews.domain.stories.Comment;
import com.demo.hackernews.domain.stories.Story;
import com.demo.hackernews.domain.stories.StoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.demo.hackernews.domain.stories.factory.StoriesFactory;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StoriesController {

    @Autowired
    private StoriesFactory storiesFactory;

    private static final Logger LOGGER = LoggerFactory.getLogger(StoriesController.class.getName());

    public List<StoriesDto> getTopStories(String storyType) {
        StoryService story = storiesFactory.getStory(storyType);
        List<Story> topStories = story.getTopStories();
        LOGGER.debug("Top stories size : {} ",topStories.size());

        List<StoriesDto> topStoriesDtoList = new ArrayList<>(topStories.size());
        topStories.parallelStream().forEach(topStory -> {
            StoriesDto storiesDtO = new StoriesDto();
            storiesDtO.setStoryId(topStory.getStoryId());
            storiesDtO.setTitle(topStory.getTitle());
            storiesDtO.setUrl(topStory.getUrl());
            storiesDtO.setScore(topStory.getScore());
            storiesDtO.setUserHandle(topStory.getUser().getUserHandle());
            storiesDtO.setTimeOfSubmission(DateUtil.getHumanReadableDate(topStory.getTimeOfSubmission()));
            topStoriesDtoList.add(storiesDtO);
        });
        return topStoriesDtoList;
    }



    public List<StoriesDto> getPastStories(String storyType) {
        StoryService storyService = storiesFactory.getStory(storyType);
        List<Story> pastStories = storyService.getPastStories();
        LOGGER.debug("Past stories size : {} ",pastStories.size());
        List<StoriesDto> pastStoriesDtoList = new ArrayList<>(pastStories.size());
        pastStories.parallelStream().forEach(pastStory -> {
            StoriesDto storiesDtO = new StoriesDto();
            storiesDtO.setStoryId(pastStory.getStoryId());
            storiesDtO.setTitle(pastStory.getTitle());
            storiesDtO.setUrl(pastStory.getUrl());
            storiesDtO.setScore(pastStory.getScore());
            storiesDtO.setUserHandle(pastStory.getUser().getUserHandle());
            storiesDtO.setTimeOfSubmission(DateUtil.getHumanReadableDate(pastStory.getTimeOfSubmission()));
            pastStoriesDtoList.add(storiesDtO);
        });
        return pastStoriesDtoList;
    }
}

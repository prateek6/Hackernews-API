package com.demo.hackernews.controller;

import com.demo.hackernews.api.rest.v1.dto.CommentsDto;
import com.demo.hackernews.domain.stories.Comment;
import com.demo.hackernews.domain.stories.StoryService;
import com.demo.hackernews.domain.stories.factory.StoriesFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CommentsController {

    @Autowired
    private StoriesFactory storiesFactory;

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentsController.class.getName());

    public List<CommentsDto> getComments(String storyId, String storyType) {
        StoryService story = storiesFactory.getStory(storyType);
        List<Comment> comments = story.getComments(storyId);
        LOGGER.debug("Comments size : {} ",comments.size());

        List<CommentsDto> commentsDtoList = new ArrayList<>(comments.size());
        comments.parallelStream().forEach(comment -> {
            CommentsDto commentsDto = new CommentsDto();
            commentsDto.setComment(comment.getComment());
            commentsDto.setTotalNumberOfComments(comment.getTotalNumberOfComments());
            commentsDto.setUserAccountAgeInYears(comment.getUser().getUserAccountAge());
            commentsDto.setUserHandle(comment.getUser().getUserHandle());
            commentsDtoList.add(commentsDto);
        });
        return commentsDtoList;
    }
}

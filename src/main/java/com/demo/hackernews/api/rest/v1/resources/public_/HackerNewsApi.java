package com.demo.hackernews.api.rest.v1.resources.public_;

import com.demo.hackernews.api.rest.v1.messages.ApiMessage;
import com.demo.hackernews.api.rest.v1.messages.ApiMessageProvider;
import com.demo.hackernews.controller.CommentsController;
import com.demo.hackernews.controller.StoriesController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import static com.demo.hackernews.commons.constants.Constants.STORY_TYPE_HACKER_NEWS;

@RestController
@RequestMapping(path="/v1/hacker-news")
@CrossOrigin("*")
public class HackerNewsApi {

    @Autowired
    private StoriesController storiesController;

    @Autowired
    private CommentsController commentsController;


    @GetMapping("/top-stories")
    public ApiMessage getTopStories(){
        return  ApiMessageProvider.provides(storiesController.getTopStories(STORY_TYPE_HACKER_NEWS),HttpStatus.OK);
    }

    @GetMapping("/comments")
    public ApiMessage getComments(@RequestParam(value = "storyId")String storyId){
        return ApiMessageProvider.provides(commentsController.getComments(storyId,STORY_TYPE_HACKER_NEWS), HttpStatus.OK);
    }

    @GetMapping("/past-stories")
    public ApiMessage getPastStories(){
        return  ApiMessageProvider.provides(storiesController.getPastStories(STORY_TYPE_HACKER_NEWS),HttpStatus.OK);
    }


}

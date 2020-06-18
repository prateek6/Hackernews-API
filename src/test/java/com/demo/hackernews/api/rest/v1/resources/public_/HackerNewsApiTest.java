package com.demo.hackernews.api.rest.v1.resources.public_;

import com.demo.hackernews.api.rest.v1.dto.CommentsDto;
import com.demo.hackernews.api.rest.v1.dto.StoriesDto;
import com.demo.hackernews.controller.CommentsController;
import com.demo.hackernews.controller.StoriesController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static com.demo.hackernews.commons.constants.Constants.STORY_TYPE_HACKER_NEWS;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers= HackerNewsApi.class)
@ActiveProfiles(value = "test")
class HackerNewsApiTest {


    @MockBean
    private CommentsController commentsController;

    @MockBean
    private StoriesController storiesController;


    @Autowired
    private MockMvc mockMvc;



    @Test
    void getTopStories() throws Exception {

        List<StoriesDto> storiesDtoList = new ArrayList<>();
        storiesDtoList.add(new StoriesDto());
        given(storiesController.getTopStories(STORY_TYPE_HACKER_NEWS)).willReturn(storiesDtoList);
        mockMvc.perform(get("/v1/hacker-news/top-stories")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").isNotEmpty());

    }

    @Test
    void getComments() throws Exception {
        List<CommentsDto> commentsDtoList = new ArrayList<>();
        commentsDtoList.add(new CommentsDto());
        given(commentsController.getComments(anyString(),anyString())).willReturn(commentsDtoList);
        mockMvc.perform(get("/v1/hacker-news/comments?storyId=12345")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").isNotEmpty());
    }



    @Test
    void getPastStories() throws Exception {

        List<StoriesDto> storiesDtoList = new ArrayList<>();
        storiesDtoList.add(new StoriesDto());
        given(storiesController.getPastStories(STORY_TYPE_HACKER_NEWS)).willReturn(storiesDtoList);
        mockMvc.perform(get("/v1/hacker-news/past-stories")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").isNotEmpty());

    }


}
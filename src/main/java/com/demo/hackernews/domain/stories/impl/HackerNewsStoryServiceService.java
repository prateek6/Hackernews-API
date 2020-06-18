package com.demo.hackernews.domain.stories.impl;

import com.demo.hackernews.commons.utils.DateUtil;
import com.demo.hackernews.domain.stories.Comment;
import com.demo.hackernews.domain.stories.Story;
import com.demo.hackernews.domain.users.User;
import com.demo.hackernews.exceptions.custom.CommentNotFoundException;
import com.demo.hackernews.exceptions.custom.StoryNotFoundException;
import com.demo.hackernews.infrastructure.dto.HackerNewUserDTO;
import com.demo.hackernews.infrastructure.dto.HackerNewsCommentDTO;
import com.demo.hackernews.infrastructure.dto.HackerNewsStoryDTO;
import com.demo.hackernews.commons.utils.HackerNewsUtil;
import com.demo.hackernews.infrastructure.cache.impl.CommentCache;
import com.demo.hackernews.infrastructure.cache.impl.StoryCache;
import com.demo.hackernews.infrastructure.enties.mysql.CommentEntity;
import com.demo.hackernews.infrastructure.enties.mysql.StoryEntity;
import com.demo.hackernews.infrastructure.enties.mysql.UsersEntity;
import com.demo.hackernews.infrastructure.repository.story.StoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import com.demo.hackernews.domain.stories.StoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.demo.hackernews.commons.constants.Constants.HACKER_NEWS_TYPE_STORY;

@Service
@Scope(value="prototype", proxyMode= ScopedProxyMode.TARGET_CLASS)
public class HackerNewsStoryServiceService implements StoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HackerNewsStoryServiceService.class.getName());


    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private StoryCache storyCache;

    @Autowired
    private CommentCache commentCache;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public List<Story> getTopStories() {
        List<StoryEntity> topStories = storyRepository.getTopStories();
        if (Objects.isNull(topStories) || topStories.isEmpty()) {
            throw new StoryNotFoundException("Top stories are not found, please try again after some time");
        }
        LOGGER.debug("Top stories size is : {} ",topStories.size());

        List<Story> storyList = new ArrayList<>(topStories.size());
        topStories.parallelStream().forEach(storyEntity -> {
            Story story = new Story();
            story.setScore(storyEntity.getScore());
            story.setTitle(storyEntity.getTitle());
            story.setUrl(storyEntity.getUrl());
            story.setTimeOfSubmission(DateUtil.getLocalDateTimeByEpoch(storyEntity.getTime()));
            User user = new User();
            user.setUserHandle(storyEntity.getUser().getUserHandle());
            story.setUser(user);
            story.setStoryId(storyEntity.getId());
            storyList.add(story);
        });
        return storyList;
    }

    @Override
    public List<Comment> getComments(String storyId) {
        List<CommentEntity> hackerNewsCommentEntities = storyRepository.getCommentByStoryId(storyId);
        if (Objects.isNull(hackerNewsCommentEntities) || hackerNewsCommentEntities.isEmpty()) {
            throw new CommentNotFoundException("Comment not found for story id : " + storyId);
        }
        LOGGER.debug("comments size is : {} ",hackerNewsCommentEntities.size());

        List<Comment> commentList = new ArrayList<>(hackerNewsCommentEntities.size());
        hackerNewsCommentEntities.parallelStream().forEach(commentEntity -> {
            Comment comment = new Comment();
            comment.setComment(commentEntity.getText());
            comment.setTotalNumberOfComments(commentEntity.getTotalComments());
            User user = new User();
            user.setUserHandle(commentEntity.getUser().getUserHandle());
            user.setUserAccountAge(DateUtil.diffInYears(commentEntity.getUser().getAccountCreated()));
            comment.setUser(user);
            commentList.add(comment);
        });
        return commentList;
    }

    @Override
    public List<Story> getPastStories() {
        List<StoryEntity> pastStories = storyRepository.getAllStories();
        if (Objects.isNull(pastStories) || pastStories.isEmpty()) {
            throw new StoryNotFoundException("Past stories are not found, please try again after some time");
        }

        LOGGER.debug("Past stories size is : {} ",pastStories.size());
        List<Story> storyList = new ArrayList<>(pastStories.size());
        pastStories.parallelStream().forEach(storyEntity -> {
            Story story = new Story();
            story.setScore(storyEntity.getScore());
            story.setTitle(storyEntity.getTitle());
            story.setUrl(storyEntity.getUrl());
            story.setTimeOfSubmission(DateUtil.getLocalDateTimeByEpoch(storyEntity.getTime()));
            User user = new User();
            user.setUserHandle(storyEntity.getUser().getUserHandle());
            story.setUser(user);
            story.setStoryId(storyEntity.getId());
            storyList.add(story);
        });
        return storyList;
    }


    @Override
    public void saveTopStories() {


        ResponseEntity<List> hackerNewsTopStoriesResponseEntity = restTemplate.getForEntity(HackerNewsUtil.getHackerNewTopStoriesUrl(), List.class);
        if (hackerNewsTopStoriesResponseEntity.getStatusCode().is2xxSuccessful() && !Objects.isNull(hackerNewsTopStoriesResponseEntity.getBody())) {
            List<Integer> storiesIdsList = hackerNewsTopStoriesResponseEntity.getBody();
            LOGGER.info("Number of top stories are: {} ", storiesIdsList.size());
            storiesIdsList.parallelStream().forEach(storyId -> {
                ResponseEntity<HackerNewsStoryDTO> storyResponseEntity = restTemplate.getForEntity(HackerNewsUtil.getHackerNewStoryUrl(storyId), HackerNewsStoryDTO.class);
                LOGGER.debug("StoryService response :{} ", storyResponseEntity);
                HackerNewsStoryDTO hackerNewsStoryDTO = storyResponseEntity.getBody();

                if(HACKER_NEWS_TYPE_STORY.equalsIgnoreCase(hackerNewsStoryDTO.getType())) {//Need to add only stories not polls from hackernews
                    storyCache.addToCache(hackerNewsStoryDTO);
                }
            });
        }
        List<HackerNewsStoryDTO> hackerNewsStoryDTOList = storyCache.getCache();
        storyCache.clearCache();
        saveStoryAndUser(hackerNewsStoryDTOList);

        hackerNewsStoryDTOList.parallelStream().forEach(hackerNewsStoryDTO -> {
            saveComments(hackerNewsStoryDTO.getKids());
        });

    }


    @Override
    public void saveComments(List<Long> commentIds) {
        if (Objects.isNull(commentIds) || commentIds.isEmpty()) {
            return;
        }
        LOGGER.info("Number of Parent comments are: {} ", commentIds.size());
        RestTemplate restTemplate = new RestTemplate();
        commentIds.parallelStream().forEach(commentId -> {
            ResponseEntity<HackerNewsCommentDTO> commentResponseEntity = restTemplate.getForEntity(HackerNewsUtil.getHackerNewsCommentUrl(commentId), HackerNewsCommentDTO.class);
            HackerNewsCommentDTO hackerNewsCommentDTO = commentResponseEntity.getBody();
            if (!Objects.isNull(hackerNewsCommentDTO) && !hackerNewsCommentDTO.isDeleted() && !Objects.isNull(hackerNewsCommentDTO.getBy())) {
                if (!Objects.isNull(hackerNewsCommentDTO.getKids()) && !hackerNewsCommentDTO.getKids().isEmpty()) {
                    int totalComments = getTotalComments(hackerNewsCommentDTO, 0);
                    hackerNewsCommentDTO.setTotalNumberOfComments(totalComments);
                }
                commentCache.addToCache(hackerNewsCommentDTO);
            } else {
                LOGGER.debug("Parent comment is deleted!!!");
            }
        });

        List<HackerNewsCommentDTO> hackerNewsCommentDTOList = commentCache.getCache();
        commentCache.clearCache();
        saveCommentAndUser(hackerNewsCommentDTOList);

    }


    private int getTotalComments(HackerNewsCommentDTO hackerNewsCommentDTO, int totalCount) {
        if (Objects.isNull(hackerNewsCommentDTO) || hackerNewsCommentDTO.isDeleted() || Objects.isNull(hackerNewsCommentDTO.getBy())) {
            LOGGER.debug("Child comment is deleted !!!!!!!!!!!!!");
            return totalCount;
        }
        if ( Objects.isNull(hackerNewsCommentDTO.getKids()) || hackerNewsCommentDTO.getKids().isEmpty()) {
            return totalCount;
        }
        totalCount = totalCount + hackerNewsCommentDTO.getKids().size();

        List<Long> commentIdList = hackerNewsCommentDTO.getKids();
        for (Long commentId : commentIdList) {
            ResponseEntity<HackerNewsCommentDTO> commentResponseEntity = restTemplate.getForEntity(HackerNewsUtil.getHackerNewsCommentUrl(commentId), HackerNewsCommentDTO.class);
            HackerNewsCommentDTO childCommentDTO = commentResponseEntity.getBody();
            totalCount = getTotalComments(childCommentDTO, totalCount);
        }
        return totalCount;
    }


    @Transactional(propagation = Propagation.REQUIRED)
    private void saveStoryAndUser(List<HackerNewsStoryDTO> hackerNewsStoryDTOList) {
        storyRepository.updateTopStory();
        hackerNewsStoryDTOList.parallelStream().forEach(hackerNewsStoryDTO -> {
            storyRepository.saveStory(hackerNewsStoryDTO, getUser(hackerNewsStoryDTO.getBy()));

        });
    }

    @Transactional(propagation = Propagation.REQUIRED)
    private void saveCommentAndUser(List<HackerNewsCommentDTO> hackerNewsCommentDTOList) {
        hackerNewsCommentDTOList.parallelStream().forEach(hackerNewsCommentDTO -> {
            StoryEntity storyEntity = storyRepository.getStoryById(hackerNewsCommentDTO.getParent());
            storyRepository.saveComment(hackerNewsCommentDTO, getUser(hackerNewsCommentDTO.getBy()), storyEntity);
        });
    }

    /**
     * Check the users database before making the api call.
     *
     * @param userHandle userId of hacker news
     * @return userEntity either from api call or from database
     */
    public UsersEntity getUser(String userHandle) {
        Optional<UsersEntity> usersEntityOptional = storyRepository.getUserByHandle(userHandle);
        if (usersEntityOptional.isPresent()) {
            return usersEntityOptional.get();
        }

        ResponseEntity<HackerNewUserDTO> userResponseEntity = restTemplate.getForEntity(HackerNewsUtil.getHackerNewsUserUrl(userHandle), HackerNewUserDTO.class);
        UsersEntity usersEntity = null;
        if (userResponseEntity.getStatusCode().is2xxSuccessful() && !Objects.isNull(userResponseEntity.getBody())) {
            usersEntity = storyRepository.saveUser(userResponseEntity.getBody());
        }
        return usersEntity;
    }

}

package com.demo.hackernews.infrastructure.repository.story;

import com.demo.hackernews.infrastructure.dto.HackerNewUserDTO;
import com.demo.hackernews.infrastructure.dto.HackerNewsCommentDTO;
import com.demo.hackernews.infrastructure.dto.HackerNewsStoryDTO;
import com.demo.hackernews.infrastructure.enties.mysql.CommentEntity;
import com.demo.hackernews.infrastructure.enties.mysql.StoryEntity;
import com.demo.hackernews.infrastructure.enties.mysql.UsersEntity;
import com.demo.hackernews.infrastructure.repository.mysql.CommentJpaRepository;
import com.demo.hackernews.infrastructure.repository.mysql.StoryJpaRepository;
import com.demo.hackernews.infrastructure.repository.mysql.UsersJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StoryRepository {

    @Autowired
    private CommentJpaRepository commentJpaRepository;


    @Autowired
    private StoryJpaRepository storyJpaRepository;

    @Autowired
    private UsersJpaRepository usersJpaRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(StoryRepository.class.getName());


    public void saveStory(HackerNewsStoryDTO hackerNewsStoryDTO, UsersEntity usersEntity) {
        StoryEntity storyEntity;
        Optional<StoryEntity> hackerNewsStoryEntityOptional = storyJpaRepository.findById(hackerNewsStoryDTO.getId());
        storyEntity = hackerNewsStoryEntityOptional.orElseGet(StoryEntity::new);
        storyEntity.setUser(usersEntity);
        storyEntity.setDescendants(hackerNewsStoryDTO.getDescendants());
        storyEntity.setId(hackerNewsStoryDTO.getId());
        storyEntity.setScore(hackerNewsStoryDTO.getScore());
        storyEntity.setTime(hackerNewsStoryDTO.getTime());
        storyEntity.setTitle(hackerNewsStoryDTO.getTitle());
        storyEntity.setUrl(hackerNewsStoryDTO.getUrl());
        storyEntity.setType(hackerNewsStoryDTO.getType());
        storyEntity.setTopStory(true);
        storyJpaRepository.save(storyEntity);
    }


    public void saveComment(HackerNewsCommentDTO hackerNewsCommentDTO, UsersEntity usersEntity, StoryEntity storyEntity) {
        CommentEntity commentEntity = commentJpaRepository.findById(hackerNewsCommentDTO.getId()).orElseGet(CommentEntity::new);
        commentEntity.setUser(usersEntity);
        commentEntity.setStory(storyEntity);
        commentEntity.setId(hackerNewsCommentDTO.getId());
        commentEntity.setParent(hackerNewsCommentDTO.getParent());
        commentEntity.setText(hackerNewsCommentDTO.getText());
        commentEntity.setTime(hackerNewsCommentDTO.getTime());
        commentEntity.setTotalComments(hackerNewsCommentDTO.getTotalNumberOfComments());
        commentEntity.setType(hackerNewsCommentDTO.getType());
        commentJpaRepository.save(commentEntity);
    }

    public UsersEntity saveUser(HackerNewUserDTO userDTO) {
        UsersEntity usersEntity = usersJpaRepository.findByUserHandle(userDTO.getId()).orElseGet(UsersEntity::new);
        usersEntity.setUserHandle(userDTO.getId());
        usersEntity.setAccountCreated(userDTO.getCreated());
        usersEntity.setKarma(userDTO.getKarma());
        return usersJpaRepository.save(usersEntity);

    }


    public StoryEntity getStoryById(int storyId) {
        return storyJpaRepository.findById(storyId).orElse(null);
    }

    public void updateTopStory() {
        storyJpaRepository.updateByTopStory(false);
    }

    public List<StoryEntity> getTopStories() {
        return storyJpaRepository.findByIsTopStory(true).orElse(null);
    }

    public List<StoryEntity> getAllStories() {
        return storyJpaRepository.findAll();
    }

    public List<CommentEntity> getCommentByStoryId(String storyId) {
        return commentJpaRepository.getByStoryId(storyId);
    }

    public Optional<UsersEntity> getUserByHandle(String userHandle) {
        return usersJpaRepository.findByUserHandle(userHandle);
    }
}

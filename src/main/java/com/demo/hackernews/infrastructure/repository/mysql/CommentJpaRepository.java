package com.demo.hackernews.infrastructure.repository.mysql;

import com.demo.hackernews.infrastructure.enties.mysql.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentJpaRepository extends JpaRepository<CommentEntity,Integer> {

    @Query(value = "select * from  comments h where story_id = :storyId", nativeQuery = true)
    List<CommentEntity> getByStoryId(String storyId);
}

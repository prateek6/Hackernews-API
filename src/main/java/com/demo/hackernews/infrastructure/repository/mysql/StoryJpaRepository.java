package com.demo.hackernews.infrastructure.repository.mysql;

import com.demo.hackernews.infrastructure.enties.mysql.StoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoryJpaRepository extends JpaRepository<StoryEntity,Integer>  {

    @Transactional
    @Modifying
    @Query(value = "update story h set is_top_story = :isTopStory", nativeQuery = true)
    int updateByTopStory(boolean isTopStory);

    Optional<List<StoryEntity>> findByIsTopStory(boolean b);
}

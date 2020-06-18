package com.demo.hackernews.infrastructure.schedulers;

import com.demo.hackernews.domain.stories.impl.HackerNewsStoryServiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class HackerNewsScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(HackerNewsScheduler.class.getName());

    private static final int DELAY_10_MINUTES = 10 * 60 * 1000;

    @Autowired
    private HackerNewsStoryServiceService hackerNewsStoryServiceService;

    @Scheduled(fixedDelay = DELAY_10_MINUTES)
    public void scheduleHackerNewsStoriesUpdate() {
        LOGGER.info(" Running hacker news stories scheduled update");
        hackerNewsStoryServiceService.saveTopStories();
        LOGGER.info("Hacker new stories update completed");
    }

}

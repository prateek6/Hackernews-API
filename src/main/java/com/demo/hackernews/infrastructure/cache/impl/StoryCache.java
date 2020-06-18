package com.demo.hackernews.infrastructure.cache.impl;

import com.demo.hackernews.infrastructure.dto.HackerNewsStoryDTO;
import com.demo.hackernews.infrastructure.cache.Cache;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class StoryCache implements Cache<HackerNewsStoryDTO> {
    private static final int STORY_CACHE_LENGTH = 10;

    PriorityQueue<HackerNewsStoryDTO> hackerNewsStoryDTOPriorityQueue ;
    Set<HackerNewsStoryDTO> hackerNewsStoryDTOSet ;

    public StoryCache(){
        //To get and remove Min score value of HackerNewsStoryDTO, time complexity O(1) to get max or min
        hackerNewsStoryDTOPriorityQueue = new PriorityQueue<HackerNewsStoryDTO>((hackerNewsStoryDTO1,hackerNewsStoryDTO2)->{
            int count1=Objects.isNull(hackerNewsStoryDTO1.getScore())?0:hackerNewsStoryDTO1.getScore();
            int count2=Objects.isNull(hackerNewsStoryDTO2.getScore())?0:hackerNewsStoryDTO2.getScore();
            return Integer.compare(count1, count2);
        }) ;

        //To Avoid duplicate values in Priority Queue, works based on hash, Time complexity to check duplicate value O(1)
        //If i use Priority contains method the time complexity is O(n)
        hackerNewsStoryDTOSet = new HashSet<>(STORY_CACHE_LENGTH);
    }


    @Override
    public List<HackerNewsStoryDTO> getCache() {
        if (!Objects.isNull(hackerNewsStoryDTOPriorityQueue)) {
            return new ArrayList<>(hackerNewsStoryDTOPriorityQueue);
        }
        return new ArrayList<>();
    }


    public synchronized void addToCache(HackerNewsStoryDTO hackerNewsStoryDTO){

        if(hackerNewsStoryDTO!=null && !hackerNewsStoryDTOSet.contains(hackerNewsStoryDTO)) {
            hackerNewsStoryDTOPriorityQueue.offer(hackerNewsStoryDTO);
            hackerNewsStoryDTOSet.add(hackerNewsStoryDTO);
        }
        if(hackerNewsStoryDTOPriorityQueue.size()>STORY_CACHE_LENGTH){
            while (hackerNewsStoryDTOPriorityQueue.size() > STORY_CACHE_LENGTH) {
                HackerNewsStoryDTO newsStoryDTO = hackerNewsStoryDTOPriorityQueue.poll();
                hackerNewsStoryDTOSet.remove(newsStoryDTO);
            }
        }
    }

    @Override
    public void clearCache() {
        if(!hackerNewsStoryDTOPriorityQueue.isEmpty()){
            hackerNewsStoryDTOPriorityQueue.clear();
        }
    }
}

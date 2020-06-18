package com.demo.hackernews.infrastructure.cache.impl;

import com.demo.hackernews.infrastructure.dto.HackerNewsCommentDTO;
import com.demo.hackernews.infrastructure.cache.Cache;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CommentCache implements Cache<HackerNewsCommentDTO> {
    private static final int COMMENT_CACHE_LENGTH = 10;

    PriorityQueue<HackerNewsCommentDTO> hackerNewsCommentDTOPriorityQueue;
    Set<HackerNewsCommentDTO> hackerNewsCommentDTOSet;

    public CommentCache(){
        //To get and remove minimum score value of HackerNewsCommentDTO, time complexity O(1)
        hackerNewsCommentDTOPriorityQueue = new PriorityQueue<>((hackerNewsCommentDTO1,hackerNewsCommentDTO2) ->{
            int count1=Objects.isNull(hackerNewsCommentDTO1.getKids())?0:hackerNewsCommentDTO1.getKids().size();
            int count2=Objects.isNull(hackerNewsCommentDTO2.getKids())?0:hackerNewsCommentDTO2.getKids().size();
            return Integer.compare(count1, count2);
        }) ;

        //To Avoid duplicate values in Priority Queue, works based on hash, Time complexity to check duplicate value O(1)
        hackerNewsCommentDTOSet = new HashSet<>(COMMENT_CACHE_LENGTH);
    }


    @Override
    public List<HackerNewsCommentDTO> getCache() {
        if (!Objects.isNull(hackerNewsCommentDTOPriorityQueue)) {
            return new ArrayList<>(hackerNewsCommentDTOPriorityQueue);
        }
        return new ArrayList<>();
    }


    public synchronized void addToCache(HackerNewsCommentDTO hackerNewsCommentDTO){
        if(hackerNewsCommentDTOPriorityQueue.size()>COMMENT_CACHE_LENGTH){
            while (hackerNewsCommentDTOPriorityQueue.size() > COMMENT_CACHE_LENGTH) {
                HackerNewsCommentDTO commentDTO = hackerNewsCommentDTOPriorityQueue.poll();
                hackerNewsCommentDTOSet.remove(commentDTO);
            }
        }
        if(hackerNewsCommentDTO!=null && !hackerNewsCommentDTOSet.contains(hackerNewsCommentDTO)) {
            hackerNewsCommentDTOPriorityQueue.offer(hackerNewsCommentDTO);
            hackerNewsCommentDTOSet.add(hackerNewsCommentDTO);
        }
    }

    @Override
    public void clearCache() {
        if(!hackerNewsCommentDTOPriorityQueue.isEmpty()){
            hackerNewsCommentDTOPriorityQueue.clear();
        }
    }
}

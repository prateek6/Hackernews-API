package com.demo.hackernews.infrastructure.cache;

import java.util.List;

public interface Cache<T> {

    public List<T> getCache();

    public void clearCache();
}

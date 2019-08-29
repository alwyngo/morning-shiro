package com.morning.morningshiro.config.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomizeCacheManager implements CacheManager {

    @Autowired
    private CustomizeCache customizeCache;

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return customizeCache;
    }
}

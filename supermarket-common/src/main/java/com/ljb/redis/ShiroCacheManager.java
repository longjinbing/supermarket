package com.ljb.redis;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/9/14<br>
 * 描述: <br>
 */
public class ShiroCacheManager implements CacheManager {

    private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<String, Cache>();
    // 注入Spring的缓存管理器
    @Autowired
    private org.springframework.cache.CacheManager cacheManager;

    private String cacheKeyPrefix = "shiro:";

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        Cache cache = caches.get(name);
        if (cache == null) {
            org.springframework.cache.Cache springCache = cacheManager.getCache(name);
            // 通过spring的缓存管理器，获取缓存，将缓存注入Redis的缓存中
            cache = new ShiroRedisCache(cacheKeyPrefix);
            caches.put(name, cache);
        }
        return cache;
    }
}

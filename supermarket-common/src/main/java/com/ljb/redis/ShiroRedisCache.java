package com.ljb.redis;

import com.ljb.entity.SysUser;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Set;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/9/14<br>
 * 描述: <br>
 */
public class ShiroRedisCache<K, V> implements Cache<K, V> {

    private String cacheKey;
    private RedisTemplate redisTemplate;


    public ShiroRedisCache(String cacheKey) {
        this.cacheKey=cacheKey;
        redisTemplate=redisTemplate();
    }

    public LettuceConnectionFactory lettuceConnectionFactory() {
        LettuceConnectionFactory redisConnectionFactory=new LettuceConnectionFactory();
        redisConnectionFactory.setHostName("127.0.0.1");
        redisConnectionFactory.setPort(6379);
        redisConnectionFactory.afterPropertiesSet();
        return redisConnectionFactory;
    }

    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory());
        return redisTemplate;
    }

    @Override
    public V get(K key) throws CacheException {
        BoundHashOperations<String,K,V> hash = redisTemplate.boundHashOps(cacheKey);
        Object k=hashKey(key);
        return hash.get(k);
    }

    @Override
    public V put(K key, V value) throws CacheException {
        BoundHashOperations<String,K,V> hash = redisTemplate.boundHashOps(cacheKey);
        Object k=hashKey(key);
        hash.put((K)k, value);
        return value;
    }

    @Override
    public V remove(K key) throws CacheException {
        BoundHashOperations<String,K,V> hash = redisTemplate.boundHashOps(cacheKey);
        Object k=hashKey(key);
        V value=hash.get(k);
        hash.delete(k);
        return value;
    }

    @Override
    public void clear() throws CacheException {
        redisTemplate.delete(cacheKey);
    }

    @Override
    public int size() {
        BoundHashOperations<String,K,V> hash = redisTemplate.boundHashOps(cacheKey);
        return hash.size().intValue();
    }

    @Override
    public Set<K> keys() {
        BoundHashOperations<String,K,V> hash = redisTemplate.boundHashOps(cacheKey);
        return hash.keys();
    }

    @Override
    public Collection<V> values() {
        BoundHashOperations<String,K,V> hash = redisTemplate.boundHashOps(cacheKey);
        return hash.values();
    }

    protected Object hashKey(K key) {

        if(key instanceof PrincipalCollection) {//此处很重要,如果key是登录凭证,那么这是访问用户的授权缓存;将登录凭证转为user对象,返回user的id属性做为hash key,否则会以user对象做为hash key,这样就不好清除指定用户的缓存了
            PrincipalCollection pc=(PrincipalCollection) key;
            SysUser user =(SysUser)pc.getPrimaryPrincipal();
            return user.getId();
        }
        return key;
    }
}

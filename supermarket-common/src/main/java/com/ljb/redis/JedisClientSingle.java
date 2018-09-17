package com.ljb.redis;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/9/14<br>
 * 描述: <br>
 */
public class JedisClientSingle {

    @Autowired
    private JedisPool jedisPool;

    public String get(String key) {//获取指定 key 的值。如果 key 不存在，返回 nil
        Jedis jedis = jedisPool.getResource();
        String string = jedis.get(key);
        jedis.close();
        return string;
    }

    public String set(String key, String value) {//设置一些字符串值
        Jedis jedis = jedisPool.getResource();
        String string = jedis.set(key, value);
        jedis.close();
        return string;
    }

    public void flushDB(){
        Jedis jedis = jedisPool.getResource();
        jedis.flushDB();
        jedis.close();
    }

    public Long size(){
        Jedis jedis = jedisPool.getResource();
        Long size=jedis.dbSize();
        jedis.close();
        return size;
    }

    public Set<String> keys(String pattern){
        Jedis jedis = jedisPool.getResource();
        Set<String> keys=jedis.keys(pattern);
        jedis.close();
        return keys;
    }


    public String hget(String hkey, String key) {//获取哈希表中指定字段的值
        Jedis jedis = jedisPool.getResource();
        String string = jedis.hget(hkey, key);
        jedis.close();
        return string;
    }

    public long hset(String hkey, String key, String value) {//为哈希表中的字段赋值
        Jedis jedis = jedisPool.getResource();
        long result = jedis.hset(hkey, key, value);
        jedis.close();
        return result;
    }


    public long incr(String key) {//将 key 中储存的数字值增一,如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行INCR操作
        Jedis jedis = jedisPool.getResource();
        long result = jedis.incr(key);
        jedis.close();
        return result;
    }

    public long expire(String key, int second) {//设置key的到期时间
        Jedis jedis = jedisPool.getResource();
        long result = jedis.expire(key, second);
        jedis.close();
        return result;
    }

    public long ttl(String key) {//以秒为单位返回 key 的剩余过期时间
        Jedis jedis = jedisPool.getResource();
        long result = jedis.ttl(key);
        jedis.close();
        return result;
    }

    public long del(String key) {//根据key删除
        Jedis jedis = jedisPool.getResource();
        long result = jedis.del(key);
        jedis.close();
        return result;
    }

    public long hdel(String hkey, String key) {//删除哈希表key中的一个或多个指定字段
        Jedis jedis = jedisPool.getResource();
        long result = jedis.hdel(hkey, key);
        jedis.close();
        return result;
    }

}

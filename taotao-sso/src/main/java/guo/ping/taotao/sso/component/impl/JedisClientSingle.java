package guo.ping.taotao.sso.component.impl;

import guo.ping.taotao.sso.component.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * redis客户端单机版实现类
 */
public class JedisClientSingle implements JedisClient {

    @Autowired
    private JedisPool jedisPool;

    @Value("${REDIS_PASSWORD}")
    private String REDIS_PASSWORD;

    private Jedis getJedisResource(JedisPool jedisPool) {
        Jedis jedis = jedisPool.getResource();
        jedis.auth(REDIS_PASSWORD);
        return jedis;
    }

    @Override
    public String set(String key, String value) {
        Jedis jedis = getJedisResource(jedisPool);
        String result = jedis.set(key, value);
        jedis.close();
        return result;
    }

    @Override
    public String get(String key) {
        Jedis jedis = getJedisResource(jedisPool);
        String result = jedis.get(key);
        jedis.close();
        return result;
    }

    @Override
    public Long hset(String key, String item, String value) {
        Jedis jedis = getJedisResource(jedisPool);
        Long result = jedis.hset(key, item, value);
        jedis.close();
        return result;
    }

    @Override
    public String hget(String key, String item) {
        Jedis jedis = getJedisResource(jedisPool);
        String result = jedis.hget(key, item);
        jedis.close();
        return result;
    }

    @Override
    public Long incr(String key) {
        Jedis jedis = getJedisResource(jedisPool);
        Long result = jedis.incr(key);
        jedis.close();
        return result;
    }

    @Override
    public Long decr(String key) {
        Jedis jedis = getJedisResource(jedisPool);
        Long result = jedis.decr(key);
        jedis.close();
        return result;
    }

    @Override
    public Long expire(String key, int second) {
        Jedis jedis = getJedisResource(jedisPool);
        Long result = jedis.expire(key, second);
        jedis.close();
        return result;
    }

    @Override
    public Long ttl(String key) {
        Jedis jedis = getJedisResource(jedisPool);
        Long result = jedis.ttl(key);
        jedis.close();
        return result;
    }

    @Override
    public Long hdel(String key, String item) {
        Jedis jedis = getJedisResource(jedisPool);
        Long result = jedis.hdel(key, item);
        jedis.close();
        return result;
    }
}

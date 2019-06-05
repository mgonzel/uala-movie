package services;

import redis.clients.jedis.JedisPool;

public class RedisService {

    private static RedisService instance = new RedisService();
    public static RedisService getInstance(){return instance;}

    JedisPool jedisPool;
    private RedisService(){
        this.jedisPool = new JedisPool("redis.local");
    }

    public void setValue(String key, String value) {
        jedisPool.getResource().set(key, value);
    }


    public String get(String key) {
        return jedisPool.getResource().get(key);
    }
}

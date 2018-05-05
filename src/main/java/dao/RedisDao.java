package dao;

import base.Constants;
import redis.clients.jedis.Jedis;

/**
 * @author guyue
 * @date 2018/4/28
 */
public class RedisDao {
    private volatile static Jedis redis;

    public static Jedis getRedis() {
        if (redis == null) {
            synchronized (RedisDao.class) {
                if (redis == null) {
                    redis = new Jedis(Constants.REDIS_HOST, Constants.REDIS_PORT);
                }
            }
        }
        return redis;
    }
}
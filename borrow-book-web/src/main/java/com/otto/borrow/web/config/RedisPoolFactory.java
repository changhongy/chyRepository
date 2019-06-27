package com.otto.borrow.web.config;
/**
 * Project Name：seckilling
 * File Name：RedisPoolFactory
 * Package Name：com.seckilling.jedis.config
 * Date：2018/8/5 20:04
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author 张辉
 * @Title：
 * @Description：
 * @Package com.seckilling.jedis.config
 * @ClassName RedisPoolFactory
 * @date 2018/8/5 20:04
 */
@Configuration
public class RedisPoolFactory {

    private final RedisProperties properties;

    @Autowired
    public RedisPoolFactory(RedisProperties properties) {
        this.properties = properties;
    }

    @Bean(destroyMethod = "close")
    public JedisPool getJedisPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(properties.getJedis().getPool().getMaxIdle());
        config.setMinIdle(properties.getJedis().getPool().getMinIdle());
        config.setMaxWaitMillis(properties.getJedis().getPool().getMaxWait().toMillis());
        config.setMaxTotal(properties.getJedis().getPool().getMaxActive());
        return new JedisPool(config, properties.getHost(), properties.getPort(),
                (int) properties.getTimeout().getSeconds(), properties.getPassword());
//                0);
    }
}

package com.huang.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.huang.config.RedisConfig;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
@Configuration
public class JedisPoolResourceConfig {
    @Autowired
    RedisConfig redisConfig;
    
    @Bean
    public JedisPool JedisPoolFactory() {
        JedisPoolConfig jdConfig = new JedisPoolConfig();
        jdConfig.setMaxTotal(redisConfig.getPoolMaxTotal());
        jdConfig.setMaxIdle(redisConfig.getPoolMaxIdle());
        jdConfig.setMaxWaitMillis(redisConfig.getPoolMaxWait() * 1000);

        JedisPool jp = new JedisPool(jdConfig, redisConfig.getHost(), redisConfig.getPort(),
                redisConfig.getTimeout()*1000);
        return jp;
    }
}

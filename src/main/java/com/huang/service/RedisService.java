package com.huang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.huang.config.RedisConfig;
import com.huang.redis.key.KeyPrefix;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

//通过这个类提供Redis服务
@Service
public class RedisService {
    @Autowired
    RedisConfig redisConfig;
    @Autowired
    JedisPool jedisPool;

    public <T> T get(KeyPrefix keyPrefix,String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = keyPrefix.getPrefix() + key;
            String str = jedis.get(realKey);
            T t = stringToBean(str, clazz);
            return t;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
    
    public <T> T getByKey (String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey =  key;
            String str = jedis.get(realKey);
            T t = stringToBean(str, clazz);
            return t;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public <T> Boolean set(KeyPrefix keyPrefix,String key, T value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = keyPrefix.getPrefix() + key;
            String str = beanToStr(value);
            if (str == null || str.length() <= 0) {
                return false;
            }
            int seconds = keyPrefix.expireSecondes();
            
            if(seconds<= 0) {
                jedis.set(realKey, str);
            }else {
                jedis.setex(realKey, seconds, str);
            }
           
            return true;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    private <T> String beanToStr(T value) {
        if(value == null) {
          return null;  
        }
        Class<?> clazz = value.getClass();
        if (clazz == int.class || clazz == Integer.class) {
            return "" + value;
        } else if (clazz == String.class) {
            return (String) value;
        } else if (clazz == long.class || clazz == Long.class) {
            return "" + value;
        } else {
            return JSON.toJSONString(value);
        }
    }

    @SuppressWarnings("unchecked")
    private <T> T stringToBean(String str,Class<T> clazz) {
        if(str == null || clazz == null || str.length() <= 0) {
            return null;  
          }
          if (clazz == int.class || clazz == Integer.class) {
              return (T) Integer.valueOf(str);
          } else if (clazz == String.class) {
              return (T) String.valueOf(str);
          } else if (clazz == long.class || clazz == Long.class) {
              return (T) Long.valueOf(str);
          } else {
              return JSON.toJavaObject(JSON.parseObject(str), clazz);
          }
    }



}

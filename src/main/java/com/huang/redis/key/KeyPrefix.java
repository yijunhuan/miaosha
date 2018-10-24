package com.huang.redis.key;

public interface KeyPrefix {
    public int expireSecondes();
    
    public String getPrefix();
}

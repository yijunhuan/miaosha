package com.huang.redis.key;

public abstract class BasisPrefix implements KeyPrefix {
    private int expireSecondes;

    private String prefix;

    // expireSecondes:0 永久有效
    public BasisPrefix(String prexfix) {
        this.expireSecondes = 0;
        this.prefix = prexfix;
    }

    public BasisPrefix(int expireSeconds, String prefix) {
        this.expireSecondes = expireSeconds;
        this.prefix = prefix;
    }

    public int expireSecondes() {
        return this.expireSecondes;
    }

    public String getPrefix() {
        String className = this.getClass().getSimpleName();
        return className + ":" + prefix;
    }

}

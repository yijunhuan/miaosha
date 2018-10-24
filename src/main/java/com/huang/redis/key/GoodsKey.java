package com.huang.redis.key;

public class GoodsKey extends BasisPrefix {

    private GoodsKey(int expireSeconds ,String prexfix) {
        super(expireSeconds,prexfix);
    }

    public static GoodsKey getPrefixByGoods = new GoodsKey(20,"gs");
    
}

package com.huang.redis.key;

/**
 * 生成token 
 *
 */
public class Token extends BasisPrefix {

    private Token(int expireSeconds,String prexfix) {
        super(expireSeconds,prexfix);
    }
    
    public static final Token TOKEN_USER = new Token(60*15, "tk");
    

}

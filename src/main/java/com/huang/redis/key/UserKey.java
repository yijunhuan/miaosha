package com.huang.redis.key;

public class UserKey extends BasisPrefix {

    private UserKey(String prexfix) {
        super(prexfix);
    }

    public static UserKey getPrefixById = new UserKey("id");
    
    public static UserKey getPrefixByName = new UserKey("name");
}

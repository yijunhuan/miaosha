package com.huang.utils;

import java.util.UUID;

public class UUIdUtils {
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}

package com.huang.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.huang.redis.key.Token;
import com.huang.service.RedisService;

/**
 * 生成cookie
 *
 */
public class CreateCookie {
    public static <T> void addCookie(HttpServletResponse response,String token ,T user,RedisService redisService,
            String cookieName) {
        redisService.set(Token.TOKEN_USER, token, user);
        Cookie cookie = new Cookie(cookieName, token);
        cookie.setMaxAge(Token.TOKEN_USER.expireSecondes());
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}

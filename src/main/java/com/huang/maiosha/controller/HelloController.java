package com.huang.maiosha.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huang.miaosha.domain.User;
import com.huang.redis.key.UserKey;
import com.huang.service.RedisService;
import com.huang.utils.Result;

@Controller
public class HelloController {
    @Autowired
    RedisService redisService;
    
    @RequestMapping("/testhello")
    public String sayHello(Map<String,String> info) {
        info.put("info", "springBoot");
        return "hello";
    }
    
    @ResponseBody
    @RequestMapping("/redis/get")
    public Result<User> redisGet(){
        User name = redisService.get(UserKey.getPrefixByName, ""+1, User.class);
        return Result.success(name);
    }
    
    @ResponseBody
    @RequestMapping("/redis/set")
    public Result<Boolean> redisSet(){
        User user = new User(1,"Ben","19931010");
        redisService.set(UserKey.getPrefixByName, ""+1, user);
        return Result.success(true);
    }
}

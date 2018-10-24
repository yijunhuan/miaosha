package com.huang.maiosha.controller;


import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huang.miaosha.domain.LoginVa;
import com.huang.utils.CodeMsg;
import com.huang.utils.Result;

@Controller
public class LoginController {
    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login/do_login", method= {RequestMethod.POST})
    @ResponseBody
    public Result<Boolean> LoginData(LoginVa loginVa) {
        String mobile = loginVa.getMobile();
        String password = loginVa.getPassword();
        
        if(StringUtils.isEmpty(password)) {
            return Result.error(CodeMsg.PASSWORD_EMPTY);
        }
        if(StringUtils.isEmpty(mobile)) {
            return Result.error(CodeMsg.MODIBLE_EMPTY);
        }
        
        return Result.error(CodeMsg.SUCCESS);
    }
}

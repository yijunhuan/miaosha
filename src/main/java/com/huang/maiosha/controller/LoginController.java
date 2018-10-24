package com.huang.maiosha.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huang.miaosha.domain.MiaoShaUser;
import com.huang.miaosha.vo.LoginVa;
import com.huang.service.MiaoShaUserService;
import com.huang.utils.CodeMsg;
import com.huang.utils.Result;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    MiaoShaUserService miaoShaService;

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/do_login")
    @ResponseBody
    public Result<CodeMsg> LoginData(LoginVa loginVa, HttpServletRequest request, HttpServletResponse response) {

        String mobile = loginVa.getMobile();
        String password = loginVa.getPassword();

        logger.info("mobile:" + mobile);

        if (StringUtils.isEmpty(password)) {
            return Result.error(CodeMsg.PASSWORD_EMPTY);
        }
        if (StringUtils.isEmpty(mobile)) {
            return Result.error(CodeMsg.MODIBLE_EMPTY);
        }
        // 登录
        CodeMsg cm = miaoShaService.login(response, loginVa);
        if (cm.getCode() == 0) {
            return Result.success(CodeMsg.SUCCESS);
        } else {
            return Result.error(cm);
        }
    }

    @RequestMapping("/get")
    @ResponseBody
    public Result<MiaoShaUser> getMiaoShaUser(@Param("id") long id) {
        MiaoShaUser user = miaoShaService.getById(id);
        return Result.success(user);
    }

}

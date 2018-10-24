package com.huang.config.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.huang.miaosha.domain.MiaoShaUser;
import com.huang.service.MiaoShaUserService;
/**
 * 
 * 自定参数解析器（HandlerMethodArgumentResolver）
 */
import com.huang.service.RedisService;

@Service
public class UserArgumentResolvers implements HandlerMethodArgumentResolver {
    @Autowired
    RedisService redisService;
    @Autowired
    MiaoShaUserService miaoShaUserService;

    // 匹配参数
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> clazz = parameter.getParameterType();

        return clazz == MiaoShaUser.class;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);

        String paramToken = request.getParameter(MiaoShaUserService.COOKI_NAME_TOKEN);
        String cookies = getCookieValue(request, MiaoShaUserService.COOKI_NAME_TOKEN);

        if (StringUtils.isEmpty(paramToken) && StringUtils.isEmpty(cookies)) {
            return null;
        }
        String token = StringUtils.isEmpty(paramToken) ? cookies : paramToken;

        return miaoShaUserService.getByToken(response, token);
    }

    private String getCookieValue(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                return cookie.getValue();
            }
        }

        return null;
    }

}

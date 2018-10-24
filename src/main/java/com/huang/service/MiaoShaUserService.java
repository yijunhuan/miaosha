package com.huang.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.huang.miaosha.dao.MiaoShaUserDao;
import com.huang.miaosha.domain.MiaoShaUser;
import com.huang.miaosha.vo.LoginVa;
import com.huang.redis.key.Token;
import com.huang.utils.CodeMsg;
import com.huang.utils.CreateCookie;
import com.huang.utils.MD5Utilts;
import com.huang.utils.UUIdUtils;

@Service
public class MiaoShaUserService {

    public static final String COOKI_NAME_TOKEN = "token";

    @Autowired
    MiaoShaUserDao miaoShaUserDao;

    @Autowired
    RedisService redisService;

    public MiaoShaUser getById(long id) {
        return miaoShaUserDao.getById(id);
    }

    /**
     * 实现登录功能
     * 
     * @param response
     * @param loginVa
     * @return
     */
    public CodeMsg login(HttpServletResponse response, LoginVa loginVa) {
        if (loginVa == null) {
            return CodeMsg.SERVER_ERROR;
        }
        String mobile = loginVa.getMobile();
        String password = loginVa.getPassword();

        MiaoShaUser user = getById(Long.parseLong(mobile));
        if (user == null) {
            return CodeMsg.MODIBLE_NOT_EXIST;
        }
        // 验证密码
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();

        String calaPass = MD5Utilts.formPassToDaoPass(password, saltDB);

        if (!calaPass.equals(dbPass)) {
            return CodeMsg.PASSWORD_ERROR;
        }
        // 生成token
        String token = UUIdUtils.uuid();
        CreateCookie.addCookie(response, token, user, redisService, COOKI_NAME_TOKEN);

        return CodeMsg.SUCCESS;
    }

    /**
     * 按照token获得Miaosha对象
     * 
     * @param token
     * @return
     */
    public MiaoShaUser getByToken(HttpServletResponse response, String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        MiaoShaUser user = redisService.get(Token.TOKEN_USER, token, MiaoShaUser.class);
        // 延时token
        if (user != null) {
            CreateCookie.addCookie(response, token, user, redisService, COOKI_NAME_TOKEN);
        }

        return user;
    }
}

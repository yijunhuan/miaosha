package com.huang.maiosha.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.IContext;
import org.thymeleaf.spring4.context.SpringWebContext;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import com.huang.miaosha.domain.MiaoShaUser;
import com.huang.miaosha.vo.GoodsDetailVo;
import com.huang.miaosha.vo.GoodsVo;
import com.huang.redis.key.GoodsKey;
import com.huang.service.GoodsService;
import com.huang.service.RedisService;
import com.huang.utils.Result;

@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    GoodsService goodsService;

    @Autowired
    RedisService redisService;

    @Autowired
    ThymeleafViewResolver viewResolver;

    @RequestMapping(value = "/to_list", produces = "text/html")
    @ResponseBody
    public String list(Model model, HttpServletRequest request, HttpServletResponse response, MiaoShaUser miaoShaUser) {
        
        List<GoodsVo> goodsVo = goodsService.listGoodsVo();
        model.addAttribute("goodsList", goodsVo);

        // 取缓存
        String html = redisService.get(GoodsKey.getPrefixByGoods, "", String.class);
        if (!StringUtils.isEmpty(html)) {
            System.out.println("从缓存中获取");
            return html;
        }
        IContext context = new SpringWebContext(request, response, request.getServletContext(), request.getLocale(),
                model.asMap(), viewResolver.getApplicationContext());
        // 手动渲染
        html = viewResolver.getTemplateEngine().process("goods_list", context);

        // 存入缓存
        redisService.set(GoodsKey.getPrefixByGoods, "", html);

        return html;
    }

    @RequestMapping("/to_detail/{goodsId}")
    @ResponseBody
    public Result<GoodsDetailVo> detail(MiaoShaUser user, @PathVariable("goodsId") long goodsId) {

        GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(goodsId);

        long startAT = goodsVo.getStartDate().getTime();
        long endAT = goodsVo.getEndDate().getTime();

        long now = System.currentTimeMillis();
        // 秒杀状况 0:秒杀还未开始 2：秒杀已经结束
        int miaoshaStatus = 0;
        // 秒杀还有多少时间
        long remainSeconds = 0;

        if (now < startAT) {// 秒杀还没开始，倒计时
            miaoshaStatus = 0;
            remainSeconds = ((startAT - now) / 1000);
        } else if (now > endAT) {// 秒杀已经结束
            miaoshaStatus = 2;
            remainSeconds = -1;
        }

        GoodsDetailVo goodsDetailVo = new GoodsDetailVo();
        goodsDetailVo.setGoodsVo(goodsVo);
        goodsDetailVo.setUser(user);
        goodsDetailVo.setRemainSeconds(remainSeconds);
        goodsDetailVo.setMiaoshaStatus(miaoshaStatus);

        return Result.success(goodsDetailVo);
    }

}

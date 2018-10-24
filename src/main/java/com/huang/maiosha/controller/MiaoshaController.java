package com.huang.maiosha.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huang.miaosha.domain.MiaoShaOrder;
import com.huang.miaosha.domain.MiaoShaUser;
import com.huang.miaosha.domain.OrderInfo;
import com.huang.miaosha.vo.GoodsVo;
import com.huang.service.GoodsService;
import com.huang.service.MiaoShaService;
import com.huang.service.OrderService;
import com.huang.service.RedisService;
import com.huang.utils.CodeMsg;
import com.huang.utils.Result;

@Controller
@RequestMapping("/miaosha")
public class MiaoshaController {
    @Autowired
    GoodsService goodsService;

    @Autowired
    RedisService redisService;

    @Autowired
    OrderService orderService;

    @Autowired
    MiaoShaService miaoshaService;

    Logger logger = LoggerFactory.getLogger(MiaoshaController.class);

    @PostMapping("/do_miaosha")
    @ResponseBody
    public Result<OrderInfo> list(Model model, MiaoShaUser user, @RequestParam("goodsId") long goodsId) {
        model.addAttribute("user", user);

        if (user == null) {
            return Result.error(CodeMsg.MIAOSHA_NOT_LOGIN);
        }
        GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(goodsId);
        // 判断库存
        int goodsStock = goodsVo.getGoodsStock();
        if (goodsStock <= 0) {
            return Result.error(CodeMsg.MIAO_SHA_OVER);
        }
        // 判断是否已经秒杀到了
        MiaoShaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
        System.out.println(order);
        if (order != null) {
            return Result.error(CodeMsg.REPEATE_MIAOSHA);
        }
        // 减库存 下订单 写入秒杀订单
        OrderInfo orderInfo = miaoshaService.miaosha(user, goodsVo);
        
        return Result.success(orderInfo);
    }

}

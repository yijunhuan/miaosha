package com.huang.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huang.miaosha.domain.MiaoShaUser;
import com.huang.miaosha.domain.OrderInfo;
import com.huang.miaosha.vo.GoodsVo;

@Service
public class MiaoShaService {
    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    Logger logger = LoggerFactory.getLogger(getClass());

    @Transactional()
    public OrderInfo miaosha(MiaoShaUser user, GoodsVo goodsVo) {
        // 减库存 下订单 写入秒杀订单
        goodsService.reducestock(goodsVo);

        logger.info("库存是：" + goodsVo.getGoodsStock());
        // order_info miaosha_order
        OrderInfo orderInfo = orderService.createOrder(user, goodsVo);

        return orderInfo;
    }

}

package com.huang.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huang.miaosha.dao.OrderDao;
import com.huang.miaosha.domain.MiaoShaOrder;
import com.huang.miaosha.domain.MiaoShaUser;
import com.huang.miaosha.domain.OrderInfo;
import com.huang.miaosha.vo.GoodsVo;

@Service
public class OrderService {

    @Autowired
    OrderDao orderDao;
    
    /**
     * 
     * @param id  用户id
     * @param goodsId 秒杀物品的id
     * @return
     */
    public MiaoShaOrder getMiaoshaOrderByUserIdGoodsId(long id, long goodsId) {
        
        return orderDao.getMiaoshaOrderByUserIdGoodsId(id,goodsId);
    }

    /**
     * 创建秒杀订单
     * @param user
     * @param goodsVo
     */
    @Transactional
    public OrderInfo createOrder(MiaoShaUser user, GoodsVo goodsVo) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goodsVo.getId());
        orderInfo.setGoodsName(goodsVo.getGoodName());
        orderInfo.setGoodsPrice(goodsVo.getMiaoshaPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setUserId(user.getId());
        orderInfo.setStatus(0);
        //插入order订单
        long orderId = orderDao.insertOrderInfo(orderInfo);
        MiaoShaOrder miaoShaOrder = new MiaoShaOrder();
        miaoShaOrder.setGoodsId(goodsVo.getId());
        miaoShaOrder.setOrderId(orderId);
        miaoShaOrder.setUserId(user.getId());
        
        orderDao.insertMiaosha(miaoShaOrder);
        
        return orderInfo;
    }

}

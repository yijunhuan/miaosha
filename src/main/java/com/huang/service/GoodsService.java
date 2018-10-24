package com.huang.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huang.miaosha.dao.GoodsDao;
import com.huang.miaosha.domain.MiaoShaGoods;
import com.huang.miaosha.vo.GoodsVo;

@Service
public class GoodsService {
    @Autowired
    GoodsDao goodsDao;

    Logger logger = LoggerFactory.getLogger(GoodsService.class);

    /**
     * 
     * @return 获得所有商品
     */
    public List<GoodsVo> listGoodsVo() {
        return goodsDao.listGoodsVo();
    }

    /**
     * 通过物品id获得指定商品
     * 
     * @param id
     *            物品的id
     * @return 通过物品id获得指定商品
     */
    public GoodsVo getGoodsVoByGoodsId(long id) {
        GoodsVo goodsVo = goodsDao.getGoodsVoByGoodsId(id);
        return goodsVo;
    }

    /**
     * 减少库存
     * 
     * @param goodsVo
     *            商品值
     */
    @Transactional
    public void reducestock(GoodsVo goodsVo) {
        MiaoShaGoods goods = new MiaoShaGoods();
        goods.setGoodsId(goodsVo.getId());
        logger.info("执行减库存指令");
        logger.info(goods.toString());
        goodsDao.reduceStock(goods);
    }
}

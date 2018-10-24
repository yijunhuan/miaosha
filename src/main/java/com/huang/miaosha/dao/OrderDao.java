package com.huang.miaosha.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.huang.miaosha.domain.MiaoShaOrder;
import com.huang.miaosha.domain.OrderInfo;

@Mapper
public interface OrderDao { 

    @Select("SELECT  * FROM order_info WHERE user_id = #{userId} AND goods_id = #{goodsId}")
    MiaoShaOrder getMiaoshaOrderByUserIdGoodsId(@Param("userId")long userId, @Param("goodsId")long goodsId);

    @Insert("INSERT INTO order_info(user_id,goods_id,delivery_addr_id,goods_name,goods_count,goods_price,"
            + "order_channel,STATUS,create_date,pay_date) VALUES(#{userId},#{goodsId},#{deliveryAddrId},"
            + "#{goodsName},#{goodsCount},#{goodsPrice},#{orderChannel},#{status},#{createDate},#{payDate})")
    @SelectKey(keyColumn="id",keyProperty="id",resultType=long.class,before=false,statement="select last_insert_id()")
    long insertOrderInfo(OrderInfo orderInfo);

    @Insert("INSERT INTO miaosha_order(user_id,order_id,goods_id) VALUES(#{userId},#{orderId},#{goodsId})")
    void insertMiaosha(MiaoShaOrder miaoShaOrder);

    
    
}

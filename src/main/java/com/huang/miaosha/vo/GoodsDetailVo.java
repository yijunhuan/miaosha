package com.huang.miaosha.vo;

import com.huang.miaosha.domain.MiaoShaUser;

public class GoodsDetailVo {
    // 秒杀状况 0:秒杀还未开始 2：秒杀已经结束
    private int miaoshaStatus = 0;
    // 秒杀还有多少时间
    private long remainSeconds = 0;
    private GoodsVo goodsVo;
    private MiaoShaUser user;

    public int getMiaoshaStatus() {
        return miaoshaStatus;
    }

    public void setMiaoshaStatus(int miaoshaStatus) {
        this.miaoshaStatus = miaoshaStatus;
    }

    public long getRemainSeconds() {
        return remainSeconds;
    }

    public void setRemainSeconds(long remainSeconds) {
        this.remainSeconds = remainSeconds;
    }

    public GoodsVo getGoodsVo() {
        return goodsVo;
    }

    public void setGoodsVo(GoodsVo goodsVo) {
        this.goodsVo = goodsVo;
    }

    public MiaoShaUser getUser() {
        return user;
    }

    public void setUser(MiaoShaUser user) {
        this.user = user;
    }

}

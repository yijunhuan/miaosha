package com.huang.utils;

public class CodeMsg {
    private int code;
    private String msg;

    public int getCode() {
        return code;
    }
    
    //通用异常
    public final static CodeMsg SUCCESS = new CodeMsg(0,"success");
    public final static CodeMsg SERVER_ERROR = new CodeMsg(500100,"服务端异常");
    
    //登录模块 5002XX
    public final static CodeMsg PASSWORD_EMPTY = new CodeMsg(500210,"手机密码不能为空");
    public final static CodeMsg MODIBLE_EMPTY = new CodeMsg(500212,"手机号码不能为空");
    public final static CodeMsg MODIBLE_NOT_EXIST = new CodeMsg(500214,"不存在这个号码");
    public final static CodeMsg PASSWORD_ERROR = new CodeMsg(500215,"输入的密码有误");
    
    //商品模块 5003XX
    
    //订单模块 5004XX
    
    //秒杀模块 5005XX
    public final static CodeMsg MIAO_SHA_OVER = new CodeMsg(500500,"商品已经秒杀完毕");
    public final static CodeMsg REPEATE_MIAOSHA = new CodeMsg(500501,"不能重复秒杀");
    public final static CodeMsg MIAOSHA_NOT_LOGIN = new CodeMsg(500502,"没有登录");

    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CodeMsg() {
        super();
        // TODO Auto-generated constructor stub
    }

}

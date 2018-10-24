package com.huang.utils;

public class CodeMsg {
    private int code;
    private String msg;

    public int getCode() {
        return code;
    }
    
    //通用异常
    public final static CodeMsg SUCCESS = new CodeMsg(0,"success");
    
    //登录模块 5002XX
    public final static CodeMsg PASSWORD_EMPTY = new CodeMsg(500210,"手机密码不能为空");
    public final static CodeMsg MODIBLE_EMPTY = new CodeMsg(500210,"手机号码不能为空");
    //商品模块 5003XX
    
    //订单模块 5004XX

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

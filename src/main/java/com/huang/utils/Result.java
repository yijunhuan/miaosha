package com.huang.utils;

public class Result<T> {
    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public static <T> Result<T> success(T data){
        return new Result<T>(data);
    }
    
    /**
     * @param data 结果值是成功
     * @param code 状态码是0
     */
    private Result(T data) {
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }
    
    /**
     * 
     * @param cm  结果返回值是失败
     * @param code 状态码
     */
    private Result(CodeMsg cm) {
        this.code = cm.getCode();
        this.msg = cm.getMsg();
    }

    public static <T> Result<T> error(CodeMsg cm){
        return new Result<T>(cm);
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}

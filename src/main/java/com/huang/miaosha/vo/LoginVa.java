package com.huang.miaosha.vo;

public class LoginVa {
    private String mobile;
    private String password;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginVa() {
        super();
        // TODO Auto-generated constructor stub
    }

    public LoginVa(String mobile, String password) {
        super();
        this.mobile = mobile;
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginVa [mobile=" + mobile + ", password=" + password + "]";
    }

}

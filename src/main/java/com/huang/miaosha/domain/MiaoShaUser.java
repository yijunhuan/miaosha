package com.huang.miaosha.domain;

import java.util.Date;

public class MiaoShaUser {
    private long id;
    private String nackname;
    private String password;
    private String salt;
    private String head;
    private Date registerTime;
    private Date lastLoginDate;
    private int loginCount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNackname() {
        return nackname;
    }

    public void setNackname(String nackname) {
        this.nackname = nackname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public int getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(int loginCount) {
        this.loginCount = loginCount;
    }

    @Override
    public String toString() {
        return "MiaoShaUser [id=" + id + ", nackname=" + nackname + ", password=" + password + ", salt=" + salt
                + ", head=" + head + ", registerTime=" + registerTime + ", lastLoginDate=" + lastLoginDate
                + ", loginCount=" + loginCount + "]";
    }

    
}

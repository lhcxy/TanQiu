package com.tanqiu.entity;

public class LoginEntity {
    String token ;
    String loginName;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getAcatar() {
        return acatar;
    }

    public void setAcatar(String acatar) {
        this.acatar = acatar;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    String nickName;
    String realName;
    String acatar;

}

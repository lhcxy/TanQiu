package com.tanqiu.entity;

public class BaseEntity {
    int status;
    String data;

    public void setData(String data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public String getData() {
        return data;
    }

    String msg;

    public int getStatus() {
        return status;
    }
}

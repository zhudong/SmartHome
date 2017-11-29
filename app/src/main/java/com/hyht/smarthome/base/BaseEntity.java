package com.hyht.smarthome.base;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-11-29.
 */

public class BaseEntity<T> implements Serializable{
    private static final int SUCCESS_CODE = 1;
    private int code;
    private String msg;
    private T data;

    public boolean isSuccess(){
        return getCode() == SUCCESS_CODE;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

package com.example.exception;

import java.io.Serializable;

/**
 * @author xianzhi.wang
 * @date 2017/12/7 -14:56
 */
public class Response implements Serializable {

    public static final Integer OK = 0;
    public static final Integer ERROR = 100;

    private int code;
    private String message;
    private Object data;
    private String reserve;
    private int costTime;

    public static Response getFailInstance(int code, String message, Object data) {
        Response response = new Response();
        response.setCode(code);
        response.setMessage(message);
        response.setData(data);
        response.setReserve("");
        response.setCostTime(0);
        return response;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve;
    }

    public int getCostTime() {
        return costTime;
    }

    public void setCostTime(int costTime) {
        this.costTime = costTime;
    }
}

package com.example.model;

import java.io.Serializable;

/**
 * @author xianzhi.wang
 * @date 2017/12/7 -10:37
 */
public class TokenInfo implements Serializable {
    private long timestamp;
    private String reserve;
    private String randomCode;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve;
    }

    public String getRandomCode() {
        return randomCode;
    }

    public void setRandomCode(String randomCode) {
        this.randomCode = randomCode;
    }
}

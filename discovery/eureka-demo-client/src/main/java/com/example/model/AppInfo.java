package com.example.model;

import java.io.Serializable;

/**
 * @author xianzhi.wang
 * @date 2017/12/7 -10:35
 */
public class AppInfo implements Serializable {

    private String appkey;
    private String client;
    private String clientVersion;
    private String clientType;

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }
}

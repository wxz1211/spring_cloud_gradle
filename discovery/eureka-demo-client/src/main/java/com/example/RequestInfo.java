package com.example;


import org.springframework.http.HttpMethod;

import java.util.Map;

/**
 * @author xianzhi.wang
 * @date 2017/12/6 -17:47
 */
public class RequestInfo {

    private HttpMethod httpMethod;
    private String path;
    private Map<String, Object> args;
    private byte[] content;

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, Object> getArgs() {
        return args;
    }

    public void setArgs(Map<String, Object> args) {
        this.args = args;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}

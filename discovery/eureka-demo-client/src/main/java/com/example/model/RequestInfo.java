package com.example.model;


import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpMethod;

import java.io.Serializable;
import java.util.Map;

/**
 * @author xianzhi.wang
 * @date 2017/12/6 -17:47
 */
public class RequestInfo implements Serializable {

    private TokenInfo tokenInfo;

    private AppInfo appInfo;

    private String methodName;

    private HttpMethod httpMethod;

    private JSONObject args;


    /**
     * 如果是get请求，那么是url里面所有的参数，如果是post的请求，是url里面所有的参数加上表单里的所有参数
     */
    private Map<String, Object> requestParameters;


    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public JSONObject getArgs() {
        return args;
    }

    public void setArgs(JSONObject args) {
        this.args = args;
    }

    public Map<String, Object> getRequestParameters() {
        return requestParameters;
    }

    public void setRequestParameters(Map<String, Object> requestParameters) {
        this.requestParameters = requestParameters;
    }

    public TokenInfo getTokenInfo() {
        return tokenInfo;
    }

    public void setTokenInfo(TokenInfo tokenInfo) {
        this.tokenInfo = tokenInfo;
    }

    public AppInfo getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(AppInfo appInfo) {
        this.appInfo = appInfo;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }


}

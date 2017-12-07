package com.example.inspector;

import com.example.exception.ClientException;
import com.example.model.RequestInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xianzhi.wang
 * @date 2017/12/7 -10:16
 */
public interface Inspector {
    /**
     * 参数校验
     *
     * @param httpServletRequest
     * @param requestInfo
     * @throws Exception
     */
    void check(HttpServletRequest httpServletRequest, RequestInfo requestInfo) throws ClientException;
}

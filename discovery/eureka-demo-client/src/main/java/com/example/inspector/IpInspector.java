package com.example.inspector;

import com.example.exception.ClientException;
import com.example.model.RequestInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xianzhi.wang
 * @date 2017/12/7 -10:17
 */
public class IpInspector implements Inspector  {

    @Override
    public void check(HttpServletRequest httpServletRequest, RequestInfo requestInfo) throws ClientException {


    }
}

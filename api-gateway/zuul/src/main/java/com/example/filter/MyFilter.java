package com.example.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.fallback.Response;
import com.example.log.DbLog;
import com.example.log.LogInfo;
import com.example.log.feign.ILogService;
import com.example.util.ZuulUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.REQUEST_URI_KEY;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SIMPLE_HOST_ROUTING_FILTER_ORDER;

/**
 * @author xianzhi.wang
 */
@Component
public class MyFilter extends ZuulFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyFilter.class);
    @Autowired
    private ILogService logService;


    @PostConstruct
    public void init() {
        //初始化鉴权
    }

    /**
     * pre：路由之前
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * filterOrder：过滤的顺序
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return SIMPLE_HOST_ROUTING_FILTER_ORDER + 1;
    }

    /**
     * shouldFilter：这里可以写逻辑判断，是否要过滤，本文true,永远过滤。
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * run：过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问。
     *
     * @return
     */
    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();

        HttpServletRequest request = requestContext.getRequest();
        String postString = null;
        try {
            ServletInputStream servletInputStream = request.getInputStream();
            postString = IOUtils.toString(servletInputStream, "utf-8");
            LOGGER.info(postString);
        } catch (IOException e) {
            return null;
        }
        String remoteAddress = request.getRemoteAddr();
        String localAddr = request.getLocalAddr();
        String pathInfo = request.getPathInfo();

        LOGGER.info("{} ,{} , {}", remoteAddress, localAddr, pathInfo);
        String method = "";
        String serviceName = "";
        try {
            JSONObject jsonObject = JSONObject.parseObject(postString);
            method = jsonObject.get("method").toString();
            serviceName = jsonObject.get("serviceName").toString();
        } catch (Exception e) {
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(401);
            try {
                requestContext.getResponse().getWriter().write(JSON.toJSONString(Response.getFailInstance(200, "wrong data", "")));
            } catch (Exception e1) {

            }
            return null;
        }


        String clientIp = remoteAddress;
        String httpMethod = request.getMethod();
        String path = ZuulUtil.parsePath(request.getRequestURI());
        Map<String, Object> args = ZuulUtil.parseParam(request.getRequestURI());

       if(StringUtils.isEmpty(path)||path.length()==1){

            requestContext.put(REQUEST_URI_KEY, "/" + method);

            URL url = null;
            try {
                url = new URL(request.getRequestURL().toString()+serviceName);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            requestContext.setRouteHost(url);
        }else{
            requestContext.put(REQUEST_URI_KEY, "/" + method);
        }
        LOGGER.debug("recv path:{}", request.getRequestURL().toString() + method);

        LOGGER.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString() + "/" + method));
        LOGGER.info(String.format("%s >>> %s", request.getMethod(), request.getServletPath() + "/" + method));

        DbLog.getInstance().setLogService(logService).offerQueue(new LogInfo("time :  " + System.currentTimeMillis() + "token is empty"));
        //requestContext.setSendZuulResponse(false);
        //requestContext.setResponseStatusCode(401);
//        try {
//            requestContext.getResponse().getWriter().write("token is empty");
//        } catch (Exception e) {
//
//        }

        //LOGGER.info("ok");
        return null;
    }

}

package com.example.filter;

import com.example.log.DbLog;
import com.example.log.LogInfo;
import com.example.log.feign.ILogService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xianzhi.wang
 */
@Component
public class MyFilter extends ZuulFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyFilter.class);
    @Autowired
    private ILogService logService;
    /**
     * pre：路由之前
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * filterOrder：过滤的顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * shouldFilter：这里可以写逻辑判断，是否要过滤，本文true,永远过滤。
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * run：过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问。
     * @return
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        LOGGER.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
        Object accessToken = request.getParameter("token");
        if (accessToken == null) {
            LOGGER.warn("token is empty");
            DbLog.getInstance().setLogService(logService).offerQueue(new LogInfo("time :  "+System.currentTimeMillis()+"token is empty"));
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                ctx.getResponse().getWriter().write("token is empty");
            } catch (Exception e) {

            }
            return null;
        }
        LOGGER.info("ok");
        return null;
    }
}

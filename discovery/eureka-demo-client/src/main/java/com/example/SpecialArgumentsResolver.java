package com.example;

import com.example.exception.ClientException;
import com.example.inspector.InspectorManager;
import com.example.model.RequestInfo;
import com.example.util.Constants;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xianzhi.wang
 * @date 2017/12/6 -17:44
 */
@Component
public class SpecialArgumentsResolver implements HandlerMethodArgumentResolver {

    private InspectorManager inspectorManager = InspectorManager.STANDARD;

    public SpecialArgumentsResolver(){

    }

    public SpecialArgumentsResolver(String value) {
        this.inspectorManager = InspectorManager.valueOf(value);
    }


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return RequestInfo.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws ClientException {
        final HttpServletRequest httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        RequestInfo requestInfo = new RequestInfo();
        httpServletRequest.setAttribute(Constants.REQUEST_INFO_TAG, requestInfo);
        try {
            inspectorManager.check(httpServletRequest, requestInfo);
        } catch (ClientException e) {
            throw e;
        }
        return requestInfo;
    }
}

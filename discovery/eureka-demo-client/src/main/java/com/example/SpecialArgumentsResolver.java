package com.example;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;

/**
 * @author xianzhi.wang
 * @date 2017/12/6 -17:44
 */
@Component
public class SpecialArgumentsResolver implements HandlerMethodArgumentResolver {


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return RequestInfo.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return this.readArguments(webRequest, parameter, parameter.getGenericParameterType());
    }

    private Object readArguments(NativeWebRequest webRequest, MethodParameter parameter, Type genericParameterType) {
        final HttpServletRequest httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        RequestInfo requestInfo = new RequestInfo();
        httpServletRequest.setAttribute("request_info", requestInfo);
        return requestInfo;
    }

}

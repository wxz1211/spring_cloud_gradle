package com.example.controller;

import com.example.exception.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ErrorViewResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;

/**
 * @author xianzhi.wang
 * @date 2017/12/7 -16:06
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class BasicErrorController extends AbstractErrorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicErrorController.class);
    private final ErrorProperties errorProperties;
    @Autowired
    private ApplicationContext applicationContext;

    public BasicErrorController(ErrorAttributes errorAttributes,
                                ErrorProperties errorProperties) {
        this(errorAttributes, errorProperties,
                Collections.<ErrorViewResolver>emptyList());
    }


    public BasicErrorController(ErrorAttributes errorAttributes,
                                ErrorProperties errorProperties, List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, errorViewResolvers);
        Assert.notNull(errorProperties, "ErrorProperties must not be null");
        this.errorProperties = errorProperties;
    }

    @Override
    public String getErrorPath() {
        return this.errorProperties.getPath();
    }

    @RequestMapping(produces = "text/html")
    public Response errorHtml() {
//        HttpStatus status = getStatus(request);
//        Map<String, Object> model = Collections.unmodifiableMap(getErrorAttributes(
//                request, isIncludeStackTrace(request, MediaType.TEXT_HTML)));
//        response.setStatus(status.value());
//        ModelAndView modelAndView = resolveErrorView(request, response, status, model);
//        return modelAndView == null ? new ModelAndView("error", model) : modelAndView;
        return Response.getFailInstance(Response.ERROR,"method not exist","");

    }


    @RequestMapping
    @ResponseBody
    public Response error() {
//        Map<String, Object> body = getErrorAttributes(request,
//                isIncludeStackTrace(request, MediaType.ALL));
//        HttpStatus status = getStatus(request);
//        return new ResponseEntity(body, status);
        return Response.getFailInstance(Response.ERROR,"method not exist","");
    }


//    protected boolean isIncludeStackTrace(HttpServletRequest request,
//                                          MediaType produces) {
//        ErrorProperties.IncludeStacktrace include = getErrorProperties().getIncludeStacktrace();
//        if (include == ErrorProperties.IncludeStacktrace.ALWAYS) {
//            return true;
//        }
//        if (include == ErrorProperties.IncludeStacktrace.ON_TRACE_PARAM) {
//            return getTraceParameter(request);
//        }
//        return false;
//    }

//    protected ErrorProperties getErrorProperties() {
//        return this.errorProperties;
//    }

}
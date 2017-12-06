//package com.example.filter;
//
//import com.alibaba.fastjson.JSONObject;
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import okhttp3.*;
//import okhttp3.internal.http.HttpMethod;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;
//import org.springframework.stereotype.Component;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.StreamUtils;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.Enumeration;
//import java.util.List;
//import java.util.Map;
//
//import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SIMPLE_HOST_ROUTING_FILTER_ORDER;
//
///**
// * @author xianzhi.wang
// * @date 2017/12/6 -15:56
// */
//@Component
//public class PostReqFilter extends ZuulFilter {
//
//    private static final String CONTENT_TYPE = "Content-Type";
//    @Autowired
//    private ProxyRequestHelper helper;
//
//    @Override
//    public String filterType() {
//        return "pre";
//    }
//
//    @Override
//    public int filterOrder() {
//        return SIMPLE_HOST_ROUTING_FILTER_ORDER - 1;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        return true;
////        return RequestContext.getCurrentContext().getRouteHost() != null &&
////                RequestContext.getCurrentContext().sendZuulResponse();
//    }
//
//    @Override
//    public Object run() {
//        OkHttpClient httpClient = new OkHttpClient.Builder()
//                // customize
//                .build();
//        RequestContext context = RequestContext.getCurrentContext();
//        HttpServletRequest request = context.getRequest();
//        String url = request.getRequestURL().toString();
//        String method = request.getMethod();
//        //String uri = this.helper.buildZuulRequestURI(request);
//        Headers.Builder headers = new Headers.Builder();
//        Enumeration<String> headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String name = headerNames.nextElement();
//            Enumeration<String> values = request.getHeaders(name);
//            while (values.hasMoreElements()) {
//                String value = values.nextElement();
//                headers.add(name, value);
//            }
//        }
//
//        InputStream inputStream = null;
//        try {
//            inputStream = request.getInputStream();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        RequestBody requestBody = null;
//        if (inputStream != null && HttpMethod.permitsRequestBody(method)) {
//            MediaType mediaType = null;
//            if (headers.get(CONTENT_TYPE) != null) {
//                mediaType = MediaType.parse(headers.get(CONTENT_TYPE));
//            }
//            try {
//                byte[] bytes = StreamUtils.copyToByteArray(inputStream);
//                JSONObject jsonObject = JSONObject.parseObject(new String(bytes, "UTF-8"));
//                String methodName = jsonObject.get("method").toString();
//                url += "/"+methodName;
//                requestBody = RequestBody.create(mediaType, bytes);
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    inputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        Request.Builder builder = new Request.Builder()
//                .headers(headers.build())
//                .url(url)
//                .method(method, requestBody);
//
//        Response response = null;
//        try {
//            response = httpClient.newCall(builder.build()).execute();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        LinkedMultiValueMap<String, String> responseHeaders = new LinkedMultiValueMap<>();
//        for (Map.Entry<String, List<String>> entry : response.headers().toMultimap().entrySet()) {
//            responseHeaders.put(entry.getKey(), entry.getValue());
//        }
//
//        try {
//            this.helper.setResponse(response.code(), response.body().byteStream(), responseHeaders);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        // prevent SimpleHostRoutingFilter from running
//        context.setRouteHost(null);
//        return null;
//    }
//
//}

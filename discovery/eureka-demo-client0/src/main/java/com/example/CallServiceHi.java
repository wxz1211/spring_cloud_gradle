package com.example;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xianzhi.wang
 * Feign中使用断路器
 * FeignClient的SchServiceHello接口的注解中加上fallback的指定类
 */
@FeignClient(value = "eureka-demo-client", fallback = HystricCallServiceHi.class)
public interface CallServiceHi {
    /**
     * sayHiFromClientOne
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    String sayHelloFromClientOne(@RequestParam(value = "name", required = false) String name);
}

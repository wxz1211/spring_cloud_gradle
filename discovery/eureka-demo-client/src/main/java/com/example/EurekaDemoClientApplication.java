package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @author xianzhi.wang
 */
@SpringBootApplication

@EnableEurekaClient
@EnableFeignClients

@EnableHystrixDashboard
@EnableCircuitBreaker

@RestController
public class EurekaDemoClientApplication extends WebMvcConfigurerAdapter {
    @Value("${server.port}")
    private int port;

    public static void main(String[] args) {
        SpringApplication.run(EurekaDemoClientApplication.class, args);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(new SpecialArgumentsResolver());
    }


    @RequestMapping("hi")
    public String hi(@RequestParam(required = false) String name) {
        if (name == null) {
            name = "client";
        }
        return "hi " + name + ", my port=" + port;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String dd(@RequestBody String name) {
        if (name == null) {
            name = "client";
        }
        return "hi " + name + ", my port=" + port;
    }

    @RequestMapping(value = "/hi2", method = RequestMethod.POST)
    public String hi2(@RequestBody String name) {
        if (name == null) {
            name = "client";
        }
        return "hi2 " + name + ", my port=" + port;
    }


    @RequestMapping("hello")
    public String hello(@RequestParam(required = false) String name) {
        if (name == null) {
            name = "client";
        }
        return "hello " + name + ", my port=" + port;
    }

}

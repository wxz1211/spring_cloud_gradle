package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xianzhi.wang
 */
@SpringBootApplication

@EnableEurekaClient
@EnableFeignClients

@EnableHystrixDashboard
@EnableCircuitBreaker

@RestController
public class EurekaDemoClientApplication {
    @Value("${server.port}")
    private int port;

    public static void main(String[] args) {
        SpringApplication.run(EurekaDemoClientApplication.class, args);
    }

    @RequestMapping("hi")
    public String hi(@RequestParam(required = false) String name) {
        if (name == null) {
            name = "client";
        }
        return "hi " + name + ", my port=" + port;
    }

    @RequestMapping("hello")
    public String hello(@RequestParam(required = false) String name) {
        if (name == null) {
            name = "client";
        }
        return "hello " + name + ", my port=" + port;
    }

}

package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
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
public class EurekaDemoClientApplication0 {
    private static final Logger LOGGER = LoggerFactory.getLogger(EurekaDemoClientApplication0.class);
    @Value("${server.port}")
    private int port;

    @Autowired
    private CallServiceHi hiServiceCaller;

    public static void main(String[] args) {
        SpringApplication.run(EurekaDemoClientApplication0.class, args);
    }

    @RequestMapping("hi")
    public String hi(@RequestParam(required = false) String name) {
        if (name == null) {
            name = "client0";
        }
        return "hi " + name + ", my port=" + port;
    }

    @RequestMapping("hello")
    public String hello(@RequestParam(required = false) String name) {
        if (name == null) {
            name = "client0";
        }
        return hiServiceCaller.sayHelloFromClientOne(name);
    }

    @RequestMapping("api/log/save")
    public String apiSave(@RequestParam(required = false) String message) {
        if (message == null) {
            message = "message";
        }
        LOGGER.info(".................message................");
        return ".................message.................";
    }




}

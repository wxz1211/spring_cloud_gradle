package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;

/**
 * @author xianzhi.wang
 */
@Controller
@EnableAutoConfiguration
@ComponentScan(
        basePackages = "com.example",
        excludeFilters =
                {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Order.class)}
)
@EnableEurekaClient
@EnableFeignClients

@EnableHystrixDashboard
@EnableCircuitBreaker
public class EurekaDemoClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaDemoClientApplication.class, args);
    }


}

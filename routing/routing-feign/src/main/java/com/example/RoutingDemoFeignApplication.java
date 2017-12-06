package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author xianzhi.wang
 * Feign支持可插拔的编码器和解码器。
 * Feign默认集成了Ribbon，并和Eureka结合，默认实现了负载均衡的效果
 * Feign 采用的是基于接口的注解
 * Feign 整合了ribbon
 *
 * EnableHystrixDashboard  监控台功能
 */
@SpringBootApplication

@EnableDiscoveryClient
@EnableFeignClients

@EnableHystrixDashboard
@EnableCircuitBreaker
public class RoutingDemoFeignApplication {
    public static void main(String[] args) {
        SpringApplication.run(RoutingDemoFeignApplication.class, args);
    }
}

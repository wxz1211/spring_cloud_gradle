package com.example;

import com.example.log.DbLog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;

/**
 * @author xianzhi.wang
 *
 * Zuul的主要功能是路由转发和过滤器。
 * 路由功能是微服务的一部分，
 * 比如／api/user转发到到user服务，
 * /api/shop转发到到shop服务。
 * zuul默认和Ribbon结合实现了负载均衡的功能。
 * 加上注解@EnableZuulProxy，开启zuul的功能
 */
@EnableDiscoveryClient
@EnableZuulProxy

@SpringBootApplication

@EnableFeignClients

@EnableHystrixDashboard
@EnableCircuitBreaker

@EnableZuulServer

public class ZuulApplication {
    public static void main(String[] args) {
        DbLog.getInstance().start();
        SpringApplication.run(ZuulApplication.class, args);
    }
}

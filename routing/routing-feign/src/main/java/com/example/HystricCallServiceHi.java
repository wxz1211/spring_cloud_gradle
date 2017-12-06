package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author xianzhi.wang
 */
@Component
public class HystricCallServiceHi implements CallServiceHi {

    private static final Logger LOGGER = LoggerFactory.getLogger(HystricCallServiceHi.class);

    @Override
    public String sayHiFromClientOne(String name) {
        LOGGER.error("fallback .... hi");
        return "{\"code\":\"fallback\"}";
    }
}

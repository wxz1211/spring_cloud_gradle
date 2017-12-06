package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xianzhi.wang
 * 在Web层的controller层，对外暴露一个”/hi”的API接口
 */
@RestController
public class HiController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HiController.class);

    @Autowired
    private CallServiceHi hiServiceCaller;

    @RequestMapping("hi")
    public String hi(@RequestParam(required = false) String name) {
        LOGGER.info("sayHiFromClientOne name {}", name);
        return hiServiceCaller.sayHiFromClientOne(name);
    }
}

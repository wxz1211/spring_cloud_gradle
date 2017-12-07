package com.example.controller;

import com.example.model.RequestInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @author xianzhi.wang
 * @date 2017/12/7 -15:40
 */
@RestController
public class TestController {
    @Value("${server.port}")
    private int port;

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

    @RequestMapping(value = "hi2", method = RequestMethod.POST)
    public RequestInfo hi2(RequestInfo requestInfo) {
        return requestInfo;
    }


    @RequestMapping("hello")
    public String hello(@RequestParam(required = false) String name) {
        if (name == null) {
            name = "client";
        }
        return "hello " + name + ", my port=" + port;
    }
}

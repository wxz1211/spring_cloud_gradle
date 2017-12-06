package com.example.log.feign;

import com.example.log.LogInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author xianzhi.wang
 */

@FeignClient("eureka-demo-client0")
public interface ILogService {
    /**
     * saveLog
     *
     * @param info
     */
    @RequestMapping(value = "/api/log/save", method = RequestMethod.POST)
    public void saveLog(LogInfo info);
}

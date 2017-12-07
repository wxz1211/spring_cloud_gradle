package com.example.inspector;

import com.alibaba.fastjson.JSONObject;
import com.example.exception.ClientException;
import com.example.model.RequestInfo;
import com.example.util.Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author xianzhi.wang
 * @date 2017/12/7 -13:41
 */
public class TimestampInspector implements Inspector {
    private static final Logger LOGGER = LoggerFactory.getLogger(TimestampInspector.class);

    @Override
    public void check(HttpServletRequest httpServletRequest, RequestInfo requestInfo) throws ClientException {
        Map<String, Object> parameters = requestInfo.getRequestParameters();

        Object args = parameters.get("args");

        if (args == null) {
            throw new ClientException("args empty");
        }

        requestInfo.setArgs(JSONObject.parseObject(args.toString()));

        long now = System.currentTimeMillis() / 1000;

        String value = parameters.get(Constants.TIMESTAMP_KEY).toString();
        if (StringUtils.isBlank(value)) {
            LOGGER.warn("timestamp {}", value);
            throw new ClientException("timestamp empty");
        }

        long time;
        try {
            time = Long.valueOf(value);
        } catch (NumberFormatException e) {
            LOGGER.warn("timestamp {}", value);
            throw new ClientException("NumberFormatException");
        }
        //超过5分钟
        if (Math.abs(time - now) > 300) {
            LOGGER.warn("timestamp {} overlimit", value);
            throw new ClientException("timestamp overlimit");
        }
    }
}

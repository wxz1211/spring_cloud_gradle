package com.example.inspector;

import com.example.exception.ClientException;
import com.example.model.RequestInfo;
import com.example.util.Constants;
import com.example.util.ParameterUtils;
import org.springframework.http.HttpMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author xianzhi.wang
 * @date 2017/12/7 -10:11
 */
public class InspectorManager {

    private final Inspector[] inspectors;

    private final String name;

    private static final List<InspectorManager> INSPECTOR_MANAGERS = new ArrayList<>();

    private InspectorManager(String name, Inspector[] inspectors) {
        this.inspectors = inspectors;
        this.name = Objects.requireNonNull(name);
        INSPECTOR_MANAGERS.add(this);
    }

    public void check(HttpServletRequest httpServletRequest, RequestInfo requestInfo) throws ClientException {
        Map<String, Object> parameters = ParameterUtils.getParameters(httpServletRequest);
        requestInfo.setRequestParameters(parameters);
        if(Constants.POST.equals(httpServletRequest.getMethod())){
            requestInfo.setHttpMethod(HttpMethod.POST);
        }else{
            requestInfo.setHttpMethod(HttpMethod.GET);
        }
        for (Inspector inspector : inspectors) {
            inspector.check(httpServletRequest, requestInfo);
        }
    }

    /**
     * 标准外部请求
     */
    public static final InspectorManager STANDARD = new InspectorManager("STANDARD",
            new Inspector[]{
                    new TimestampInspector(),
//                    new TokenInfoInspector(),
//                    new MetaDataInspector(),
                    new IpInspector()
//                    ,
//                    new FrequencyInspector(),
//                    new SignInspector()
    }
    );


    public static final InspectorManager valueOf(String name) {
        for (InspectorManager inspectorManager : INSPECTOR_MANAGERS) {
            if (inspectorManager.name.equalsIgnoreCase(name)) {
                return inspectorManager;
            }
        }
        throw new RuntimeException();
    }

}

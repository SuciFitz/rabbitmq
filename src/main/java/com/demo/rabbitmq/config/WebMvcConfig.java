package com.demo.rabbitmq.config;

import com.demo.rabbitmq.common.CommonResultReturnValueHandlerProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author szh 2020/6/22
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @PostConstruct
    void addApiResponseHandlerProxy() {
        List<HandlerMethodReturnValueHandler> returnValueHandlers = requestMappingHandlerAdapter.getReturnValueHandlers();
        if (returnValueHandlers == null || returnValueHandlers.isEmpty()) {
            return;
        }
        ArrayList<HandlerMethodReturnValueHandler> newReturnValueHandlers = new ArrayList<>(returnValueHandlers.size());
        returnValueHandlers.forEach(handler -> {
            if (handler instanceof RequestResponseBodyMethodProcessor) {
                newReturnValueHandlers.add(new CommonResultReturnValueHandlerProxy(handler));
            } else {
                newReturnValueHandlers.add(handler);
            }
        });
        requestMappingHandlerAdapter.setReturnValueHandlers(newReturnValueHandlers);
    }
}
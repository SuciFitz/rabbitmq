package com.demo.rabbitmq.common;

import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author szh 2020/6/22
 */
public class CommonResultReturnValueHandlerProxy implements HandlerMethodReturnValueHandler {

    private final HandlerMethodReturnValueHandler handler;

    private static final List<String> NO_WRAP_CLASSES = new ArrayList<>();

    static {
        NO_WRAP_CLASSES.add(CommonResult.class.toString());
    }

    public CommonResultReturnValueHandlerProxy(HandlerMethodReturnValueHandler handler) {
        this.handler = handler;
    }

    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        return handler.supportsReturnType(methodParameter);
    }

    @Override
    public void handleReturnValue(Object o, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) throws Exception {
        handler.handleReturnValue(wrapReturnValue(o), methodParameter, modelAndViewContainer, nativeWebRequest);
    }

    private Object wrapReturnValue(Object returnValue) {
        for (String str : NO_WRAP_CLASSES) {
            if (str.equals(returnValue.getClass().toString())) {
                return returnValue;
            }
        }
        return new CommonResult<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), returnValue);
    }
}
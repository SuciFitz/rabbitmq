package com.demo.rabbitmq.common;

/**
 * 封装API的错误码
 *
 * @author szh 2020/6/18
 */
public interface IErrorCode {

    /**
     * 获取错误代码
     *
     * @return 错误代码
     */
    long getCode();

    /**
     * 获取错误信息
     *
     * @return 错误信息
     */
    String getMessage();
}
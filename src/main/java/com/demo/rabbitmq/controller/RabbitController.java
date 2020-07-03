package com.demo.rabbitmq.controller;

import cn.hutool.core.thread.ThreadUtil;
import com.demo.rabbitmq.common.CommonResult;
import com.demo.rabbitmq.direct.DirectSender;
import com.demo.rabbitmq.fanout.FanoutSender;
import com.demo.rabbitmq.simple.SimpleSender;
import com.demo.rabbitmq.topic.TopicSender;
import com.demo.rabbitmq.work.WorkSender;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author szh 2020/6/17
 */
@Api(tags = "RabbitController")
@Controller
@RequestMapping("/rabbit")
public class RabbitController {

    @Autowired
    private SimpleSender simpleSender;

    @Autowired
    private WorkSender workSender;

    @Autowired
    private FanoutSender fanoutSender;

    @Autowired
    private DirectSender directSender;

    @Autowired
    private TopicSender topicSender;

    @ApiOperation("简单模式")
    @RequestMapping(value = "/simple", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Object> simpleTest() {
        for (int i = 0; i < 10; i++) {
            simpleSender.send();
            ThreadUtil.sleep(1000);
        }
        return CommonResult.success(null);
    }

    @ApiOperation("工作模式")
    @RequestMapping(value = "/work", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Object> workTest() {
        for (int i = 0; i < 10; i++) {
            workSender.send(i);
            ThreadUtil.sleep(1000);
        }
        return CommonResult.success(null);
    }

    @ApiOperation("发布/订阅模式")
    @RequestMapping(value = "/fanout", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Object> fanoutTest() {
        for (int i = 0; i < 10; i++) {
            fanoutSender.send(i);
            ThreadUtil.sleep(1000);
        }
        return CommonResult.success(null);
    }

    @ApiOperation("路由模式")
    @RequestMapping(value = "/direct", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Object> directTest() {
        for (int i = 0; i < 10; i++) {
            directSender.send(i);
            ThreadUtil.sleep(1000);
        }
        return CommonResult.success(null);
    }

    @ApiOperation("通配符模式")
    @RequestMapping(value = "/topic", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Object> topicTest() {
        for (int i = 0; i < 10; i++) {
            topicSender.send(i);
            ThreadUtil.sleep(1000);
        }
        return CommonResult.success(null);
    }

    @ApiOperation("测试返回值封装")
    @RequestMapping(value = "/testString", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<String> returnValueTest() {
        return CommonResult.success("null");
    }
}
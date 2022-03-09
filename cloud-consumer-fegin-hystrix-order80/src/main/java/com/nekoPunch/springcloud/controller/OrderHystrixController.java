package com.nekoPunch.springcloud.controller;


import com.nekoPunch.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "paymentGlobalFallbackMethod")
public class OrderHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id")Integer id){
        String result = paymentHystrixService.paymentInfo_OK(id);
        return  result;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand
   /* @HystrixCommand(fallbackMethod = "paymentTimeOutFallBackMethod",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
    })*/
    public String paymentInfo_TimeOut(@PathVariable("id")Integer id){
        String result = paymentHystrixService.paymentInfo_TimeOut(id);
        return  result;
    }

    public String paymentTimeOutFallBackMethod(@PathVariable("id") Integer id){
        return "消费者80，对方支付系统繁忙，请稍后再试 paymentTimeOutFallBackMethod";
    }

    public String paymentGlobalFallbackMethod(){
        return "消费者80，对方支付系统繁忙，请稍后再试 paymentGlobalFallbackMethod";
    }




}
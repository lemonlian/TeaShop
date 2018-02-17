package com.controller;

import com.service.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 测试读写分离
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Resource
    private TestService testService;

    @RequestMapping(value = "/test",method = RequestMethod.PUT)
    @ResponseBody
    public String test(HttpServletRequest request){
        testService.testWr(10,"Interp");
        return "test";
    }
}

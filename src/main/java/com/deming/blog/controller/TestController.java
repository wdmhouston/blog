package com.deming.blog.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//Controller for html page, RestController for txt data only.
public class TestController {

    @Value("${test.hello:Test}")
    private String testHello;

    /**
     * GET, POST, PUT, DELTE
     * /user?id=1
     * /user/1
     */
    @GetMapping("/hello")
    public String hello(){
        return "hello world!" + testHello;
    }

    @PostMapping("/hello/post")
    public String helloPost(String name){
        return "hello world! Post," + name;
    }
}

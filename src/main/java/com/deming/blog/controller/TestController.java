package com.deming.blog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//Controller for html page, RestController for txt data only.
public class TestController {

    /**
     * GET, POST, PUT, DELTE
     * /user?id=1
     * /user/1
     * @return txt message
     */
    @RequestMapping("/hello")
    public String hello(){
        return "hello world!";
    }
}

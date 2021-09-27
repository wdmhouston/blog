package com.deming.blog.service;

import com.deming.blog.domain.Demo;
import com.deming.blog.domain.Test;
import com.deming.blog.mapper.DemoMapper;
import com.deming.blog.mapper.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DemoService {

    @Resource  //or@autowire
    private DemoMapper demoMapper;

    public List<Demo> list() {
        return demoMapper.selectByExample(null);
    }

}

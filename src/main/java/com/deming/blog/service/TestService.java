package com.deming.blog.service;

import com.deming.blog.domain.Test;
import com.deming.blog.mapper.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestService {

    @Resource  //or@autowire
    private TestMapper testMapper;

    public List<Test> list() {
        return testMapper.list();
    }

}

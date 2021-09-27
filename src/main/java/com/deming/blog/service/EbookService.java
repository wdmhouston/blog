package com.deming.blog.service;

import com.deming.blog.domain.Demo;
import com.deming.blog.domain.Ebook;
import com.deming.blog.mapper.EbookMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {

    @Resource  //or@autowire
    private EbookMapper ebookMapper;

    public List<Ebook> list() {
        return ebookMapper.selectByExample(null);
    }

}

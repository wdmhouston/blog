package com.deming.blog.controller;

import com.deming.blog.domain.Demo;
import com.deming.blog.domain.Ebook;
import com.deming.blog.req.EbookReq;
import com.deming.blog.resp.CommonResp;
import com.deming.blog.resp.EbookResp;
import com.deming.blog.resp.PageResp;
import com.deming.blog.service.DemoService;
import com.deming.blog.service.EbookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/ebook")
public class EbookController {
    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public CommonResp list(EbookReq req){
        //CommonResp<List<EbookResp>> resp = new CommonResp<>();
        //List<EbookResp> list = ebookService.list(req);

        CommonResp<PageResp<EbookResp>> resp = new CommonResp<>();
        PageResp<EbookResp> list  = ebookService.list(req);
        resp.setContent(list);
        return resp;
    }
}

package com.deming.blog.controller;

import com.deming.blog.req.EbookQueryReq;
import com.deming.blog.req.EbookSaveReq;
import com.deming.blog.resp.CommonResp;
import com.deming.blog.resp.EbookQueryResp;
import com.deming.blog.resp.PageResp;
import com.deming.blog.service.EbookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/ebook")
public class EbookController {
    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public CommonResp list(@Valid EbookQueryReq req){
        //CommonResp<List<EbookResp>> resp = new CommonResp<>();
        //List<EbookResp> list = ebookService.list(req);
        CommonResp<PageResp<EbookQueryResp>> resp = new CommonResp<>();
        PageResp<EbookQueryResp> list  = ebookService.list(req);
        resp.setContent(list);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody EbookSaveReq req){
        //RequestBody is for content type json.
        //if it's form submit, no need to add @RequestBody
        CommonResp resp = new CommonResp<>();
        ebookService.save(req);
        return resp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id){
        //RequestBody is for content type json.
        //if it's form submit, no need to add @RequestBody
        CommonResp resp = new CommonResp<>();
        ebookService.delete(id);
        return resp;
    }
}

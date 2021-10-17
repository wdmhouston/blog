package com.deming.blog.controller;

import com.deming.blog.domain.Mlfruit;
import com.deming.blog.req.MlfruitQueryReq;
import com.deming.blog.resp.CommonResp;
import com.deming.blog.resp.MlfruitQueryResp;
import com.deming.blog.resp.PageResp;
import com.deming.blog.service.MlfruitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/mlfruit")
public class MlfruitController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private MlfruitService mlfruitService;

    /**
     * List prediction history
     * @param req
     * @return
     */
    @GetMapping("/list")
    public CommonResp list(@Valid MlfruitQueryReq req){
        CommonResp<PageResp<MlfruitQueryResp>> resp = new CommonResp<>();
        PageResp<MlfruitQueryResp> list  = mlfruitService.list(req);
        resp.setContent(list);
        return resp;
    }
}

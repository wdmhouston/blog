package com.deming.blog.controller;

import com.deming.blog.req.CategoryQueryReq;
import com.deming.blog.req.CategorySaveReq;
import com.deming.blog.resp.CommonResp;
import com.deming.blog.resp.CategoryQueryResp;
import com.deming.blog.resp.PageResp;
import com.deming.blog.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @GetMapping("all")
    public CommonResp all(){
        CommonResp<List<CategoryQueryResp>> resp = new CommonResp<>();
        List<CategoryQueryResp> list  = categoryService.all();
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/list")
    public CommonResp list(@Valid CategoryQueryReq req){
        //CommonResp<List<CategoryResp>> resp = new CommonResp<>();
        //List<CategoryResp> list = categoryService.list(req);
        CommonResp<PageResp<CategoryQueryResp>> resp = new CommonResp<>();
        PageResp<CategoryQueryResp> list  = categoryService.list(req);
        resp.setContent(list);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody CategorySaveReq req){
        //RequestBody is for content type json.
        //if it's form submit, no need to add @RequestBody
        CommonResp resp = new CommonResp<>();
        categoryService.save(req);
        return resp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id){
        //RequestBody is for content type json.
        //if it's form submit, no need to add @RequestBody
        CommonResp resp = new CommonResp<>();
        categoryService.delete(id);
        return resp;
    }
}

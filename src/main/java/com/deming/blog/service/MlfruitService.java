package com.deming.blog.service;

import com.deming.blog.domain.*;
import com.deming.blog.mapper.MlfruitMapper;
import com.deming.blog.req.MlfruitQueryReq;
import com.deming.blog.resp.MlfruitQueryResp;
import com.deming.blog.resp.PageResp;
import com.deming.blog.util.CopyUtil;
import com.deming.blog.util.SnowFlake;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MlfruitService {
    private static final Logger LOG = LoggerFactory.getLogger(MlfruitService.class);

    @Resource  //or@autowire
    private MlfruitMapper mlfruitMapper;

    @Resource
    private SnowFlake snowFlake;

    public List<MlfruitQueryResp> all() {
        MlfruitExample mlfruitExample = new MlfruitExample();
        mlfruitExample.setOrderByClause("id asc");
        List<Mlfruit> mlfruitList = mlfruitMapper.selectByExample(mlfruitExample);

        List<MlfruitQueryResp> list = CopyUtil.copyList(mlfruitList, MlfruitQueryResp.class);
        return list;
    }

    public PageResp<MlfruitQueryResp> list(MlfruitQueryReq req) {

        MlfruitExample mlfruitExample = new MlfruitExample();
        mlfruitExample.setOrderByClause("id asc");
        MlfruitExample.Criteria criteria = mlfruitExample.createCriteria();

        PageHelper.startPage(req.getPage(),req.getSize());
        List<Mlfruit> mlfruitList = mlfruitMapper.selectByExample(mlfruitExample);

        PageInfo<Mlfruit> pageInfo = new PageInfo<>(mlfruitList);
        LOG.info("Total Rows:" + pageInfo.getTotal());
        LOG.info("Total Pages:" + pageInfo.getPages());

        List<MlfruitQueryResp> list = CopyUtil.copyList(mlfruitList, MlfruitQueryResp.class);
        PageResp<MlfruitQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
        return pageResp;
    }

    public void save(Mlfruit mlfruit){
        try {
            System.out.println("MlfruitService.set snowFlake.nextId:");
            System.out.println("MlfruitService.save:");
            System.out.println(mlfruit.toString());
            mlfruit.setId(snowFlake.nextId());
            mlfruitMapper.insert(mlfruit);
            System.out.println("inserted id=" + mlfruit.getId());
        }catch (Exception e){
            System.out.println("Exception captured");
            System.out.println(e.getMessage());
        }
    }
}

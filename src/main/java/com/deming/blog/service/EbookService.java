package com.deming.blog.service;

import com.deming.blog.domain.Ebook;
import com.deming.blog.domain.EbookExample;
import com.deming.blog.mapper.EbookMapper;
import com.deming.blog.req.EbookQueryReq;
import com.deming.blog.req.EbookSaveReq;
import com.deming.blog.resp.EbookQueryResp;
import com.deming.blog.resp.PageResp;
import com.deming.blog.util.CopyUtil;
//import org.springframework.beans.BeanUtils;
import com.deming.blog.util.SnowFlake;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {
    private static final Logger LOG = LoggerFactory.getLogger(EbookService.class);

    @Resource  //or@autowire
    private EbookMapper ebookMapper;

    @Resource
    private SnowFlake snowFlake;

    //public List<EbookResp> list(EbookReq req) {
    public PageResp<EbookQueryResp> list(EbookQueryReq req) {

        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        if (!ObjectUtils.isEmpty(req.getName())){
            criteria.andNameLike('%' + req.getName() + '%');
        }
        if (!ObjectUtils.isEmpty(req.getCategoryId2())) {
            criteria.andCategory2IdEqualTo(req.getCategoryId2());
        }

        PageHelper.startPage(req.getPage(),req.getSize());
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);

        PageInfo<Ebook> pageInfo = new PageInfo<>(ebookList);
        LOG.info("Total Rows:" + pageInfo.getTotal());
        LOG.info("Total Pages:" + pageInfo.getPages());

/*        List<EbookResp> respList1 = new ArrayList<>();
        for (Ebook ebook : ebookList){

            EbookResp ebookResp = new EbookResp();
            ebookResp.setId(ebook.getId()); //set every attributes
            BeanUtils.copyProperties(ebook, ebookResp);  //tools to copy data
            ebookResp.setId(123l);

            //simplified to next line. Copy single object
            EbookResp ebookResp = CopyUtil.copy(ebook, EbookResp.class);

            respList1.add(ebookResp);
        }*/

        //List<EbookResp> respList = CopyUtil.copyList(ebookList, EbookResp.class);
        //return CopyUtil.copyList(ebookList, EbookResp.class);

        List<EbookQueryResp> list = CopyUtil.copyList(ebookList, EbookQueryResp.class);
        PageResp<EbookQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
        return pageResp;
    }

    public void save(EbookSaveReq req){
        Ebook ebook = CopyUtil.copy(req, Ebook.class);
        if(ObjectUtils.isEmpty(req.getId())) {
            ebook.setId(snowFlake.nextId());
            ebookMapper.insert(ebook);
        }else {
            ebookMapper.updateByPrimaryKey(ebook);
        }
    }

    public void delete(Long id){
        ebookMapper.deleteByPrimaryKey(id);
    }

}

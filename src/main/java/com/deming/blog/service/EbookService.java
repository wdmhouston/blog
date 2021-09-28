package com.deming.blog.service;

import com.deming.blog.domain.Ebook;
import com.deming.blog.domain.EbookExample;
import com.deming.blog.mapper.EbookMapper;
import com.deming.blog.req.EbookReq;
import com.deming.blog.resp.EbookResp;
import com.deming.blog.resp.PageResp;
import com.deming.blog.util.CopyUtil;
//import org.springframework.beans.BeanUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.cj.util.StringUtils;
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

    //public List<EbookResp> list(EbookReq req) {
    public PageResp<EbookResp> list(EbookReq req) {

        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        if (!ObjectUtils.isEmpty(req.getName())){
            criteria.andNameLike('%' + req.getName() + '%');
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

        List<EbookResp> list = CopyUtil.copyList(ebookList, EbookResp.class);
        PageResp<EbookResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
        return pageResp;
    }

}

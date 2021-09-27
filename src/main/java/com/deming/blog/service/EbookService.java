package com.deming.blog.service;

import com.deming.blog.domain.Ebook;
import com.deming.blog.domain.EbookExample;
import com.deming.blog.mapper.EbookMapper;
import com.deming.blog.req.EbookReq;
import com.deming.blog.resp.EbookResp;
import com.deming.blog.util.CopyUtil;
//import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {

    @Resource  //or@autowire
    private EbookMapper ebookMapper;

    public List<EbookResp> list(EbookReq req) {
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        criteria.andNameLike('%' + req.getName() + '%');
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);

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

        return CopyUtil.copyList(ebookList, EbookResp.class);
    }

}

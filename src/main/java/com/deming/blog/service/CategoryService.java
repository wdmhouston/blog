package com.deming.blog.service;

import com.deming.blog.domain.Category;
import com.deming.blog.domain.CategoryExample;
import com.deming.blog.mapper.CategoryMapper;
import com.deming.blog.req.CategoryQueryReq;
import com.deming.blog.req.CategorySaveReq;
import com.deming.blog.resp.CategoryQueryResp;
import com.deming.blog.resp.PageResp;
import com.deming.blog.util.CopyUtil;
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
public class CategoryService {
    private static final Logger LOG = LoggerFactory.getLogger(CategoryService.class);

    @Resource  //or@autowire
    private CategoryMapper categoryMapper;

    @Resource
    private SnowFlake snowFlake;

    public List<CategoryQueryResp> all() {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.setOrderByClause("sort asc");
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);

        List<CategoryQueryResp> list = CopyUtil.copyList(categoryList, CategoryQueryResp.class);
        return list;
    }

    //public List<CategoryResp> list(CategoryReq req) {
    public PageResp<CategoryQueryResp> list(CategoryQueryReq req) {

        CategoryExample categoryExample = new CategoryExample();
        categoryExample.setOrderByClause("sort asc");
        CategoryExample.Criteria criteria = categoryExample.createCriteria();
//        if (!ObjectUtils.isEmpty(req.getName())){
//            criteria.andNameLike('%' + req.getName() + '%');
//        }

        PageHelper.startPage(req.getPage(),req.getSize());
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);

        PageInfo<Category> pageInfo = new PageInfo<>(categoryList);
        LOG.info("Total Rows:" + pageInfo.getTotal());
        LOG.info("Total Pages:" + pageInfo.getPages());

/*        List<CategoryResp> respList1 = new ArrayList<>();
        for (Category category : categoryList){

            CategoryResp categoryResp = new CategoryResp();
            categoryResp.setId(category.getId()); //set every attributes
            BeanUtils.copyProperties(category, categoryResp);  //tools to copy data
            categoryResp.setId(123l);

            //simplified to next line. Copy single object
            CategoryResp categoryResp = CopyUtil.copy(category, CategoryResp.class);

            respList1.add(categoryResp);
        }*/

        //List<CategoryResp> respList = CopyUtil.copyList(categoryList, CategoryResp.class);
        //return CopyUtil.copyList(categoryList, CategoryResp.class);

        List<CategoryQueryResp> list = CopyUtil.copyList(categoryList, CategoryQueryResp.class);
        PageResp<CategoryQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
        return pageResp;
    }

    public void save(CategorySaveReq req){
        Category category = CopyUtil.copy(req, Category.class);
        if(ObjectUtils.isEmpty(req.getId())) {
            category.setId(snowFlake.nextId());
            categoryMapper.insert(category);
        }else {
            categoryMapper.updateByPrimaryKey(category);
        }
    }

    public void delete(Long id){
        categoryMapper.deleteByPrimaryKey(id);
    }

}

package com.e3mall.content.service;

import com.e3mall.mapper.TbContentMapper;
import com.e3mall.pojo.DataGridResult;
import com.e3mall.pojo.E3Result;
import com.e3mall.pojo.TbContent;
import com.e3mall.pojo.TbContentExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private TbContentMapper contentMapper;
    @Override
    public DataGridResult getContentList(Integer page, Integer rows, long categoryId) {
        PageHelper.startPage(page,rows);
        TbContentExample example = new TbContentExample();
        if (categoryId!=0){
            TbContentExample.Criteria criteria = example.createCriteria();
            criteria.andCategoryIdEqualTo(categoryId);
        }
        List<TbContent> contentList = contentMapper.selectByExample(example);
        PageInfo<TbContent> pageInfo = new PageInfo<>(contentList);
        long total = pageInfo.getTotal();
        DataGridResult result = new DataGridResult();
        result.setTotal(total);
        result.setRows(pageInfo.getList());
        return result;
    }

    @Override
    public E3Result addContent(TbContent content) {
        content.setCreated(new Date());
        content.setUpdated(new Date());
        int insert = contentMapper.insert(content);
        if (insert!=0){
            return E3Result.ok();
        }else {
            return E3Result.build(100,"存储失败");
        }

    }
}

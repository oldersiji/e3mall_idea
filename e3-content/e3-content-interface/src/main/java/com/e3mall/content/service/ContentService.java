package com.e3mall.content.service;

import com.e3mall.pojo.DataGridResult;
import com.e3mall.pojo.E3Result;
import com.e3mall.pojo.TbContent;

public interface ContentService {
    DataGridResult getContentList(Integer page, Integer rows, long categoryId);

    E3Result addContent(TbContent content);
}

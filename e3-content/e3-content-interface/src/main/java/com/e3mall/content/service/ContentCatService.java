package com.e3mall.content.service;

import com.e3mall.pojo.E3Result;
import com.e3mall.pojo.TbContentCategory;
import com.e3mall.pojo.TreeNode;

import java.util.List;

public interface ContentCatService {
    List<TreeNode> getContentCatList(long parentId);

    E3Result createContentCat(TbContentCategory contentCategory);

    E3Result updateCatContent(long id, String name);

    void deleteContentCat(long id);
}

package com.e3mall.service;

import com.e3mall.pojo.TreeNode;

import java.util.List;

public interface ItemCatService {
    List<TreeNode> getItemCatList(long parentId);
}

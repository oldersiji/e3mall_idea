package com.e3mall.content.service;

import com.e3mall.mapper.TbContentCategoryMapper;
import com.e3mall.pojo.E3Result;
import com.e3mall.pojo.TbContentCategory;
import com.e3mall.pojo.TbContentCategoryExample;
import com.e3mall.pojo.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ContentCatServiceImpl implements ContentCatService {
    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;
    @Override
    public List<TreeNode> getContentCatList(long parentId) {
        List<TreeNode> treeNodeList =  new ArrayList<TreeNode>();
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> contentCategoryList = contentCategoryMapper.selectByExample(example);
        for (TbContentCategory contentCat:contentCategoryList) {
            TreeNode treeNode = new TreeNode();
            treeNode.setId(contentCat.getId());
            treeNode.setText(contentCat.getName());
            treeNode.setState(contentCat.getIsParent()?"closed":"open");
            treeNodeList.add(treeNode);
        }
        return treeNodeList;
    }

    @Override
    public E3Result createContentCat(TbContentCategory contentCategory) {
//      补全contentCategory信息
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(new Date());
        contentCategory.setSortOrder(2);
        contentCategory.setIsParent(false);
        contentCategory.setStatus(1);
        contentCategoryMapper.insert(contentCategory);
//      判断父节点的isparent参数
        TbContentCategory parentCat = contentCategoryMapper.selectByPrimaryKey(contentCategory.getParentId());
        if(!parentCat.getIsParent()){
            parentCat.setIsParent(true);
            contentCategoryMapper.updateByPrimaryKey(parentCat);
        }


        return E3Result.ok(contentCategory);
    }

    @Override
    public E3Result updateCatContent(long id, String name) {
        TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
        contentCategory.setName(name);
        contentCategoryMapper.updateByPrimaryKey(contentCategory);
        return E3Result.ok(contentCategory);
    }

    @Override
    public void deleteContentCat(long id) {
        if (id == 0 ){
            System.exit(0);
        }
        List<TbContentCategory>list =checkParentCat(id);
        if (list!=null){
           for (TbContentCategory category:list){
               deleteContentCat(category.getId());
           }
        }else {
            contentCategoryMapper.deleteByPrimaryKey(id);
        }

    }

//    判断父节点下是否还有子节点 如果没有则改变父节点状态
    public List<TbContentCategory> checkParentCat(long id){
        TbContentCategoryExample categoryExample = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = categoryExample.createCriteria();
        criteria.andParentIdEqualTo(id);
        List<TbContentCategory> categoryList = contentCategoryMapper.selectByExample(categoryExample);
        if (categoryList!=null&&categoryList.size()!=0){
            return categoryList;
        }else {
            TbContentCategory category = contentCategoryMapper.selectByPrimaryKey(id);
            Long parentId = category.getParentId();
            List<TbContentCategory> parentCat = checkParentCat(parentId);
            if (parentCat==null||parentCat.size()==0){
                TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(parentId);
                contentCategory.setIsParent(false);
                contentCategoryMapper.updateByPrimaryKey(contentCategory);
            }
            return null;
        }
    }
}

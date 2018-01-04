package com.e3mall.controller;

import com.e3mall.content.service.ContentCatService;
import com.e3mall.pojo.E3Result;
import com.e3mall.pojo.TbContentCategory;
import com.e3mall.pojo.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/content/category")
public class ContentCatController {
    @Autowired
    private ContentCatService contentCatService;

    @RequestMapping("/list")
    @ResponseBody
    public List<TreeNode> getContentCatList(@RequestParam(value = "id",defaultValue = "0")long parentId){
        List<TreeNode> treeNodeList = contentCatService.getContentCatList(parentId);
        return treeNodeList;
    }

    @RequestMapping("/create")
    @ResponseBody
    public E3Result createContentCatList(@RequestParam(value = "parentId")long parentId,@RequestParam(value = "name") String name){
        TbContentCategory contentCategory = new TbContentCategory();
        contentCategory.setParentId(parentId);
        contentCategory.setName(name);
        E3Result e3Result = contentCatService.createContentCat(contentCategory);
        return e3Result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public E3Result updateContentCat(@RequestParam(value = "id")long id,@RequestParam(value = "name") String name){
        E3Result e3Result = contentCatService.updateCatContent(id,name);
        return e3Result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public E3Result deleteContentCat(@RequestParam(value = "id")long id){
      contentCatService.deleteContentCat(id);
       return E3Result.ok();

    }
}

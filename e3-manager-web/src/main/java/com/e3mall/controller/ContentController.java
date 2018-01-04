package com.e3mall.controller;

import com.e3mall.content.service.ContentService;
import com.e3mall.pojo.DataGridResult;
import com.e3mall.pojo.E3Result;
import com.e3mall.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
//@RequestMapping("/")
public class ContentController {
    @Autowired
    private ContentService contentService;

    @RequestMapping("/content/save")
    @ResponseBody
    public E3Result addContent(TbContent content){
        E3Result e3Result = contentService.addContent(content);
        return e3Result;
    }

    @RequestMapping("/content/query/list")
    @ResponseBody
    public DataGridResult getContentList(Integer page, Integer rows, @RequestParam(value = "categoryId",defaultValue = "0") long categoryId){
        DataGridResult gridResult = contentService.getContentList(page,rows,categoryId);
        return gridResult;
    }
}

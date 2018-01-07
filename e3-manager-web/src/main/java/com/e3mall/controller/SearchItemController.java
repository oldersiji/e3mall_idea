package com.e3mall.controller;

import com.e3mall.pojo.E3Result;
import com.e3mall.search.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/index/item")
public class SearchItemController {
    @Autowired
    private SearchItemService searchItemService;

    @RequestMapping("/import")
    @ResponseBody
    public E3Result importItem(){
        E3Result e3Result = searchItemService.importItem();
        return e3Result;

    }
}

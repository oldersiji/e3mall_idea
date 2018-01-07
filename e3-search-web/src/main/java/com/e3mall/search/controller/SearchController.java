package com.e3mall.search.controller;

import com.e3mall.search.pojo.SearchResult;
import com.e3mall.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;

@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;
    @Value("${search.result.rows}")
    private int rows;

    @RequestMapping("/search")
    public String search(String keyword, @RequestParam(defaultValue = "1")Integer page, Model model){
        try {
            keyword = new String(keyword.getBytes("iso8859-1"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        SearchResult searchResult = searchService.searchItem(keyword, page, rows);
        model.addAttribute("itemList",searchResult.getItemList());
        model.addAttribute("recourdCount",searchResult.getRecordCount());
        model.addAttribute("totalPages",searchResult.getTotalPages());
        model.addAttribute("query",keyword);
        model.addAttribute("page",page);
        return  "search";
    }
}

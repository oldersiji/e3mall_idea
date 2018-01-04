package com.e3mall.controller;

import com.e3mall.pojo.TbItem;
import com.e3mall.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.e3mall.pojo.DataGridResult;

@Controller
public class ItemController {
	@Autowired
	private ItemService itemservice;
	
	@RequestMapping("/")
	public String Index() {
		return "index";
	}
	
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page) {
		return page;
	}
	
	@RequestMapping("/getItem/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId) {
		TbItem item = itemservice.getItemById(itemId);
		System.out.println(item.getTitle());
		return item;
	}
	
	@RequestMapping("/item/list")
	@ResponseBody
	public DataGridResult getItemList(Integer page,Integer rows) {
		DataGridResult gridResult = itemservice.getItemList(page, rows);
		return gridResult;
	}
}

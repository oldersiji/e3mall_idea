package com.e3mall.service;

import com.e3mall.pojo.DataGridResult;
import com.e3mall.pojo.TbItem;

public interface ItemService {

	TbItem getItemById(Long id);
	
	DataGridResult getItemList(int page,int rows);
}

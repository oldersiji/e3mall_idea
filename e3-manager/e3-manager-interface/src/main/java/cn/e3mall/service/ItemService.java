package cn.e3mall.service;

import cn.e3mall.pojo.DataGridResult;
import cn.e3mall.pojo.TbItem;

public interface ItemService {

	TbItem getItemById(Long id);
	
	DataGridResult getItemList(int page,int rows);
}

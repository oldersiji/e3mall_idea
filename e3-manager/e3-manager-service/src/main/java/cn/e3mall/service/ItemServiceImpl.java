package cn.e3mall.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.DataGridResult;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemExample;
import cn.e3mall.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private TbItemMapper itemMapper;

	@Override
	public TbItem getItemById(Long id) {
		TbItem item = itemMapper.selectByPrimaryKey(id);
		return item;
	}

	@Override
	public DataGridResult getItemList(int page, int rows) {
		PageHelper.startPage(page, rows);
		TbItemExample example = new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();
		DataGridResult result = new DataGridResult(total,list);
		return result;
	}
	
	
	
}

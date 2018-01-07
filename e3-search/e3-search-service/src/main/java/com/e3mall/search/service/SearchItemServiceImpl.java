package com.e3mall.search.service;

import com.e3mall.pojo.E3Result;
import com.e3mall.search.mapper.SearchItemMapper;
import com.e3mall.search.pojo.SearchItem;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchItemServiceImpl implements SearchItemService {
    @Autowired
    private SearchItemMapper searchItemMapper;
    @Autowired
    private HttpSolrServer solrServer;

    @Override
    public E3Result importItem(){
        List<SearchItem> itemList = searchItemMapper.getItemList();
        try {
            for (SearchItem searchItem:itemList) {
                SolrInputDocument document = new SolrInputDocument();
                document.addField("id",searchItem.getId());
                document.addField("item_title",searchItem.getTitle());
                document.addField("item_sell_point",searchItem.getSell_point());
                document.addField("item_price",searchItem.getPrice());
                document.addField("item_image",searchItem.getImage());
                document.addField("item_category_name",searchItem.getCategory_name());
                solrServer.add(document);
            }
            solrServer.commit();
            return  E3Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return E3Result.build(500,"导入失败");
        }

    }

}

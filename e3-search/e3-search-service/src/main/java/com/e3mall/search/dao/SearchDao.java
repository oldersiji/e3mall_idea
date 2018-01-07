package com.e3mall.search.dao;

import com.e3mall.search.pojo.SearchItem;
import com.e3mall.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class SearchDao {
    @Autowired
    private HttpSolrServer solrServer;

    public SearchResult search(SolrQuery solrQuery) {
        SearchResult searchResult = new SearchResult();
        try {
            QueryResponse query = solrServer.query(solrQuery);
            SolrDocumentList results = query.getResults();
//            获取总数
            long found = results.getNumFound();
            searchResult.setRecordCount((int) found);
//            获取查询全部结果
            List<SearchItem> items = new ArrayList<>();
//            取得高亮结果集
            Map<String, Map<String, List<String>>> highlighting = query.getHighlighting();
            for (SolrDocument document:results) {
                //取得商品信息
                SearchItem searchItem = new SearchItem();
                searchItem.setCategory_name((String) document.get("item_category_name"));
                searchItem.setId((String) document.get("id"));
                searchItem.setImage((String) document.get("item_image"));
                searchItem.setPrice((long) document.get("item_price"));
                searchItem.setSell_point((String) document.get("item_sell_point"));
//              取得高亮结果
                List<String> itemTitles = highlighting.get(document.get("id")).get("item_title");
                String itemTitle = "";
                if (itemTitles != null && itemTitles.size() > 0) {
                    itemTitle = itemTitles.get(0);
                } else {
                    itemTitle = (String) document.get("item_title");
                }
                searchItem.setTitle(itemTitle);
                //添加到商品列表
                items.add(searchItem);
                searchResult.setItemList(items);
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        }

        return searchResult;
    }
}

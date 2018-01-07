package com.e3mall.search.service;

import com.e3mall.search.dao.SearchDao;
import com.e3mall.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchDao searchDao;
    @Autowired
    private HttpSolrServer solrServer;
    @Override
    public SearchResult searchItem(String keywords,int page,int rows) {
//        使用ｓｏｌｒ关键字创建查询Query
        SolrQuery solrQuery = new SolrQuery();
//        2）设置查询条件，可以参考后台
//        3）设置主查询条件
        solrQuery.setQuery(keywords);
//        4）设置分页条件
        solrQuery.setStart((page-1)*rows);
//        5）设置默认搜索域
        solrQuery.set("df","item_title");
//        6）开启高亮显示
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlightSimplePost("</span>");
        solrQuery.setHighlightSimplePre("<span style=\"color:red\">");
//        7）执行查询，需要调用dao
        SearchResult searchResult = searchDao.search(solrQuery);
//        8）取查询结果
        int recordCount = searchResult.getRecordCount();
        int pageCount = recordCount%rows;
        if (pageCount%rows!=0){
            pageCount++;
        }
//        9）根据总记录数计算查询结果的总页数
        searchResult.setTotalPages(pageCount);
//        10）返回查询结果
        return searchResult;
    }
}

package com.e3mall.search.service;

import com.e3mall.search.pojo.SearchResult;

public interface SearchService {
    SearchResult searchItem(String keywords ,int page,int rows);
}

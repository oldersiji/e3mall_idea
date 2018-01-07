package com.e3mall.search.pojo;

import java.io.Serializable;
import java.util.List;

public class SearchResult implements Serializable{
    private List<SearchItem> itemList;
    private int totalPages;
    private int recordCount;

    public List<SearchItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<SearchItem> itemList) {
        this.itemList = itemList;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }
}

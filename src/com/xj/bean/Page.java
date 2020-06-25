package com.xj.bean;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable {
    //当前页码  参数
    private int pageNo;
    //总页码  计算
    private int totalPage;
    //总记录数  查询
    private int totalCount;
    //每页显示的记录数 
    private int pageSize = 4;
    //开始查询的索引  计算
    private int index;
    //表示是否有下一页  判断
    private boolean hasNext;
    //是否有上一页
    private boolean hasPrev;
    //实际查询出的数据
    private List<T> pageData;
    
    //当前调用页面的url
    private String url;

    public Page() {
    }

    public Page(int pageNo, int totalPage, int totalCount, int pageSize, int index, boolean hasNext, boolean hasPrev, List<T> pageData, String url) {
        this.pageNo = pageNo;
        this.totalPage = totalPage;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.index = index;
        this.hasNext = hasNext;
        this.hasPrev = hasPrev;
        this.pageData = pageData;
        this.url = url;
    }

    public int getPageNo() { return pageNo; }

    public void setPageNo(int pageNo) {
        pageNo = pageNo>0?pageNo:1;
        this.pageNo = pageNo>getTotalPage()?getTotalPage():pageNo;
    }

    public int getTotalPage() {
        int t = getTotalCount()/getPageSize();
        if(!(getTotalCount()%getPageSize() == 0)) {
            t = t+1;
        }
        return t;
    }

    public void setTotalPage(int totalPage) { this.totalPage = totalPage; }

    public int getTotalCount() { return totalCount; }

    public void setTotalCount(int totalCount) { this.totalCount = totalCount; }

    public int getPageSize() { return pageSize; }

    public void setPageSize(int pageSize) { this.pageSize = pageSize; }
    
    //计算出索引值
    public int getIndex() {
        //每页显示4条
        //页码  开始索引  结束索引
        // 1      0         3
        // 2      4         7
        // 3      8         11
        int i = (getPageNo() - 1) * getPageSize();
        if(i < 0) i = 0;
        return i;
    }
    
    public boolean isHasNext() {
        return getPageNo() < getTotalPage();
    }

    public boolean isHasPrev() {
        return getPageNo() > 1;
    }

    public List<T> getPageData() {
        return pageData;
    }

    public void setPageData(List<T> pageData) {
        this.pageData = pageData;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", totalPage=" + totalPage +
                ", totalCount=" + totalCount +
                ", pageSize=" + pageSize +
                ", index=" + index +
                ", hasNext=" + hasNext +
                ", hasPrev=" + hasPrev +
                ", pageData=" + pageData +
                ", url='" + url + '\'' +
                '}';
    }
}

package com.self.project.core.page;


import com.google.common.base.Preconditions;

/**
 * @Description
 * @Author will
 * @Date 2019/04/11
 */
public class PageParam {

    private int pageNum = -1; // 第几页

    private int pageSize = -1; // 每页记录数

    private boolean needPage = true;//是否需要分页

    private boolean count = false;//总数

    private String orderBy;//排序字段

    private boolean asc = true;//排序方式


    public PageParam() {
    }

    public PageParam(int pageNum, int pageSize) {
        setPageNum(pageNum);
        setPageSize(pageSize);
    }

    public int getPageNum() {
        return pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public boolean isNeedPage() {
        return needPage;
    }

    public void setNeedPage(boolean needPage) {
        this.needPage = needPage;
    }

    public boolean isCount() {
        return count;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public boolean isAsc() {
        return asc;
    }

    public void setPageNum(int pageNum) {
        Preconditions.checkArgument(pageNum >= 1, "pageNum参数错误");
        this.pageNum = pageNum;
    }

    public void setPageSize(int pageSize) {
        Preconditions.checkArgument(pageSize >= 1, "pageSize参数错误");
        this.pageSize = pageSize;
    }

    public void setCount(boolean count) {
        this.count = count;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public void setAsc(boolean asc) {
        this.asc = asc;
    }

}

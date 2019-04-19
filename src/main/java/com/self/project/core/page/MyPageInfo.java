package com.self.project.core.page;

import com.github.pagehelper.PageInfo;
import java.io.Serializable;
import java.util.List;

/**
 * @Description
 * @Author will
 * @Date 2019/04/11
 */
public class MyPageInfo<T> implements Serializable {

  private int pageSize;

  private int pageNum;

  private long total;

  private List<T> list;

  public MyPageInfo(List<T> list) {
    PageInfo<T> page = new PageInfo<>(list);
    this.pageNum = page.getPageNum();
    this.pageSize = page.getPageSize();
    this.total = page.getTotal();
    this.list = page.getList();
  }

  public int getPageSize() {
    return pageSize;
  }

  public MyPageInfo<T> setPageSize(int pageSize) {
    this.pageSize = pageSize;
    return this;
  }

  public int getPageNum() {
    return pageNum;
  }

  public MyPageInfo<T> setPageNum(int pageNum) {
    this.pageNum = pageNum;
    return this;
  }

  public long getTotal() {
    return total;
  }

  public MyPageInfo<T> setTotal(long total) {
    this.total = total;
    return this;
  }

  public List<T> getList() {
    return list;
  }

  public MyPageInfo<T> setList(List<T> list) {
    this.list = list;
    return this;
  }
}

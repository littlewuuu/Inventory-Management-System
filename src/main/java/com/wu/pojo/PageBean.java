package com.wu.pojo;

import java.util.List;

/**
 * 分页查询
 * @param <T> 查询的页面可能是 brand 也可能是 user
 */
public class PageBean<T> {

    /**
     * 存放总记录数
     */
    private int totalCount;

    /**
     * 存放查询出的每页的记录
     */
    List<T> rows;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}

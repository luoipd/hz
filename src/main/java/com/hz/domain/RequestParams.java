package com.hz.domain;

/**
 * @author lyp
 * @date 2018/11/1
 */
public class RequestParams {

    private int pageSize;
    private int pageNum;
    private Object data;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

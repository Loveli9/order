package com.example.order.common;

import java.util.List;

/**
 * @author author
 */
@SuppressWarnings("unchecked")
public class PageResponse<T> extends ListResponse<T> {

    /**
     * default serial version UID
     */
    private static final long serialVersionUID = 1L;

    private int               pageSize;

    private int               pageNumber;

    private int               pageCount;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageCount() {
        if (super.totalCount > 0 && this.pageSize > 0) {
            int remainder = super.totalCount % this.pageSize;
            int quotient = super.totalCount / this.pageSize;
            this.pageCount = remainder == 0 ? quotient : quotient + 1;
        }
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public int getTotalCount() {
        return super.totalCount;
    }

    public PageResponse<T> pageNumber(long pageNumber) {
        this.pageNumber = new Long(pageNumber).intValue();
        return this;
    }

    public PageResponse<T> pageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
        return this;
    }

    public PageResponse<T> pageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public PageResponse<T> pageSize(long pageSize) {
        this.pageSize = new Long(pageSize).intValue();
        return this;
    }

    public static PageResponse ok(List list) {
        PageResponse response = new PageResponse();
        response.setData(list);
        return response;
    }

    public PageResponse<T> totalCount(long totalCount) {
        Long val = totalCount;
        this.setTotalCount(val.intValue());
        return this;
    }

    public PageResponse totalCount(int totalCount) {
        this.setTotalCount(totalCount);
        return this;
    }
}

package com.hr.model;

import java.util.List;

/**
 * 分页返回结果
 */
public class PageResult {

    /**
     * 页码总数
     */
    private int totalPages;
    /**
     * 数据模型
     */
    private List<?> content;

    public int getTotalPages() {
        return totalPages;
    }
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
    public List<?> getContent() {
        return content;
    }
    public void setContent(List<?> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "totalPages=" + totalPages +
                ", content=" + content +
                '}';
    }
}


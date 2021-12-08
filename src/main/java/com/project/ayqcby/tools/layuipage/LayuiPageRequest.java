package com.project.ayqcby.tools.layuipage;

public class LayuiPageRequest {
    /**
     * 当前页码
     */
    private int page;
    /**
     * 每页数量
     */
    private int limit;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public LayuiPageRequest(int page,int limit){
        this.page = page;
        this.limit = limit;
    }
}

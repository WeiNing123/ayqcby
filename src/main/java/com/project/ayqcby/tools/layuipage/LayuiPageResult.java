package com.project.ayqcby.tools.layuipage;

import java.util.List;

public class LayuiPageResult {

    private int code;

    /**
     * 记录总数
     */
    private long count;

    /**
     * 数据模型
     */
    private List<?> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
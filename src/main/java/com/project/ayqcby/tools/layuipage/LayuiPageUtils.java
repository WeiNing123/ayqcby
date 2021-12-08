package com.project.ayqcby.tools.layuipage;

import com.github.pagehelper.PageInfo;

public class LayuiPageUtils {

    /**
     * 将分页信息封装到统一的接口
     * @param pageRequest
     * @param pageInfo
     * @return
     */
    public static LayuiPageResult getPageResult(LayuiPageRequest pageRequest, PageInfo<?> pageInfo) {
        LayuiPageResult pageResult = new LayuiPageResult();
        pageResult.setCode(0);
        pageResult.setCount(pageInfo.getTotal());
        pageResult.setData(pageInfo.getList());
        return pageResult;
    }
}
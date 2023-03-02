package com.hr.utils;

import com.github.pagehelper.PageInfo;
import com.hr.model.PageResult;

public class PageUtil{

        /**
         * 将分页信息封装到统一的接口
         *
         * @param
         * @return
         */
        public static PageResult getPageResult(PageInfo<?> pageInfo) {
            PageResult pageResult = new PageResult();
            pageResult.setTotalPages(pageInfo.getPages());
            pageResult.setContent(pageInfo.getList());
            return pageResult;
        }
}
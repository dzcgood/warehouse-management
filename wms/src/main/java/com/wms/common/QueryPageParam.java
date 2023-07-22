package com.wms.common;

import lombok.Data;

import java.util.HashMap;

/**
 * @author DzcGood
 * @date 2023/7/22 - 17:06
 */
@Data
public class QueryPageParam {
    /**
     * 默认页面大小
     */
    private static int PAGE_SIZE = 20;

    /**
     * 默认当前页码
     */
    private static int PAGE_NUM = 1;

    /**
     * 前端传过来的页面大小
     */
    private int pageSize = PAGE_SIZE;

    /**
     * 前端传过来的页码
     */
    private int pageNum = PAGE_NUM;

    /**
     * 前端传过来的其他参数
     */
    private HashMap param;
}

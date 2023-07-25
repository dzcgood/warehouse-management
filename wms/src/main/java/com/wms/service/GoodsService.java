package com.wms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.entity.Storage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author DzcGood
 * @since 2023-07-26
 */
public interface GoodsService extends IService<Goods> {
    /**
     * 自定义分页
     * @param page
     * @return
     */
    IPage<Goods> pageCC(Page<Goods> page , Wrapper wrapper);
}

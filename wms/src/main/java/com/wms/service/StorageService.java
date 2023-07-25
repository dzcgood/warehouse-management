package com.wms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.entity.Storage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author DzcGood
 * @since 2023-07-25
 */
public interface StorageService extends IService<Storage> {
    /**
     * 自定义分页
     * @param page
     * @return
     */
    IPage<Storage> pageCC(Page<Storage> page , Wrapper wrapper);
}

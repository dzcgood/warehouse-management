package com.wms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author DzcGood
 * @since 2023-07-22
 */
public interface UserService extends IService<User> {

    /**
     * 自定义分页
     * @param page
     * @return
     */
    IPage<User> pageCC(Page<User> page , Wrapper wrapper);
}

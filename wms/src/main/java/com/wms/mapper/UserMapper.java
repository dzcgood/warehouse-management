package com.wms.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author DzcGood
 * @since 2023-07-22
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 自定义分页
     * @param page
     * @return
     */
    IPage<User> pageCC(Page<User> page, @Param(Constants.WRAPPER) Wrapper wrapper);
}

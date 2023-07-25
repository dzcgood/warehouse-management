package com.wms.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.entity.Storage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wms.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author DzcGood
 * @since 2023-07-25
 */
@Mapper
public interface StorageMapper extends BaseMapper<Storage> {
    /**
     * 自定义分页
     * @param page
     * @return
     */
    IPage<Storage> pageCC(Page<Storage> page, @Param(Constants.WRAPPER) Wrapper wrapper);
}

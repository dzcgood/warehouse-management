package com.wms.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wms.entity.Storage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author DzcGood
 * @since 2023-07-26
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
    /**
     * 自定义分页
     * @param page
     * @return
     */
    IPage<Goods> pageCC(Page<Goods> page, @Param(Constants.WRAPPER) Wrapper wrapper);
}

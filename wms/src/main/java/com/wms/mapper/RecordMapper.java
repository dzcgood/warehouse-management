package com.wms.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.entity.Goods;
import com.wms.entity.Record;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface RecordMapper extends BaseMapper<Record> {
    /**
     * 自定义分页
     * @param page
     * @return
     */
    IPage<Record> pageCC(Page<Record> page, @Param(Constants.WRAPPER) Wrapper wrapper);
}

package com.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.entity.Goods;
import com.wms.entity.Record;
import com.wms.mapper.GoodsMapper;
import com.wms.mapper.RecordMapper;
import com.wms.service.RecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author DzcGood
 * @since 2023-07-26
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {
    @Resource
    RecordMapper recordMapper;

    @Override
    public IPage<Record> pageCC(Page<Record> page, Wrapper wrapper) {
        return recordMapper.pageCC(page, wrapper);
    }
}

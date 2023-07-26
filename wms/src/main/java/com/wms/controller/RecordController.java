package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.QueryPageParam;
import com.wms.common.Result;
import com.wms.entity.Goods;
import com.wms.entity.GoodsType;
import com.wms.entity.Record;
import com.wms.service.GoodsService;
import com.wms.service.RecordService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author DzcGood
 * @since 2023-07-26
 */
@RestController
@RequestMapping("/record")
public class RecordController {
    @Autowired
    RecordService recordService;

    @Autowired
    GoodsService goodsService;

    /**
     * 分页查询
     * @param queryPageParam
     * @return
     */
    @PostMapping("listPage")
    public Result listPageC1(@RequestBody QueryPageParam queryPageParam) {
        Page<Record> page = new Page<>(queryPageParam.getPageNum(), queryPageParam.getPageSize());

        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        queryWrapper.apply("a.goods_id = b.id AND c.id = b.storage_id AND b.goods_type_id = d.id");
        HashMap param = queryPageParam.getParam();
        String name = (String)param.get("name");
        Object storageIdObj = param.get("storageId");
        Object goodsTypeIdObj = param.get("goodsTypeId");

        if(StringUtils.isNotBlank(name) && !"null".equals(name)) {
            queryWrapper.like("b.name", name);
        }

        if(storageIdObj instanceof Integer) {
            queryWrapper.eq("c.id", storageIdObj);
        }

        if(goodsTypeIdObj instanceof Integer) {
            queryWrapper.eq("d.id", goodsTypeIdObj);
        }

        IPage<Record> result = recordService.pageCC(page, queryWrapper);
        return Result.suc(result.getTotal(), result.getRecords());
    }

    /**
     * 新增
     * @param record
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody Record record) {
        // 更新数量
        Goods goods = goodsService.getById(record.getGoodsId());
        Integer oldCount = goods.getCount();
        Integer num = record.getCount();
        if(record.getAction() == 2) {
            num = -num;
            record.setCount(num);
        }
        goods.setCount(oldCount + num);
        goodsService.updateById(goods);
        return recordService.save(record) ? Result.suc(null, null) : Result.fail();
    }
}

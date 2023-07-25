package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.QueryPageParam;
import com.wms.common.Result;
import com.wms.entity.Goods;
import com.wms.entity.Storage;
import com.wms.entity.User;
import com.wms.service.GoodsService;
import com.wms.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    /**
     * 新增
     * @param goods
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody Goods goods) {
        return goodsService.save(goods) ? Result.suc(null, null) : Result.fail();
    }

    /**
     * 修改
     * @param goods
     * @return
     */
    @PostMapping("mod")
    public Result mod(@RequestBody Goods goods) {
        return goodsService.updateById(goods) ? Result.suc(null, null) : Result.fail();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @GetMapping("delete")
    public Result saveOrMod(Integer id) {
        return goodsService.removeById(id) ? Result.suc(null, null) : Result.fail();
    }

    /**
     * 分页查询
     * @param queryPageParam
     * @return
     */
    @PostMapping("listPage")
    public Result listPageC1(@RequestBody QueryPageParam queryPageParam) {
        Page<Goods> page = new Page<>(queryPageParam.getPageNum(), queryPageParam.getPageSize());

        LambdaQueryWrapper<Goods> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        HashMap param = queryPageParam.getParam();
        String name = (String)param.get("name");
        Object storageIdObj = param.get("storageId");
        Object goodsTypeIdObj = param.get("goodsTypeId");


        if(StringUtils.isNotBlank(name) && !"null".equals(name)) {
            lambdaQueryWrapper.like(Goods::getName, name);
        }

        if(storageIdObj instanceof Integer) {
            lambdaQueryWrapper.eq(Goods::getStorageId, storageIdObj);
        }

        if(goodsTypeIdObj instanceof Integer) {
            lambdaQueryWrapper.eq(Goods::getGoodsTypeId, goodsTypeIdObj);
        }

        IPage<Goods> result = goodsService.pageCC(page, lambdaQueryWrapper);
        return Result.suc(result.getTotal(), result.getRecords());
    }
}

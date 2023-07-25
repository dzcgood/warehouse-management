package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.QueryPageParam;
import com.wms.common.Result;
import com.wms.entity.Storage;
import com.wms.entity.User;
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
 * @since 2023-07-25
 */
@RestController
@RequestMapping("/storage")
public class StorageController {

    @Autowired
    private StorageService storageService;

    /**
     * 新增
     * @param storage
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody Storage storage) {
        return storageService.save(storage) ? Result.suc(null, null) : Result.fail();
    }

    /**
     * 修改
     * @param storage
     * @return
     */
    @PostMapping("mod")
    public Result mod(@RequestBody Storage storage) {
        return storageService.updateById(storage) ? Result.suc(null, null) : Result.fail();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @GetMapping("delete")
    public Result saveOrMod(Integer id) {
        return storageService.removeById(id) ? Result.suc(null, null) : Result.fail();
    }

    /**
     * 分页查询
     * @param queryPageParam
     * @return
     */
    @PostMapping("listPage")
    public Result listPageC1(@RequestBody QueryPageParam queryPageParam) {
        Page<Storage> page = new Page<>(queryPageParam.getPageNum(), queryPageParam.getPageSize());

        LambdaQueryWrapper<Storage> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        HashMap param = queryPageParam.getParam();
        String name = (String)param.get("name");


        if(StringUtils.isNotBlank(name) && !"null".equals(name)) {
            lambdaQueryWrapper.like(Storage::getName, name);
        }

        IPage<Storage> result = storageService.pageCC(page, lambdaQueryWrapper);
        return Result.suc(result.getTotal(), result.getRecords());
    }
}

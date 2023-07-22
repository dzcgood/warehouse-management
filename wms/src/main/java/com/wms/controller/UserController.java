package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.QueryPageParam;
import com.wms.entity.User;
import com.wms.service.UserService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author DzcGood
 * @since 2023-07-22
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("list")
    public List<User> list() {
        return userService.list();
    }

    /**
     * 新增
     * @param user
     * @return
     */
    @PostMapping("save")
    public boolean save(@RequestBody User user) {
        return userService.save(user);
    }

    /**
     * 修改
     * @param user
     * @return
     */
    @PostMapping("mod")
    public boolean mod(@RequestBody User user) {
        return userService.updateById(user);
    }

    /**
     * 新增或修改
     * 若user有id，就是修改；若user没有id或id不存在，就是新增
     * @param user
     * @return
     */
    @PostMapping("saveOrMod")
    public boolean saveOrMod(@RequestBody User user) {
        return userService.saveOrUpdate(user);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @GetMapping("delete")
    public boolean saveOrMod(Integer id) {
        return userService.removeById(id);
    }

    /**
     * 根据姓名模糊查询
     * @param user
     * @return
     */
    @PostMapping("listP")
    public List<User> listP(@RequestBody User user) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(User::getName, user.getName());
        return userService.list(lambdaQueryWrapper);
    }

    /**
     * 分页
     * @param queryPageParam
     * @return
     */
    @PostMapping("listPage")
    public List<User> listPage(@RequestBody QueryPageParam queryPageParam) {
        Page<User> page = new Page<>(queryPageParam.getPageNum(), queryPageParam.getPageSize());
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        HashMap param = queryPageParam.getParam();
        String name = (String)param.get("name");
        lambdaQueryWrapper.like(User::getName, name == null ? "" : name);

        IPage<User> result = userService.page(page, lambdaQueryWrapper);
        return result.getRecords();
    }

    /**
     * 分页
     * @param queryPageParam
     * @return
     */
    @PostMapping("listPageC")
    public List<User> listPageC(@RequestBody QueryPageParam queryPageParam) {
        Page<User> page = new Page<>(queryPageParam.getPageNum(), queryPageParam.getPageSize());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        HashMap param = queryPageParam.getParam();
        String name = (String)param.get("name");
        lambdaQueryWrapper.like(User::getName, name == null ? "" : name);

        IPage<User> result = userService.pageCC(page, lambdaQueryWrapper);
        return result.getRecords();
    }

}

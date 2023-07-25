package com.wms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wms.common.QueryPageParam;
import com.wms.common.Result;
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
//@CrossOrigin
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
    public Result save(@RequestBody User user) {
        return userService.save(user) ? Result.suc(null, null) : Result.fail();
    }

    /**
     * 修改
     * @param user
     * @return
     */
    @PostMapping("mod")
    public Result mod(@RequestBody User user) {
        return userService.updateById(user) == true ? Result.suc(null, null) : Result.fail();
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
    public Result saveOrMod(Integer id) {
        return userService.removeById(id) ? Result.suc(null, null) : Result.fail();
    }

    /**
     * 根据姓名模糊查询
     * @param user
     * @return
     */
    @PostMapping("listP")
    public Result listP(@RequestBody User user) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(User::getName, user.getName());
        return Result.suc(null, userService.list(lambdaQueryWrapper));
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
     * 自定义分页
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

    /**
     * 返回数据封装测试
     * @param queryPageParam
     * @return
     */
    @PostMapping("listPageC1")
    public Result listPageC1(@RequestBody QueryPageParam queryPageParam) {
        Page<User> page = new Page<>(queryPageParam.getPageNum(), queryPageParam.getPageSize());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        HashMap param = queryPageParam.getParam();
        String name = (String)param.get("name");
        Object sexObj = param.get("sex");

        if(StringUtils.isNotBlank(name) && !"null".equals(name)) {
            lambdaQueryWrapper.like(User::getName, name);
        }
        if(sexObj instanceof Integer) {
            lambdaQueryWrapper.eq(User::getSex, sexObj);
        }

        IPage<User> result = userService.pageCC(page, lambdaQueryWrapper);
        return Result.suc(result.getTotal(), result.getRecords());
    }

    /**
     * 根据no查找用户
     * @param no
     * @return
     */
    @GetMapping("findByNo")
    public Result findByNo(@RequestParam String no) {
        List<User> list = userService.lambdaQuery().eq(User::getNo, no).list();
        return list.size() > 0 ? Result.suc(null, list) : Result.fail();
    }

    /**
     * 登录
     * @param user
     * @return
     */
    @PostMapping("login")
    public Result login(@RequestBody User user) {
        List<User> list = userService.lambdaQuery()
                .eq(User::getNo, user.getNo())
                .eq(User::getPassword, user.getPassword())
                .list();
        return list.size() > 0 ? Result.suc(null, list.get(0)) : Result.fail();
    }



}

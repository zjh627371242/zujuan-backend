package com.mju.generatepaper.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mju.generatepaper.common.PageParams;
import com.mju.generatepaper.common.Result;
import com.mju.generatepaper.common.ResultFactory;
import com.mju.generatepaper.entity.User;
import com.mju.generatepaper.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    /**
     * 获取用户详情
     **/
    @ApiOperation(value = "获取用户详情", notes = "获取用户详情")
    @PostMapping("/detail")
    public Result<User> detail(Long id){
        User user = iUserService.getById(id);
        return ResultFactory.success(user);
    }
    /**
     * 获取分页数据
     */
    @ApiOperation(value = "获取分页数据", notes = "获取分页数据")
    @PostMapping(value = "/list")
    @ResponseBody
    public Result<IPage<User>> list(@RequestBody Map map){
        QueryWrapper<User> queryWrapper=new QueryWrapper();
        if (map.get("username")!=null && map.get("username")!=""){
            queryWrapper.like("username",map.get("username")+"");
        }
        PageParams pageParams=new PageParams(map);
        return ResultFactory.success(iUserService.page(pageParams,queryWrapper));
    }
    /**
     * 新增用户
     */
    @ApiOperation(value = "新增用户", notes = "新增用户")
    @PostMapping(value = "/add")
    @ResponseBody
    public Result add(@RequestBody User user){
        user.setPassword("123456");
        if (iUserService.save(user)){
            return ResultFactory.success("新增成功");
        }
        return ResultFactory.failed("新增失败",null);
    }
    /**
     * 编辑用户
     */
    @ApiOperation(value = "编辑用户", notes = "编辑用户")
    @PostMapping(value = "/edit")
    @ResponseBody
    public Result edit(@RequestBody User user){
        if (iUserService.updateById(user)){
            return ResultFactory.success("修改成功");
        }
        return ResultFactory.failed("修改失败",null);
    }
    /**
     * 删除用户
     */
    @ApiOperation(value = "删除用户", notes = "删除用户")
    @PostMapping(value = "/delete")
    @ResponseBody
    public Result delete(@RequestBody User user){
        if (iUserService.removeById(user)){
            return ResultFactory.success("删除成功");
        }
        return ResultFactory.failed("删除失败",null);
    }
}

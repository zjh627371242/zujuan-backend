package com.mju.generatepaper.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mju.generatepaper.common.PageParams;
import com.mju.generatepaper.common.Result;
import com.mju.generatepaper.common.ResultFactory;
import com.mju.generatepaper.entity.User;
import com.mju.generatepaper.service.IUserService;
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
     * 用户登录
     **/
    @PostMapping("/login")
    public Result<User> login(@RequestBody User user){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",user.getUsername());
        queryWrapper.eq("password",user.getPassword());
        User one = iUserService.getOne(queryWrapper);
        if (one!=null){
            return ResultFactory.success("登录成功",one);
        }
        return ResultFactory.failed("账号或密码不正确",null);
    }
    /**
     * 获取用户详情
     **/
    @PostMapping("/detail")
    public Result<User> detail(@RequestBody User user){
        user = iUserService.getById(user.getId());
        user.setPassword(null);
        return ResultFactory.success(user);
    }

    /**
     * 获取分页数据
     */
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
    @ResponseBody
    @PostMapping(value = "/add")
    public Result add(@RequestBody User user){
        user.setPassword("123456");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",user.getUsername());
        User one = iUserService.getOne(queryWrapper);
        if (one!=null){
            return ResultFactory.failed("新增失败，改账号已存在",null);
        }
        if (iUserService.save(user)){
            return ResultFactory.success("新增成功");
        }
        return ResultFactory.failed("新增失败",null);
    }

    /**
     * 编辑用户
     */
    @PostMapping(value = "/edit")
    @ResponseBody
    public Result edit(@RequestBody User user){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",user.getUsername());
        User newUser = iUserService.getOne(queryWrapper);
        User oldUser = iUserService.getById(user.getId());
        if (newUser!=null && !newUser.getUsername().equals(oldUser.getUsername())){
            return ResultFactory.failed("修改失败,改账号名已存在",null);
        }
        if (iUserService.updateById(user)){
            return ResultFactory.success("修改成功");
        }
        return ResultFactory.failed("修改失败",null);
    }

    /**
     * 删除用户
     */
    @PostMapping(value = "/delete")
    @ResponseBody
    public Result delete(@RequestBody User user){
        if (iUserService.removeById(user)){
            return ResultFactory.success("删除成功");
        }
        return ResultFactory.failed("删除失败",null);
    }
}

package com.mju.generatepaper.controller;


import com.mju.generatepaper.common.Result;
import com.mju.generatepaper.common.ResultFactory;
import com.mju.generatepaper.entity.User;
import com.mju.generatepaper.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * 用户的个人信息
     **/
    @GetMapping("/info")
    public Result<User> getUserInfo(Long id){
        //根据用户id查询用户信息
        User user = iUserService.getById(id);
        return ResultFactory.success(user);
    }
}

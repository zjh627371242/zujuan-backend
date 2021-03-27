package com.mju.generatepaper.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mju.generatepaper.common.PageParams;
import com.mju.generatepaper.common.Result;
import com.mju.generatepaper.common.ResultFactory;
import com.mju.generatepaper.entity.Notice;
import com.mju.generatepaper.service.INoticeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 公告信息表 前端控制器
 * </p>

 */
@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    INoticeService iNoticeService;
    /**
     * 获取公告详情
     **/
    @ApiOperation(value = "获取公告详情", notes = "获取公告详情")
    @PostMapping("/detail")
    public Result<Notice> detail(Long id){
        //根据用户id查询用户信息
        Notice notice = iNoticeService.getById(id);
        return ResultFactory.success(notice);
    }
    /**
     * 获取分页数据
     */
    @ApiOperation(value = "获取分页数据", notes = "获取分页数据")
    @PostMapping(value = "/list")
    @ResponseBody
    public Result<IPage<Notice>> list(@RequestBody Map map){
        PageParams pageParams=new PageParams(map);
        return ResultFactory.success(iNoticeService.page(pageParams));
    }
    /**
     * 新增公告
     */
    @ApiOperation(value = "新增公告", notes = "新增公告")
    @PostMapping(value = "/add")
    @ResponseBody
    public Result add(@RequestBody Notice notice){
        if (iNoticeService.save(notice)){
            return ResultFactory.success("新增成功");
        }
        return ResultFactory.failed("新增失败",null);
    }
    /**
     * 编辑字典
     */
    @ApiOperation(value = "编辑字典", notes = "编辑字典")
    @PostMapping(value = "/edit")
    @ResponseBody
    public Result edit(@RequestBody Notice notice){
        if (iNoticeService.updateById(notice)){
            return ResultFactory.success("修改成功");
        }
        return ResultFactory.failed("修改失败",null);
    }
    /**
     * 删除字典
     */
    @ApiOperation(value = "删除字典", notes = "删除字典")
    @PostMapping(value = "/delete")
    @ResponseBody
    public Result delete(@RequestBody Notice notice){
        if (iNoticeService.removeById(notice)){
            return ResultFactory.success("删除成功");
        }
        return ResultFactory.failed("删除失败",null);
    }
}

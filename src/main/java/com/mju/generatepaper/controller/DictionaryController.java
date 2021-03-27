package com.mju.generatepaper.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mju.generatepaper.common.PageParams;
import com.mju.generatepaper.common.Result;
import com.mju.generatepaper.common.ResultFactory;
import com.mju.generatepaper.entity.Dictionary;
import com.mju.generatepaper.service.IDictionaryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 字典表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/dictionary")
public class DictionaryController {
    @Autowired
    IDictionaryService iDictionaryService;
    /**
     * 获取公告详情
     **/
    @ApiOperation(value = "获取字典详情", notes = "获取字典详情")
    @PostMapping("/detail")
    public Result<Dictionary> detail(Long id){
        Dictionary dictionary = iDictionaryService.getById(id);
        return ResultFactory.success(dictionary);
    }
    /**
     * 获取分页数据
     */
    @ApiOperation(value = "获取分页数据", notes = "获取分页数据")
    @PostMapping(value = "/list")
    @ResponseBody
    public Result<IPage<Dictionary>> list(@RequestBody Map map){
        PageParams pageParams=new PageParams(map);
        return ResultFactory.success(iDictionaryService.page(pageParams));
    }
    /**
     * 新增字典
     */
    @ApiOperation(value = "新增字典", notes = "新增字典")
    @PostMapping(value = "/add")
    @ResponseBody
    public Result add(@RequestBody Dictionary dictionary){
        if (iDictionaryService.save(dictionary)){
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
    public Result edit(@RequestBody Dictionary dictionary){
        if (iDictionaryService.updateById(dictionary)){
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
    public Result delete(@RequestBody Dictionary dictionary){
        if (iDictionaryService.removeById(dictionary)){
            return ResultFactory.success("删除成功");
        }
        return ResultFactory.failed("删除失败",null);
    }
}

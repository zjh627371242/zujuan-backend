package com.mju.generatepaper.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mju.generatepaper.common.PageParams;
import com.mju.generatepaper.common.Result;
import com.mju.generatepaper.common.ResultFactory;
import com.mju.generatepaper.entity.Notice;
import com.mju.generatepaper.service.IPaperService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import com.mju.generatepaper.common.Result;
import com.mju.generatepaper.entity.ManualPaperParam;
import com.mju.generatepaper.entity.Paper;
import com.mju.generatepaper.service.IPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 试卷表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/paper")
public class PaperController {
    @Autowired
    IPaperService iPaperService;
    /**
     * 自动组卷
     **/
    @ApiOperation(value = "自动组卷", notes = "自动组卷")
    @PostMapping("/autoPaper")
    public Result autoPaper(@RequestBody Map map){
        return iPaperService.autoPaper(map);
    }

    /**
     * 手动组卷
     **/
    @PostMapping("/manualPaper")
    public Result manualPaper(@RequestBody ManualPaperParam manualPaperParam){
        //添加试卷
        return iPaperService.manualPaper(manualPaperParam);
    }

    /**
     * 试卷列表
     **/
    @PostMapping("/list")
    public Result<IPage<Paper>> listPaper(@RequestBody Map map){
        PageParams pageParams = new PageParams(map);
        QueryWrapper<Paper> queryWrapper = new QueryWrapper<>();
        if (map.containsKey("title")){
            queryWrapper.like("title",map.get("title"));
            //试卷列表
            return ResultFactory.success(iPaperService.page(pageParams,queryWrapper));
        }else {
            return ResultFactory.success(iPaperService.page(pageParams,null));
        }
    }

    /**
     * 删除试卷
     **/
    @PostMapping("/delete")
    public Result deleteById(@RequestBody Map map){
        return ResultFactory.success(iPaperService.deleteById(map));
    }

    /**
     * 试卷详情
     **/
    @PostMapping("/detail")
    public Result detail(@RequestBody Map map){
        return ResultFactory.success(iPaperService.detail(map));
    }
}

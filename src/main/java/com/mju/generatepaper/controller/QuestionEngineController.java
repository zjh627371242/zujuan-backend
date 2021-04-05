package com.mju.generatepaper.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mju.generatepaper.common.PageParams;
import com.mju.generatepaper.common.Result;
import com.mju.generatepaper.common.ResultFactory;
import com.mju.generatepaper.entity.Knowledge;
import com.mju.generatepaper.entity.Question;
import com.mju.generatepaper.entity.QuestionEngine;
import com.mju.generatepaper.entity.Subject;
import com.mju.generatepaper.service.IQuestionEngineService;
import com.mju.generatepaper.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 题目类型表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/question-engine")
public class QuestionEngineController {
    @Autowired
    private IQuestionEngineService iQuestionEngineService;

    @Autowired
    private IQuestionService iQuestionService;

    /**
     * 保存题型信息
     **/
    @PostMapping("/add")
    public Result add(@RequestBody QuestionEngine questionEngine){
        //保存题型信息
        boolean result = iQuestionEngineService.save(questionEngine);
        //如果是True返回成功
        if (result){
            return ResultFactory.success("添加题型成功",null);
        }
        //如果是False返回失败
        return ResultFactory.success("添加题型失败",null);
    }

    /**
     * 删除题型
     **/
    @PostMapping("/delete")
    public Result delete(@RequestBody QuestionEngine questionEngine){
        //判断知识点是否被试题引用
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("question_ENGINE_id",questionEngine.getId());
        List<Question> questionList = iQuestionService.list(queryWrapper);
        if (questionList != null && questionList.size() > 0){
            return ResultFactory.failed("该题型已被试题引用,无法删除",null);
        }
        //删除知识点
        boolean result = iQuestionEngineService.removeById(questionEngine.getId());
        //如果是True返回成功
        if (result){
            return ResultFactory.success("删除题型成功",null);
        }
        //如果是False返回失败
        return ResultFactory.success("删除题型失败",null);
    }

    /**
     * 修改题型
     **/
    @PostMapping("/edit")
    public Result edit(@RequestBody QuestionEngine questionEngine){
        //根据题型id 修改题型信息
        boolean result = iQuestionEngineService.updateById(questionEngine);
        //如果是True返回成功
        if (result){
            return ResultFactory.success("修改题型成功",null);
        }
        //如果是False返回失败
        return ResultFactory.success("修改题型失败",null);
    }

    /**
     * 查询题型信息
     **/
    @PostMapping("/info")
    public Result<QuestionEngine> getInfo(@RequestBody QuestionEngine questionEngine){
        //根据题型id 查询题型信息
        QuestionEngine one = iQuestionEngineService.getById(questionEngine.getId());
        return ResultFactory.success(one);
    }

    /**
     * 查询题型列表 分页查询
     **/
    @PostMapping("/list")
    public Result<IPage<QuestionEngine>> list(@RequestBody Map map){
        //查询题型列表
        PageParams pageParams=new PageParams(map);
        if (map.get("typeName")!=null && map.get("typeName")!=""){
            QueryWrapper<QuestionEngine> queryWrapper=new QueryWrapper();
            queryWrapper.like("type_name",map.get("typeName")+"");
            return ResultFactory.success(iQuestionEngineService.page(pageParams,queryWrapper));
        }else {
            return ResultFactory.success(iQuestionEngineService.page(pageParams,null));
        }
    }

    /**
     * 查询题型列表
     **/
    @PostMapping("/all")
    public Result<List<QuestionEngine>> all(){
        //查询题型列表
        return ResultFactory.success(iQuestionEngineService.list());
    }
}

package com.mju.generatepaper.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mju.generatepaper.common.PageParams;
import com.mju.generatepaper.common.Result;
import com.mju.generatepaper.common.ResultFactory;
import com.mju.generatepaper.entity.Knowledge;
import com.mju.generatepaper.entity.Question;
import com.mju.generatepaper.entity.Subject;
import com.mju.generatepaper.entity.User;
import com.mju.generatepaper.service.IKnowledgeService;
import com.mju.generatepaper.service.IQuestionEngineService;
import com.mju.generatepaper.service.IQuestionService;
import com.mju.generatepaper.service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 知识点 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/knowledge")
public class KnowledgeController {

    @Autowired
    private IKnowledgeService iKnowledgeService;

    @Autowired
    private IQuestionService iQuestionService;

    @Autowired
    private ISubjectService iSubjectService;

    /**
     * 保存知识点信息
     **/
    @PostMapping("/save")
    public Result<Subject> save(@RequestBody Knowledge knowledge){
        //保存知识点信息
        boolean result = iKnowledgeService.save(knowledge);
        //如果是True返回成功
        if (result){
            return ResultFactory.success("添加知识点成功",null);
        }
        //如果是False返回失败
        return ResultFactory.success("添加知识点失败",null);
    }

    /**
     * 删除知识点
     **/
    @PostMapping("/delete")
    public Result<Subject> delete(@RequestBody Knowledge knowledge){
        //判断知识点是否被试题引用
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("knowledge_id",knowledge.getId());
        List<Question> questionList = iQuestionService.list(queryWrapper);
        if (questionList != null && questionList.size() > 0){
            return ResultFactory.failed("该知识点已被试题引用,无法删除",null);
        }
        //删除知识点
        boolean result = iKnowledgeService.removeById(knowledge.getId());
        //如果是True返回成功
        if (result){
            return ResultFactory.success("删除知识点成功",null);
        }
        //如果是False返回失败
        return ResultFactory.success("删除知识点失败",null);
    }

    /**
     * 修改知识点
     **/
    @PostMapping("/update")
    public Result<Subject> update(@RequestBody Knowledge knowledge){
        //根据知识点id 修改知识点信息
        boolean result = iKnowledgeService.updateById(knowledge);
        //如果是True返回成功
        if (result){
            return ResultFactory.success("修改知识点成功",null);
        }
        //如果是False返回失败
        return ResultFactory.success("修改知识点失败",null);
    }

    /**
     * 查询知识点信息
     **/
    @PostMapping("/info")
    public Result<Knowledge> getInfo(@RequestBody Knowledge knowledge){
        //根据知识点id 查询知识点信息
        Knowledge one = iKnowledgeService.getById(knowledge.getId());
        //根据科目id 查询科目信息
        Subject subject = iSubjectService.getById(one.getSubjectId());
        one.setSubject(subject);
        return ResultFactory.success(one);
    }

    /**
     * 查询知识点列表 分页
     **/
    @PostMapping("/list")
    public Result<IPage<Knowledge>> list(@RequestBody Map map){
        //查询知识点列表
        return ResultFactory.success(iKnowledgeService.getList(map));
    }

    /**
     * 查询知识点列表
     **/
    @PostMapping("/all")
    public Result<List<Knowledge>> all(){
        //查询知识点列表
        return ResultFactory.success(iKnowledgeService.list());
    }
}

package com.mju.generatepaper.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mju.generatepaper.common.Result;
import com.mju.generatepaper.common.ResultFactory;
import com.mju.generatepaper.entity.Knowledge;
import com.mju.generatepaper.entity.Subject;
import com.mju.generatepaper.service.IKnowledgeService;
import com.mju.generatepaper.service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 科目表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    private ISubjectService iSubjectService;

    @Autowired
    private IKnowledgeService iKnowledgeService;

    /**
     * 保存科目信息
     **/
    @PostMapping("/save")
    public Result<Subject> save(@RequestBody Subject subject){
        //保存科目信息
        boolean result = iSubjectService.save(subject);
        //如果是True返回成功
        if (result){
            return ResultFactory.success("添加科目成功",null);
        }
        //如果是False返回失败
        return ResultFactory.success("添加科目失败",null);
    }

    /**
     * 删除科目
     **/
    @PostMapping("/delete")
    public Result<Subject> delete(@RequestBody Subject subject){
        //判断科目是否被知识点引用
        QueryWrapper<Knowledge> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("subject_id",subject.getId());
        List<Knowledge> knowledgeList = iKnowledgeService.list(queryWrapper);
        if (knowledgeList != null && knowledgeList.size() > 0){
            return ResultFactory.failed("该科目被知识点引用了,无法删除",null);
        }
        //删除科目
        boolean result = iSubjectService.removeById(subject.getId());
        //如果是True返回成功
        if (result){
            return ResultFactory.success("删除科目成功",null);
        }
        //如果是False返回失败
        return ResultFactory.success("删除科目失败",null);
    }

    /**
     * 修改科目
     **/
    @PostMapping("/update")
    public Result<Subject> update(@RequestBody Subject subject){
        //根据科目id 修改科目信息
        boolean result = iSubjectService.updateById(subject);
        //如果是True返回成功
        if (result){
            return ResultFactory.success("修改科目信息成功",null);
        }
        //如果是False返回失败
        return ResultFactory.success("修改科目信息失败",null);
    }

    /**
     * 查询科目信息
     **/
    @PostMapping("/info")
    public Result<Subject> getInfo(@RequestBody Subject subject){
        //根据科目id 查询科目信息
        Subject one = iSubjectService.getById(subject.getId());
        return ResultFactory.success(one);
    }

    /**
     * 查询科目列表 分页查询
     **/
    @PostMapping("/list")
    public Result<IPage<Subject>> list(@RequestBody Map map){
        //查询科目列表 分页查询
        return iSubjectService.listSubject(map);
    }

    /**
     * 查询科目列表
     **/
    @PostMapping("/all")
    public Result<List<Subject>> All(){
        //查询科目列表
        return ResultFactory.success(iSubjectService.list());
    }
}

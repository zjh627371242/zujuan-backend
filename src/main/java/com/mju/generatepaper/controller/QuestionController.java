package com.mju.generatepaper.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mju.generatepaper.common.PageParams;
import com.mju.generatepaper.common.Result;
import com.mju.generatepaper.common.ResultFactory;
import com.mju.generatepaper.entity.*;
import com.mju.generatepaper.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 试题表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private IQuestionService iQuestionService;

    @Autowired
    private IPapermxService iPapermxService;

    @Autowired
    private IQuestionEngineService iQuestionEngineService;

    @Autowired
    private IKnowledgeService iKnowledgeService;

    @Autowired
    private IDictionaryService iDictionaryService;

    /**
     * 保存试题信息
     **/
    @PostMapping("/add")
    public Result<Subject> add(@RequestBody Question question){
        //保存试题信息
        boolean result = iQuestionService.save(question);
        //如果是True返回成功
        if (result){
            return ResultFactory.success("添加试题成功",null);
        }
        //如果是False返回失败
        return ResultFactory.success("添加试题失败",null);
    }

    /**
     * 删除试题
     **/
    @PostMapping("/delete")
    public Result<Subject> delete(@RequestBody Question question){
        //判断试题是否被试卷引用
        QueryWrapper<Papermx> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("selected_qu",question.getId());
        List<Papermx> papermxList = iPapermxService.list(queryWrapper);
        if (papermxList != null && papermxList.size() > 0){
            return ResultFactory.failed("该试题已被试卷引用,无法删除",null);
        }
        //根据试题id 删除试题
        boolean result = iQuestionService.removeById(question.getId());
        //如果是True返回成功
        if (result){
            return ResultFactory.success("删除试题成功",null);
        }
        //如果是False返回失败
        return ResultFactory.success("删除试题失败",null);
    }

    /**
     * 修改试题
     **/
    @PostMapping("/edit")
    public Result<Subject> edit(@RequestBody Question question){
        //根据试题id 修改试题信息
        boolean result = iQuestionService.updateById(question);
        //如果是True返回成功
        if (result){
            return ResultFactory.success("修改试题成功",null);
        }
        //如果是False返回失败
        return ResultFactory.success("修改试题失败",null);
    }

    /**
     * 查询试题信息
     **/
    @PostMapping("/info")
    public Result<Question> getInfo(@RequestBody Question question){
        //根据试题id 查询试题信息
        Question one = iQuestionService.getById(question.getId());
        //根据题目类型id 查询题目类型
        QuestionEngine questionEngine = iQuestionEngineService.getById(question.getQuestionEngineId());
        //根据知识点id 查询知识点
        Knowledge knowledge = iKnowledgeService.getById(question.getKnowledgeId());
        one.setQuestionEngine(questionEngine);
        one.setKnowledge(knowledge);
        return ResultFactory.success(one);
    }

    /**
     * 查询试题列表
     **/
    @PostMapping("/list")
    public Result<IPage<Question>> list(@RequestBody Map map){
        QueryWrapper<Dictionary> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dic_code","ND");
        List<Dictionary> dictionaries = iDictionaryService.list(queryWrapper);
        //查询试题列表
        IPage<Question> list = iQuestionService.getList(map);
        List<Question> records = list.getRecords();
        for (Question record : records) {
            for(int i=0;i<dictionaries.size();i++){
                if ((record.getLevel()+"").equals(dictionaries.get(i).getDicKey()+"")){
                    record.setLevelName(dictionaries.get(i).getDicName());
                }
            }
        }
        return ResultFactory.success(list);
    }

    /**
     * 打印题库
     **/
    @PostMapping("/excel")
    public void excel(@RequestBody Subject subject, HttpServletResponse httpServletRespons) throws IOException {
        if (subject.getId() == null){
            iQuestionService.excel(null,httpServletRespons);
        }
        iQuestionService.excel(subject.getId(),httpServletRespons);
    }
}

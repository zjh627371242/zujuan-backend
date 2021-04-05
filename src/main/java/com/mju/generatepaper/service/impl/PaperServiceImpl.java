package com.mju.generatepaper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mju.generatepaper.common.Result;
import com.mju.generatepaper.common.ResultFactory;
import com.mju.generatepaper.entity.*;
import com.mju.generatepaper.mapper.PaperMapper;
import com.mju.generatepaper.mapper.PapermxMapper;
import com.mju.generatepaper.mapper.QuestionEngineMapper;
import com.mju.generatepaper.mapper.QuestionMapper;
import com.mju.generatepaper.service.IPaperService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.*;

/**
 * <p>
 * 试卷表 服务实现类
 * </p>
 */
@Service
public class PaperServiceImpl extends ServiceImpl<PaperMapper, Paper> implements IPaperService {
    @Autowired
    QuestionEngineMapper questionEngineMapper;
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    PaperMapper paperMapper;
    @Autowired
    PapermxMapper papermxMapper;
    public static List<List<Rule>> list = new ArrayList<>();
    static {
        Rule rule = new Rule(10,3);
        Rule rule1 = new Rule(20,2);
        Rule rule2 = new Rule(30,1);
        List<Rule> list1 = new ArrayList();
        list1.add(rule);
        list1.add(rule1);
        list1.add(rule2);

        Rule rule3 = new Rule(10,3);
        Rule rule4 = new Rule(20,2);
        Rule rule5 = new Rule(30,1);
        List<Rule> list2 = new ArrayList();
        list2.add(rule3);
        list2.add(rule4);
        list2.add(rule5);

        list.add(list1);
        list.add(list2);
    }
    @Override
    public Result autoPaper(Map map) {
        Paper paper = new Paper();
        paper.setTitle(map.get("title")+"");
        paper.setCreateTime(new Date());
        paperMapper.insert(paper);
        Random random = new Random();
        int n = random.nextInt(list.size());
        List<Rule> rules = list.get(n);
        List<QuestionEngine> questionEngines = questionEngineMapper.randQuestionEngine(rules.size());
        if (CollectionUtils.isEmpty(questionEngines) || questionEngines.size()!=rules.size()){
            throw new RuntimeException("题型数量不足，请完善题型和对应试题");
        }
       for (int i=0;i<questionEngines.size();i++){
           List<Question> questions = questionMapper.randQuestion(questionEngines.get(i).getId(), rules.get(i).getCount(),Long.parseLong(map.get("subjectId")+""));
           if (CollectionUtils.isEmpty(questions) || questions.size()!=rules.get(i).getCount()){
               throw new RuntimeException("该题型("+questionEngines.get(i).getTypeName()+")的试题数量不足，请完善对应试题");
           }else {
               for (Question question : questions) {
                   Papermx papermx = new Papermx();
                   papermx.setPaperId(paper.getId());
                   papermx.setScore(rules.get(i).getScore());
                   papermx.setSelectedQu(question.getId());
                   papermxMapper.insert(papermx);
               }
           }
       }
        return ResultFactory.success("组卷成功",null);
    }

    @Override
    @Transactional
    public Result manualPaper(ManualPaperParam manualPaperParam) {
        if (manualPaperParam.getPaperParamItems() == null || manualPaperParam.getPaperParamItems().isEmpty()){
            throw new RuntimeException("请添加试题类别");
        }
        Paper paper = new Paper().setTitle(manualPaperParam.getTitle()).setCreateTime(new Date());
        try {
            //保存试卷
            paperMapper.insert(paper);
            for (ManualPaperParamItem paperParamItem : manualPaperParam.getPaperParamItems()) {
                //随机查询试题
                List<Question> questionList = questionMapper.randQuestion(paperParamItem.getQuestionEngineId(), paperParamItem.getCount(),manualPaperParam.getSubjectId());
                if (questionList == null || questionList.size()!=paperParamItem.getCount()){
                    QuestionEngine questionEngine = questionEngineMapper.selectById(paperParamItem.getQuestionEngineId());
                    throw new RuntimeException(questionEngine.getTypeName() + "试题集合不足,请去添加试题");
                }
                for (Question question : questionList) {
                    //插入试卷试题
                    papermxMapper.insert(new Papermx().setPaperId(paper.getId()).setScore(paperParamItem.getScore()).setSelectedQu(question.getId()));
                }
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("手动组卷失败" + e.getMessage());
        }
        return ResultFactory.success("手动组卷成功",null);
    }

    @Override
    public Result deleteById(Map map) {
        QueryWrapper<Papermx> papermxQueryWrapper = new QueryWrapper<>();
        papermxQueryWrapper.eq("paper_id",map.get("paperId")+"");
        try {
            //根据试卷id 删除试卷试题
            papermxMapper.delete(papermxQueryWrapper);
            //根据试卷id 删除试卷
            paperMapper.deleteById(map.get("paperId")+"");
        } catch (Exception e) {
            throw new RuntimeException("删除试卷失败");
        }
        return ResultFactory.success("删除试卷成功",null);
    }

    @Override
    public Result detail(Map map) {
        Paper paper = paperMapper.selectById(Long.parseLong(map.get("paperId") + ""));
        QueryWrapper<Papermx> papermxQueryWrapper = new QueryWrapper<>();
        papermxQueryWrapper.eq("paper_id",paper.getId());
        //根据试卷id 查询试卷试题
        List<Papermx> papermxList = papermxMapper.selectList(papermxQueryWrapper);
        //遍历试卷试题
        for (Papermx papermx : papermxList) {
            //根据试卷试题id 查询试卷试题
            Question question = questionMapper.selectById(papermx.getSelectedQu());
            //根据试题查询试题类型
            QuestionEngine questionEngine = questionEngineMapper.selectById(question.getQuestionEngineId());
            //设置试卷类型
            question.setQuestionEngine(questionEngine);
            //设置试题
            papermx.setQuestion(question);
        }
        //设置试卷试题集合
        paper.setPapermxList(papermxList);
        return ResultFactory.success(paper);
    }
}

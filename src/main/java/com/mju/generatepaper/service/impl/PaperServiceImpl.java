package com.mju.generatepaper.service.impl;

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
}

package com.mju.generatepaper.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mju.generatepaper.common.Result;
import com.mju.generatepaper.common.ResultFactory;
import com.mju.generatepaper.entity.Paper;
import com.mju.generatepaper.entity.QuestionEngine;
import com.mju.generatepaper.entity.Rule;
import com.mju.generatepaper.mapper.PaperMapper;
import com.mju.generatepaper.mapper.QuestionEngineMapper;
import com.mju.generatepaper.mapper.QuestionMapper;
import com.mju.generatepaper.service.IPaperService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    public static List<List<Rule>> list;
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
    public Result autoPaper() {
        Random random = new Random();
        int n = random.nextInt(list.size());
        List<Rule> rules = list.get(n);
        List<QuestionEngine> questionEngines = questionEngineMapper.randQuestionEngine(rules.size());
        if (CollectionUtils.isEmpty(questionEngines) || questionEngines.size()!=rules.size()){
            return ResultFactory.failed("题型数量不足，请完善题型和对应试题库");
        }
       for (int i=0;i<questionEngines.size();i++){
           questionMapper.randQuestion(questionEngines.get(i).getId(),rules.get(i).getCount());
       }
        return null;
    }
}

package com.mju.generatepaper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mju.generatepaper.common.PageParams;
import com.mju.generatepaper.common.ResultFactory;
import com.mju.generatepaper.entity.Question;
import com.mju.generatepaper.entity.Subject;
import com.mju.generatepaper.mapper.QuestionMapper;
import com.mju.generatepaper.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 试题表 服务实现类
 * </p>
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public IPage<Question> getList(Map map) {
        PageParams page = new PageParams(map);
        return questionMapper.getList(page,map);
    }
}

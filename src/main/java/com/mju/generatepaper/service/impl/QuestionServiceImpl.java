package com.mju.generatepaper.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mju.generatepaper.entity.Question;
import com.mju.generatepaper.mapper.QuestionMapper;
import com.mju.generatepaper.service.IQuestionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 试题表 服务实现类
 * </p>
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {

}

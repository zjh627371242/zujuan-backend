package com.mju.generatepaper.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mju.generatepaper.entity.Question;

import java.util.Map;

/**
 * <p>
 * 试题表 服务类
 * </p>
 */
public interface IQuestionService extends IService<Question> {

    IPage<Question> getList(Map map);
}

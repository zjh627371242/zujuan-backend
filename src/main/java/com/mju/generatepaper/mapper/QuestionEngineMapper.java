package com.mju.generatepaper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mju.generatepaper.entity.QuestionEngine;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 题目类型表 Mapper 接口
 * </p>
 */
@Mapper
@Repository
public interface QuestionEngineMapper extends BaseMapper<QuestionEngine> {
    List<QuestionEngine> randQuestionEngine(@Param(value = "limit") int limit);
}

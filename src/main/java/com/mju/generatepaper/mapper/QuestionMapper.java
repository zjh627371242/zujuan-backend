package com.mju.generatepaper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mju.generatepaper.entity.Question;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 试题表 Mapper 接口
 * </p>
 */
@Mapper
public interface QuestionMapper extends BaseMapper<Question> {

}

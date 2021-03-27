package com.mju.generatepaper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mju.generatepaper.common.PageParams;
import com.mju.generatepaper.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * <p>
 * 试题表 Mapper 接口
 * </p>
 */
@Mapper
@Repository
public interface QuestionMapper extends BaseMapper<Question> {

    IPage<Question> getList(@Param("pageParams") PageParams pageParams, @Param("queryfilter") Map<String, Object> queryfilter);
}

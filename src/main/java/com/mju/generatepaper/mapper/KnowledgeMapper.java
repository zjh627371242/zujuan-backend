package com.mju.generatepaper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mju.generatepaper.common.PageParams;
import com.mju.generatepaper.entity.Knowledge;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * <p>
 * 知识点 Mapper 接口
 * </p>
 */
@Mapper
@Repository
public interface KnowledgeMapper extends BaseMapper<Knowledge> {

    IPage<Knowledge> getList(@Param("pageParams") PageParams pageParams, @Param("queryfilter") Map<String, Object> queryfilter);
}

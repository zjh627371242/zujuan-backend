package com.mju.generatepaper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mju.generatepaper.common.PageParams;
import com.mju.generatepaper.entity.Subject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * <p>
 * 科目表 Mapper 接口
 * </p>
 */
@Mapper
@Repository
public interface SubjectMapper extends BaseMapper<Subject> {

    IPage<Subject> list(@Param("pageParams") PageParams pageParams, @Param("map") Map<Object,String> map);

}

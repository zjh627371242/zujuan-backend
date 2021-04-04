package com.mju.generatepaper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mju.generatepaper.entity.Papermx;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 试卷题目表 Mapper 接口
 * </p>
 */
@Mapper
@Repository
public interface PapermxMapper extends BaseMapper<Papermx> {

}

package com.mju.generatepaper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mju.generatepaper.entity.Notice;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 公告信息表 Mapper 接口
 * </p>
 */
@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {

}

package com.mju.generatepaper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mju.generatepaper.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}

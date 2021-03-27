package com.mju.generatepaper.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mju.generatepaper.common.Result;
import com.mju.generatepaper.entity.Subject;

import java.util.Map;

/**
 * <p>
 * 科目表 服务类
 * </p>
 */
public interface ISubjectService extends IService<Subject> {

    Result<IPage<Subject>> listSubject(Map map);
}

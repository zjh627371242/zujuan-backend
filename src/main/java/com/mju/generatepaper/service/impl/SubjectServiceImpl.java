package com.mju.generatepaper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mju.generatepaper.common.PageParams;
import com.mju.generatepaper.common.Result;
import com.mju.generatepaper.common.ResultFactory;
import com.mju.generatepaper.entity.Subject;
import com.mju.generatepaper.mapper.SubjectMapper;
import com.mju.generatepaper.service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 科目表 服务实现类
 * </p>
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements ISubjectService {
    @Autowired
    private SubjectMapper subjectMapper;

    @Override
    public Result<IPage<Subject>> listSubject(Map map) {
        QueryWrapper<Subject> queryWrapper=new QueryWrapper();
        queryWrapper.orderByDesc("id");
        PageParams pageParams=new PageParams(map);
        return ResultFactory.success(subjectMapper.list(pageParams, map));
    }
}

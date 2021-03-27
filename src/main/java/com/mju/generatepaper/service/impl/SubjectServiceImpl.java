package com.mju.generatepaper.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mju.generatepaper.entity.Subject;
import com.mju.generatepaper.mapper.SubjectMapper;
import com.mju.generatepaper.service.ISubjectService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 科目表 服务实现类
 * </p>
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements ISubjectService {

}

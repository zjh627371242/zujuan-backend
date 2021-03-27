package com.mju.generatepaper.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mju.generatepaper.entity.Paper;
import com.mju.generatepaper.mapper.PaperMapper;
import com.mju.generatepaper.service.IPaperService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 试卷表 服务实现类
 * </p>
 */
@Service
public class PaperServiceImpl extends ServiceImpl<PaperMapper, Paper> implements IPaperService {

}

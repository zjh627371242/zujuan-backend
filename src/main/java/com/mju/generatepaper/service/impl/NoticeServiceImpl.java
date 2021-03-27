package com.mju.generatepaper.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mju.generatepaper.entity.Notice;
import com.mju.generatepaper.mapper.NoticeMapper;
import com.mju.generatepaper.service.INoticeService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 公告信息表 服务实现类
 * </p>
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {

}

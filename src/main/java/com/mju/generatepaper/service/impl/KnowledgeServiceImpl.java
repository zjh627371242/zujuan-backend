package com.mju.generatepaper.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mju.generatepaper.entity.Knowledge;
import com.mju.generatepaper.mapper.KnowledgeMapper;
import com.mju.generatepaper.service.IKnowledgeService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 知识点 服务实现类
 * </p>
 */
@Service
public class KnowledgeServiceImpl extends ServiceImpl<KnowledgeMapper, Knowledge> implements IKnowledgeService {

}

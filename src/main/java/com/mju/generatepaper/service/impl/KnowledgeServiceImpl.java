package com.mju.generatepaper.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mju.generatepaper.common.PageParams;
import com.mju.generatepaper.entity.Knowledge;
import com.mju.generatepaper.mapper.KnowledgeMapper;
import com.mju.generatepaper.service.IKnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 知识点 服务实现类
 * </p>
 */
@Service
public class KnowledgeServiceImpl extends ServiceImpl<KnowledgeMapper, Knowledge> implements IKnowledgeService {
    @Autowired
    private KnowledgeMapper knowledgeMapper;

    @Override
    public IPage<Knowledge> getList(Map map) {
        PageParams page = new PageParams(map);
        return knowledgeMapper.getList(page,map);
    }
}

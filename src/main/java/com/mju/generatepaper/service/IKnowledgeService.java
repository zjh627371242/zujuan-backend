package com.mju.generatepaper.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mju.generatepaper.entity.Knowledge;

import java.util.Map;

/**
 * <p>
 * 知识点 服务类
 * </p>
 */
public interface IKnowledgeService extends IService<Knowledge> {

    IPage<Knowledge> getList(Map map);
}

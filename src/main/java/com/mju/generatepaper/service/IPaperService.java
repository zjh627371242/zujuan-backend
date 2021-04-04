package com.mju.generatepaper.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mju.generatepaper.common.Result;
import com.mju.generatepaper.entity.Paper;

/**
 * <p>
 * 试卷表 服务类
 * </p>
 */
public interface IPaperService extends IService<Paper> {

    Result autoPaper();

}

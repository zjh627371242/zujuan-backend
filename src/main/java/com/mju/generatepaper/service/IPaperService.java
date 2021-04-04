package com.mju.generatepaper.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mju.generatepaper.common.Result;
import com.mju.generatepaper.entity.ManualPaperParam;
import com.mju.generatepaper.entity.Paper;

import java.util.Map;

/**
 * <p>
 * 试卷表 服务类
 * </p>
 */
public interface IPaperService extends IService<Paper> {

    Result autoPaper(Map map);

    Result manualPaper(ManualPaperParam manualPaperParam);
}

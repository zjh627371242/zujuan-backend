package com.mju.generatepaper.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mju.generatepaper.entity.Question;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * <p>
 * 试题表 服务类
 * </p>
 */
public interface IQuestionService extends IService<Question> {

    /**
     * 分页显示试题
     **/
    IPage<Question> getList(Map map);

    /**
     * 打印题库
     **/
    void excel(Long id, HttpServletResponse httpServletRespons) throws IOException;
}

package com.mju.generatepaper.service.impl;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mju.generatepaper.common.PageParams;
import com.mju.generatepaper.common.ResultFactory;
import com.mju.generatepaper.entity.Question;
import com.mju.generatepaper.entity.Subject;
import com.mju.generatepaper.mapper.QuestionMapper;
import com.mju.generatepaper.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 试题表 服务实现类
 * </p>
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public IPage<Question> getList(Map map) {
        PageParams page = new PageParams(map);
        return questionMapper.getList(page,map);
    }

    @Override
    public String excel(Long id) {
        //根据科目id 查询试题列表
        List<Question> rows = questionMapper.getListBySubjectId(id);

        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("d:/题库.xlsx");

        //自定义标题别名
        writer.addHeaderAlias("content", "试题题目");
        writer.addHeaderAlias("knowledge.point", "知识点");
        writer.addHeaderAlias("knowledge.subject.title", "科目名称");

        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(2, "题库试题");
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(rows, true);
        // 关闭writer，释放内存
        writer.close();
        return null;
    }
}

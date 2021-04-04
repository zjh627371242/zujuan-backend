package com.mju.generatepaper.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mju.generatepaper.common.PageParams;
import com.mju.generatepaper.entity.Question;
import com.mju.generatepaper.entity.QuestionExcel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 试题表 Mapper 接口
 * </p>
 */
@Mapper
@Repository
public interface QuestionMapper extends BaseMapper<Question> {

    /**
     * 分页显示试题
     **/
    IPage<Question> getList(@Param("pageParams") PageParams pageParams, @Param("queryfilter") Map<String, Object> queryfilter);

    /**
     * //根据科目,知识点 查询试题列表
     **/
    List<QuestionExcel> getExportList(@Param("title") String title,@Param("point") String point);

    /**
     * 随机获取试题
     * @param questionEngineId
     * @param limit
     * @return
     */
    List<Question> randQuestion(@Param("questionEngineId") Long questionEngineId,@Param("point") String limit);
}

package com.mju.generatepaper.entity;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 手动组卷参数
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ManualPaperParam {


    /**
     * 试卷题目
     **/
    private String title;

    /**
     * 科目id
     **/
    private Long subjectId;

    private List<ManualPaperParamItem> paperParamItems;

}

package com.mju.generatepaper.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 手动组卷参数项
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ManualPaperParamItem {

    /**
     * 试题类型id
     **/
    private Long questionEngineId;

    /**
     * 试题数量
     **/
    private Integer count;

    /**
     * 试题分数
     **/
    private Integer score;



}

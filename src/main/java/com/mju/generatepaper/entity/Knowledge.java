package com.mju.generatepaper.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 知识点
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Knowledge implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 知识点名称
     */
    private String point;

    /**
     * 科目id
     */
    private Long subjectId;


}

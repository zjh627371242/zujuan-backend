package com.mju.generatepaper.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 试题表
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 试题id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 题目内容
     */
    private String content;

    /**
     * 题目类型id
     */
    private Long questionEngineId;

    /**
     * 难度等级
     */
    private Long level;

    /**
     * 知识点
     */
    private Long knowledgeId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 题目类型信息
     */
    @TableField(exist = false)
    private QuestionEngine questionEngine;

    /**
     * 知识点信息
     */
    @TableField(exist = false)
    private Knowledge knowledge;
}

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
    @TableField("question_ENGINE_id")
    private Long questionEngineId;

    /**
     * 难度等级
     */
    private Long level;

    /**
     * 科目id
     */
    private Long subjectId;

    /**
     * 知识点
     */
    private Long knowledge;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}

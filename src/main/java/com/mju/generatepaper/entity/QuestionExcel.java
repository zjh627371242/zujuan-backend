package com.mju.generatepaper.entity;

import lombok.Data;

@Data
public class QuestionExcel {
    /**
     * 题目内容
     */
    private String content;

    /**
     * 知识点名称
     */
    private String point;

    /**
     * 科目名称
     */
    private String title;
}

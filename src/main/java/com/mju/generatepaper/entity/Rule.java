package com.mju.generatepaper.entity;

public class Rule {
    private Integer score;
    private Integer count;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Rule(Integer score, Integer count) {
        this.score = score;
        this.count = count;
    }
}

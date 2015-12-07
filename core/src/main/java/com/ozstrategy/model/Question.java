package com.ozstrategy.model;

import com.ozstrategy.annotations.Table;

import java.util.Date;

/**
 * Created by lihao1 on 10/19/15.
 */
@Table(name = "t_question")
public class Question extends BaseEntity{
    private Long id;
    private String name;
    private String rule;
    private Long userId;
    private Date createDate;

    public Question() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}

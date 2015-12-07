package com.ozstrategy.webapp.command.model;
import com.ozstrategy.model.Question;

import java.io.Serializable;
import java.util.Date;

/**
* Created by lihao1 on 2015-10-20.
*/
public class QuestionCommand implements Serializable{
    private Long id;
    private String rule;
    private String name;
    private Long userId;
    private Date createDate;
    private String questionDes;
    public QuestionCommand() {
    }
    public QuestionCommand(Question model) {
        this.id=model.getId();
        this.rule=model.getRule();
        this.name=model.getName();
        this.userId=model.getId();
        this.createDate=model.getCreateDate();
        this.questionDes=new StringBuffer().append(this.name).append("(").append(this.rule).append("分)").toString();
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getRule() {
        return rule;
    }
    public void setRule(String rule) {
        this.rule = rule;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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

    public String getQuestionDes() {
        return questionDes;
    }

    public void setQuestionDes(String questionDes) {
        this.questionDes = questionDes;
    }
}

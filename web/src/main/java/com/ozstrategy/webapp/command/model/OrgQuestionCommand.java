package com.ozstrategy.webapp.command.model;
import com.ozstrategy.model.OrgQuestion;

import java.util.Date;

/**
* Created by lihao1 on 2015-10-20.
*/
public class OrgQuestionCommand {
    private Long id;
    private Long questionId;
    private Long userId;
    private String mark;
    private Date createDate;
    private Long surveyId;
    private Long orgId;
    private String surveyName;
    private String questionName;
    private String questionRule;
    public OrgQuestionCommand() {
    }
    public OrgQuestionCommand(OrgQuestion model) {
        this.id=model.getId();
        this.questionId=model.getQuestionId();
        this.userId=model.getUserId();
        this.mark=model.getMark();
        this.createDate=model.getCreateDate();
        this.surveyId=model.getSurveyId();
        this.surveyName=model.getSurveyName();
        this.questionName=model.getQuestionName();
        this.questionRule=model.getQuestionRule();
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getQuestionId() {
        return questionId;
    }
    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getMark() {
        return mark;
    }
    public void setMark(String mark) {
        this.mark = mark;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public Long getSurveyId() {
        return surveyId;
    }
    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getSurveyName() {
        return surveyName;
    }

    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public String getQuestionRule() {
        return questionRule;
    }

    public void setQuestionRule(String questionRule) {
        this.questionRule = questionRule;
    }
}

package com.ozstrategy.model;

import com.ozstrategy.annotations.Id;
import com.ozstrategy.annotations.Table;

import java.util.Date;

/**
 * Created by lihao1 on 10/20/15.
 */
@Table(name = "t_orgquestion")
public class OrgQuestion extends BaseEntity{
    @Id
    private Long id;
    private Long surveyId;
    private Long questionId;
    private Long orgId;
    private Date createDate;
    private Long userId;
    private String mark;
    private String surveyName;
    private String questionName;
    private String questionRule;

    public OrgQuestion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

package com.ozstrategy.model;

import com.ozstrategy.annotations.NamedQueries;
import com.ozstrategy.annotations.NamedQuery;
import com.ozstrategy.annotations.Table;

import java.util.Date;

/**
 * Created by lihao1 on 10/20/15.
 */
@Table(name = "t_orgsurveyaudit")
@NamedQueries({
        @NamedQuery(name = "listSurveys",query = "select s.* from t_survey s join t_surveyorg os on s.id=os.surveyId where s.verify=2 and os.orgId=:orgId "),
        @NamedQuery(name = "listSurveysCount",query = "select count(*) from t_survey s join t_surveyorg os on s.id=os.surveyId where s.verify=2 and os.orgId=:orgId ")
})
public class OrgSurveyAudit {
    private Long id;
    private Long orgId;
    private Long surveyId;
    private Long userId;
    private Double total;
    private Date createDate;
    private String surveyName;
    private String orgName;

    public OrgSurveyAudit() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getSurveyName() {
        return surveyName;
    }

    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}

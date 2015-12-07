package com.ozstrategy.webapp.command.model;
import com.ozstrategy.model.OrgSurveyAudit;

import java.util.Date;

/**
* Created by lihao1 on 2015-11-19.
*/
public class OrgSurveyAuditCommand {
    private Double total;
    private Long orgId;
    private Long id;
    private Long userId;
    private Date createDate;
    private Long surveyId;
    private String surveyName;
    private String orgName;
    private String allTotal;

    public OrgSurveyAuditCommand() {
    }
    public OrgSurveyAuditCommand(OrgSurveyAudit model) {
        this.total=model.getTotal();
        this.orgId=model.getOrgId();
        this.id=model.getId();
        this.userId=model.getUserId();
        this.createDate=model.getCreateDate();
        this.surveyId=model.getSurveyId();
        this.surveyName=model.getSurveyName();
        this.orgName=model.getOrgName();
    }

    public String getAllTotal() {
        return allTotal;
    }

    public void setAllTotal(String allTotal) {
        this.allTotal = allTotal;
    }

    public Double getTotal() {
        return total;
    }
    public void setTotal(Double total) {
        this.total = total;
    }
    public Long getOrgId() {
        return orgId;
    }
    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public Long getSurveyId() {
        return surveyId;
    }
    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
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

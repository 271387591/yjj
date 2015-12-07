package com.ozstrategy.webapp.command.model;
import com.ozstrategy.model.SurveyAudit;

import java.util.Date;

/**
* Created by lihao1 on 2015-11-18.
*/
public class SurveyAuditCommand {
    private Long id;
    private String remark;
    private Integer verify;
    private String name;
    private Date createDate;
    private Long surveyId;
    private Date updateDate;
    public SurveyAuditCommand() {
    }
    public SurveyAuditCommand(SurveyAudit model) {
        this.id=model.getId();
        this.remark=model.getRemark();
        this.verify=model.getVerify();
        this.name=model.getName();
        this.createDate=model.getCreateDate();
        this.surveyId=model.getSurveyId();
        this.updateDate=model.getUpdateDate();
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Integer getVerify() {
        return verify;
    }
    public void setVerify(Integer verify) {
        this.verify = verify;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
    public Date getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}

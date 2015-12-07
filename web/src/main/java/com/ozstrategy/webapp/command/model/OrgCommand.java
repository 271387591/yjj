package com.ozstrategy.webapp.command.model;
import com.ozstrategy.model.Org;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* Created by lihao1 on 2015-10-20.
*/
public class OrgCommand {
    private String picPath;
    private Long id;
    private String picUrl;
    private String description;
    private String orgNo;
    private String integrity;
    private Long userId;
    private String name;
    private Date createDate;
    private String picName;
    private Date lastUpdateDate;
    private List<OrgQuestionCommand> orgQuestions=new ArrayList<OrgQuestionCommand>();
    private List<SurveyCommand> surveys=new ArrayList<SurveyCommand>();
    private String address;
    private String dbr;
    private Integer type;
    private String xkzNo;
    private String fzjg;
    private Date fzDate;
    private Date startDate;
    private Date endDate;
    private String remark;
    private String cls;
    private String fzDateStr;
    private String endDateStr;

    private Long surveyId;
    private String surveyName;
    public OrgCommand() {
    }
    public OrgCommand(Org model) {
        this.picPath=model.getPicPath();
        this.id=model.getId();
        this.picUrl=model.getPicUrl();
        this.description=model.getDescription();
        this.orgNo=model.getOrgNo();
        this.integrity=model.getIntegrity();
        this.userId=model.getUserId();
        this.name=model.getName();
        this.createDate=model.getCreateDate();
        this.picName=model.getPicName();
        this.lastUpdateDate=model.getLastUpdateDate();
        this.address= model.getAddress();
        this.dbr= model.getDbr();
        this.type= model.getType();
        this.xkzNo=model.getXkzNo();
        this.fzjg=model.getFzjg();
        this.fzDate= model.getFzDate();
        this.startDate= model.getStartDate();
        this.endDate= model.getEndDate();
        this.remark= model.getRemark();
        this.cls= model.getCls();
    }
    public String getPicPath() {
        return picPath;
    }
    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPicUrl() {
        return picUrl;
    }
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getOrgNo() {
        return orgNo;
    }
    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }
    public String getIntegrity() {
        return integrity;
    }
    public void setIntegrity(String integrity) {
        this.integrity = integrity;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
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
    public String getPicName() {
        return picName;
    }
    public void setPicName(String picName) {
        this.picName = picName;
    }
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public List<OrgQuestionCommand> getOrgQuestions() {
        return orgQuestions;
    }

    public void setOrgQuestions(List<OrgQuestionCommand> orgQuestions) {
        this.orgQuestions = orgQuestions;
    }

    public List<SurveyCommand> getSurveys() {
        return surveys;
    }

    public void setSurveys(List<SurveyCommand> surveys) {
        this.surveys = surveys;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDbr() {
        return dbr;
    }

    public void setDbr(String dbr) {
        this.dbr = dbr;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getXkzNo() {
        return xkzNo;
    }

    public void setXkzNo(String xkzNo) {
        this.xkzNo = xkzNo;
    }

    public String getFzjg() {
        return fzjg;
    }

    public void setFzjg(String fzjg) {
        this.fzjg = fzjg;
    }

    public Date getFzDate() {
        return fzDate;
    }

    public void setFzDate(Date fzDate) {
        this.fzDate = fzDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCls() {
        return cls;
    }

    public void setCls(String cls) {
        this.cls = cls;
    }

    public String getFzDateStr() {
        return fzDateStr;
    }

    public void setFzDateStr(String fzDateStr) {
        this.fzDateStr = fzDateStr;
    }

    public String getEndDateStr() {
        return endDateStr;
    }

    public void setEndDateStr(String endDateStr) {
        this.endDateStr = endDateStr;
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
}

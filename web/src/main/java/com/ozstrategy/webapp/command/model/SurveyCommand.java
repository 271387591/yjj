package com.ozstrategy.webapp.command.model;
import com.ozstrategy.model.Survey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* Created by lihao1 on 2015-10-20.
*/
public class SurveyCommand implements Serializable {
    private Long id;
    private Integer verify;
    private Long userId;
    private String name;
    private Date createDate;
    private Date lastUpdateDate;
    private List<QuestionCommand> questions=new ArrayList<QuestionCommand>();
    private List<OrgCommand> orgs=new ArrayList<OrgCommand>();
    private List<SurveyAuditCommand> audits=new ArrayList<SurveyAuditCommand>();
    private List<OrgQuestionCommand> orgQuestions=new ArrayList<OrgQuestionCommand>();
    private Long orgId;
    private Boolean hasPj;
    private List<Map> orgps=new ArrayList<Map>();


    public SurveyCommand() {
    }
    public SurveyCommand(Survey model) {
        this.id=model.getId();
        this.verify=model.getVerify();
        this.userId=model.getUserId();
        this.name=model.getName();
        this.createDate=model.getCreateDate();
        this.lastUpdateDate=model.getLastUpdateDate();
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getVerify() {
        return verify;
    }
    public void setVerify(Integer verify) {
        this.verify = verify;
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
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public List<QuestionCommand> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionCommand> questions) {
        this.questions = questions;
    }

    public List<OrgCommand> getOrgs() {
        return orgs;
    }

    public void setOrgs(List<OrgCommand> orgs) {
        this.orgs = orgs;
    }

    public List<SurveyAuditCommand> getAudits() {
        return audits;
    }

    public void setAudits(List<SurveyAuditCommand> audits) {
        this.audits = audits;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Boolean getHasPj() {
        return hasPj;
    }

    public void setHasPj(Boolean hasPj) {
        this.hasPj = hasPj;
    }

    public List<OrgQuestionCommand> getOrgQuestions() {
        return orgQuestions;
    }

    public void setOrgQuestions(List<OrgQuestionCommand> orgQuestions) {
        this.orgQuestions = orgQuestions;
    }

    public List<Map> getOrgps() {
        return orgps;
    }

    public void setOrgps(List<Map> orgps) {
        this.orgps = orgps;
    }
}

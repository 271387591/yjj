package com.ozstrategy.model;

import com.ozstrategy.annotations.Id;
import com.ozstrategy.annotations.NamedQueries;
import com.ozstrategy.annotations.NamedQuery;
import com.ozstrategy.annotations.Table;

import java.util.Date;

/**
 * Created by lihao1 on 10/19/15.
 */
@Table(name = "t_org")
@NamedQueries({
        @NamedQuery(name = "listSurveys",query = "select s.*,oa.total from t_survey s join t_surveyorg os on s.id=os.surveyId left join t_orgsurveyaudit oa on (s.id=oa.surveyId and oa.orgId=os.orgId) where s.verify=2 and os.orgId=:orgId"),
        @NamedQuery(name = "listSurveysCount",query = "select count(*) from t_survey s join t_surveyorg os on s.id=os.surveyId left join t_orgsurveyaudit oa on (s.id=oa.surveyId and oa.orgId=os.orgId) where s.verify=2 and os.orgId=:orgId")
})
public class Org extends BaseEntity{
    @Id
    private Long id;
    private String name;
    private String orgNo;
    private String picName;
    private String picPath;
    private String picUrl;
    private String description;
    private Date createDate;
    private Date lastUpdateDate;
    private Long userId;
    private String integrity;
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

    public Org() {
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

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getIntegrity() {
        return integrity;
    }

    public void setIntegrity(String integrity) {
        this.integrity = integrity;
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
}

package com.ozstrategy.model;

import com.ozstrategy.annotations.Id;
import com.ozstrategy.annotations.Table;

import java.util.Date;

/**
 * Created by lihao1 on 11/18/15.
 */
@Table(name = "t_surveyaudit")
public class SurveyAudit extends BaseEntity{
    @Id
    private Long id;
    private String name;
    private Long surveyId;
    private Date createDate;
    private Date updateDate;
    private Integer verify=Integer.valueOf(0);
    private String remark;
    private Long verifyUserId;

    public SurveyAudit() {
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

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getVerify() {
        return verify;
    }

    public void setVerify(Integer verify) {
        this.verify = verify;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getVerifyUserId() {
        return verifyUserId;
    }

    public void setVerifyUserId(Long verifyUserId) {
        this.verifyUserId = verifyUserId;
    }
}

package com.ozstrategy.model;

import com.ozstrategy.annotations.Id;
import com.ozstrategy.annotations.NamedQueries;
import com.ozstrategy.annotations.NamedQuery;
import com.ozstrategy.annotations.Table;

import java.util.Date;

/**
 * Created by lihao1 on 10/19/15.
 */
@Table(name = "t_survey")
@NamedQueries({
        @NamedQuery(name = "listOrgs",query = "select s.* from t_org s join t_surveyorg os on s.id=os.orgId where os.surveyId=:surveyId "),
        @NamedQuery(name = "listPjOrgs",query = "select T.*,oa.total from (select s.*,os.surveyId from t_org s join t_surveyorg os on s.id=os.orgId where os.surveyId=:surveyId )T left join t_orgsurveyaudit oa on (T.id=oa.orgId and oa.surveyId=T.surveyId) "),
        @NamedQuery(name = "listOrgsCount",query = "select count(*) from t_org s join t_surveyorg os on s.id=os.orgId where os.surveyId=:surveyId "),
        @NamedQuery(name = "listQuestions",query = "select s.* from t_question s join t_surveyquestion os on s.id=os.questionId where os.surveyId=:surveyId "),
        @NamedQuery(name = "listQuestionsCount",query = "select count(*) from t_question s join t_surveyquestion os on s.id=os.questionId where os.surveyId=:surveyId "),
        @NamedQuery(name = "listLeaderSurvey",query = "select s.*,u.nickName from t_survey s join t_user u on s.userId=u.id where 1=1 "),
        @NamedQuery(name = "listLeaderSurveyCount",query = "select count(*) from t_survey s join t_user u on s.userId=u.id where 1=1 ")
})
public class Survey extends BaseEntity{
    @Id
    private Long id;
    private String name;
    private Date createDate;
    private Date lastUpdateDate;
    private Integer verify=Integer.valueOf(0);
    private Long userId;
    private Long verifyUserId;

    public Survey() {
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

    public Long getVerifyUserId() {
        return verifyUserId;
    }

    public void setVerifyUserId(Long verifyUserId) {
        this.verifyUserId = verifyUserId;
    }
}

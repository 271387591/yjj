package com.ozstrategy.dao.model.impl;

import com.ozstrategy.dao.impl.BaseDaoImpl;
import com.ozstrategy.dao.model.OrgDao;
import com.ozstrategy.model.Org;
import com.ozstrategy.model.Survey;
import org.springframework.stereotype.Repository;

import java.util.Set;


/**
* Created by lihao1 on 2015-10-20.
*/
@Repository("orgDao")
public class OrgDaoImpl extends BaseDaoImpl<Org> implements OrgDao{

    public OrgDaoImpl() {
    super(Org.class);
    }

    @Override
    public void delSurvey(Org org, Survey survey) {
        String sql="delete from t_surveyorg where surveyId=? and orgId=?";
        jdbcTemplate.update(sql,survey.getId(),org.getId());
    }

    @Override
    public void delOrg(Org org) {
        String sql="delete from t_surveyorg where orgId=?";
        jdbcTemplate.update(sql,org.getId());
    }

    @Override
    public void delOrgQuestion(Org org) {

    }

    @Override
    public void deleteSurvey(Long id) {
        String sql="delete from t_orgsurvey where orgId=?";
        jdbcTemplate.update(sql,id);
    }

    @Override
    public void addSurvey(Long id, Set<Long> sids) {
        for(Long s:sids){
            String sql="insert into t_orgsurvey(orgId,surveyId) values(?,?)";
            jdbcTemplate.update(sql,id,s);
        }


    }

    @Override
    public void removeSurvety(Long surveyId,Long orgId) {
        String sql="delete from t_orgsurvey where surveyId=? and orgId=?";
        jdbcTemplate.update(sql,surveyId,orgId);
    }

    @Override
    public void deleteSurveyBySid(Long surveyId) {
        String sql="delete from t_orgsurvey where surveyId=?";
        jdbcTemplate.update(sql,surveyId);
    }
}

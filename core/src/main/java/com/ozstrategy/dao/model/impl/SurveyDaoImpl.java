package com.ozstrategy.dao.model.impl;

import com.ozstrategy.dao.impl.BaseDaoImpl;
import com.ozstrategy.dao.model.SurveyDao;
import com.ozstrategy.model.Org;
import com.ozstrategy.model.Question;
import com.ozstrategy.model.Survey;
import org.springframework.stereotype.Repository;


/**
* Created by lihao1 on 2015-10-20.
*/
@Repository("surveyDao")
public class SurveyDaoImpl extends BaseDaoImpl<Survey> implements SurveyDao{

    public SurveyDaoImpl() {
    super(Survey.class);
    }

    @Override
    public void saveOrg(Survey survey, Org org) {
        String sql="insert into t_surveyorg(surveyId,orgId) values(?,?)";
        jdbcTemplate.update(sql,survey.getId(),org.getId());
    }

    @Override
    public void saveQuestion(Survey survey, Question question) {
        String sql="insert into t_surveyquestion(surveyId,questionId) values(?,?)";
        jdbcTemplate.update(sql,survey.getId(),question.getId());
    }

    @Override
    public void delOrg(Survey survey) {
        String sql="delete from t_surveyorg where surveyId=?";
        jdbcTemplate.update(sql,survey.getId());
    }

    @Override
    public void delQuestion(Survey survey) {
        String sql="delete from t_surveyquestion where surveyId=?";
        jdbcTemplate.update(sql,survey.getId());
    }
}

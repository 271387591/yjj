package com.ozstrategy.dao.model;
import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.model.Org;
import com.ozstrategy.model.Question;
import com.ozstrategy.model.Survey;

/**
* Created by lihao1 on 2015-10-20.
*/
public interface SurveyDao extends BaseDao<Survey> {
    void saveOrg(Survey survey,Org org);
    void saveQuestion(Survey survey,Question question);
    void delOrg(Survey survey);
    void delQuestion(Survey survey);
}

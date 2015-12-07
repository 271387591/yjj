package com.ozstrategy.dao.model;
import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.model.Question;

/**
* Created by lihao1 on 2015-10-20.
*/
public interface QuestionDao extends BaseDao<Question> {
    void delSurveyQuestion(Question question);
}

package com.ozstrategy.dao.model.impl;

import com.ozstrategy.dao.impl.BaseDaoImpl;
import com.ozstrategy.dao.model.QuestionDao;
import com.ozstrategy.model.Question;
import org.springframework.stereotype.Repository;


/**
* Created by lihao1 on 2015-10-20.
*/
@Repository("questionDao")
public class QuestionDaoImpl extends BaseDaoImpl<Question> implements QuestionDao{

    public QuestionDaoImpl() {
    super(Question.class);
    }

    @Override
    public void delSurveyQuestion(Question question) {
        String sql="delete from t_surveyquestion where questionId=?";
        jdbcTemplate.update(sql,question.getId());
    }
}

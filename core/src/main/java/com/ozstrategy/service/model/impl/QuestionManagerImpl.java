package com.ozstrategy.service.model.impl;

import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.dao.model.QuestionDao;
import com.ozstrategy.model.Question;
import com.ozstrategy.service.impl.BaseManagerImpl;
import com.ozstrategy.service.model.QuestionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* Created by lihao1 on 6/8/15.
*/
@Service("questionManager")
public class QuestionManagerImpl extends BaseManagerImpl<Question> implements QuestionManager {
    @Autowired
    private QuestionDao questionDao;

    @Override
    public BaseDao<Question> baseDao() {
        return questionDao;
    }

    @Override
    public void delQuestion(Question question) {
        questionDao.delSurveyQuestion(question);
        questionDao.delete(question);
    }
}

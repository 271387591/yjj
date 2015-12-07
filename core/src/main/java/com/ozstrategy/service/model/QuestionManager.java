package com.ozstrategy.service.model;

import com.ozstrategy.model.Question;
import com.ozstrategy.service.BaseManager;

/**
* Created by lihao1 on 2015-10-20.
*/
public interface QuestionManager extends BaseManager<Question> {
    void delQuestion(Question question);
}

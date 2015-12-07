package com.ozstrategy.service.model;

import com.ozstrategy.model.Question;
import com.ozstrategy.model.Survey;
import com.ozstrategy.model.SurveyAudit;
import com.ozstrategy.service.BaseManager;

import java.util.List;
import java.util.Set;

/**
* Created by lihao1 on 2015-10-20.
*/
public interface SurveyManager extends BaseManager<Survey> {
    void saveSurvey(Survey survey,Set<Long> questions,Set<Long> orgs);
    void delSurvey(Survey survey);

    void verifySurvey(SurveyAudit surveyAudit);


    void deletensurvey(Long id);
    void deletepsurvey(Long id);
}

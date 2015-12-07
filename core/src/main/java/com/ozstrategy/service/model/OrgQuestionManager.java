package com.ozstrategy.service.model;

import com.ozstrategy.model.OrgQuestion;
import com.ozstrategy.model.OrgSurveyAudit;
import com.ozstrategy.service.BaseManager;

import java.util.List;

/**
* Created by lihao1 on 2015-10-20.
*/
public interface OrgQuestionManager extends BaseManager<OrgQuestion> {
    void submitorgquestion(List<OrgQuestion> questions,OrgSurveyAudit surveyAudit);
    void updateorgquestion(List<OrgQuestion> questions,OrgSurveyAudit surveyAudit);
}

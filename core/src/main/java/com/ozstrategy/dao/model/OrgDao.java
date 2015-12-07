package com.ozstrategy.dao.model;
import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.model.Org;
import com.ozstrategy.model.Survey;

import java.util.Set;

/**
* Created by lihao1 on 2015-10-20.
*/
public interface OrgDao extends BaseDao<Org> {
    void delSurvey(Org org, Survey survey);
    void delOrg(Org org);
    void delOrgQuestion(Org org);


    void deleteSurvey(Long id);
    void addSurvey(Long id,Set<Long> sids);
    void removeSurvety(Long surveyId,Long orgId);
    void deleteSurveyBySid(Long surveyId);
}

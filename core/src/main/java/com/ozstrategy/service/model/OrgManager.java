package com.ozstrategy.service.model;

import com.ozstrategy.model.Org;
import com.ozstrategy.model.Survey;
import com.ozstrategy.service.BaseManager;

import java.util.Set;

/**
* Created by lihao1 on 2015-10-20.
*/
public interface OrgManager extends BaseManager<Org> {
    void delSurvey(Org org,Survey survey);
    void delOrg(Org org);


    void addSurvey(Long id,Set<Long> sids);
    void removeSurvety(Long surveyId,Long orgId);
    void deleteOrg(Long orgId);
}

package com.ozstrategy.service.model.impl;

import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.dao.model.OrgQuestionDao;
import com.ozstrategy.dao.model.OrgSurveyAuditDao;
import com.ozstrategy.model.OrgQuestion;
import com.ozstrategy.model.OrgSurveyAudit;
import com.ozstrategy.service.impl.BaseManagerImpl;
import com.ozstrategy.service.model.OrgQuestionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Created by lihao1 on 6/8/15.
*/
@Service("orgQuestionManager")
public class OrgQuestionManagerImpl extends BaseManagerImpl<OrgQuestion> implements OrgQuestionManager {
    @Autowired
    private OrgQuestionDao orgQuestionDao;
    @Autowired
    private OrgSurveyAuditDao orgSurveyAuditDao;


    @Override
    public BaseDao<OrgQuestion> baseDao() {
        return orgQuestionDao;
    }

    @Override
    public void submitorgquestion(List<OrgQuestion> questions, OrgSurveyAudit surveyAudit) {
        batchSave(questions);
        Map map=new HashMap();
        map.put("Q_orgId_EQ",surveyAudit.getOrgId());
        map.put("Q_surveyId_EQ",surveyAudit.getSurveyId());
        orgSurveyAuditDao.deleteByParam(map);
        orgSurveyAuditDao.save(surveyAudit);

    }

    @Override
    public void updateorgquestion(List<OrgQuestion> questions, OrgSurveyAudit surveyAudit) {
        batchUpdate(questions);
        Map map=new HashMap();
        map.put("Q_orgId_EQ",surveyAudit.getOrgId());
        map.put("Q_surveyId_EQ",surveyAudit.getSurveyId());
        orgSurveyAuditDao.deleteByParam(map);
        orgSurveyAuditDao.save(surveyAudit);
    }
}

package com.ozstrategy.service.model.impl;

import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.dao.model.SurveyAuditDao;
import com.ozstrategy.model.SurveyAudit;
import com.ozstrategy.service.impl.BaseManagerImpl;
import com.ozstrategy.service.model.SurveyAuditManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* Created by lihao1 on 6/8/15.
*/
@Service("surveyAuditManager")
public class SurveyAuditManagerImpl extends BaseManagerImpl<SurveyAudit> implements SurveyAuditManager {
    @Autowired
    private SurveyAuditDao surveyAuditDao;

    @Override
    public BaseDao<SurveyAudit> baseDao() {
        return surveyAuditDao;
    }
}

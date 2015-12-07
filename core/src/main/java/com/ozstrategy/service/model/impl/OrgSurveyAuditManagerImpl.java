package com.ozstrategy.service.model.impl;

import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.dao.model.OrgSurveyAuditDao;
import com.ozstrategy.model.OrgSurveyAudit;
import com.ozstrategy.service.impl.BaseManagerImpl;
import com.ozstrategy.service.model.OrgSurveyAuditManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* Created by lihao1 on 6/8/15.
*/
@Service("orgSurveyAuditManager")
public class OrgSurveyAuditManagerImpl extends BaseManagerImpl<OrgSurveyAudit> implements OrgSurveyAuditManager {
    @Autowired
    private OrgSurveyAuditDao orgSurveyAuditDao;

    @Override
    public BaseDao<OrgSurveyAudit> baseDao() {
        return orgSurveyAuditDao;
    }
}

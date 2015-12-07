package com.ozstrategy.dao.model.impl;

import com.ozstrategy.dao.impl.BaseDaoImpl;
import com.ozstrategy.dao.model.OrgSurveyAuditDao;
import com.ozstrategy.model.OrgSurveyAudit;
import org.springframework.stereotype.Repository;


/**
* Created by lihao1 on 2015-11-19.
*/
@Repository("orgSurveyAuditDao")
public class OrgSurveyAuditDaoImpl extends BaseDaoImpl<OrgSurveyAudit> implements OrgSurveyAuditDao{

    public OrgSurveyAuditDaoImpl() {
    super(OrgSurveyAudit.class);
    }

}
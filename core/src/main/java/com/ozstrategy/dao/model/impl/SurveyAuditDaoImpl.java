package com.ozstrategy.dao.model.impl;

import com.ozstrategy.dao.impl.BaseDaoImpl;
import com.ozstrategy.dao.model.SurveyAuditDao;
import com.ozstrategy.model.SurveyAudit;
import org.springframework.stereotype.Repository;


/**
* Created by lihao1 on 2015-11-18.
*/
@Repository("surveyAuditDao")
public class SurveyAuditDaoImpl extends BaseDaoImpl<SurveyAudit> implements SurveyAuditDao{

    public SurveyAuditDaoImpl() {
    super(SurveyAudit.class);
    }

}
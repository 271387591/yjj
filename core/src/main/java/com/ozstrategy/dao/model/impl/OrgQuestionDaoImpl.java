package com.ozstrategy.dao.model.impl;

import com.ozstrategy.dao.impl.BaseDaoImpl;
import com.ozstrategy.dao.model.OrgQuestionDao;
import com.ozstrategy.model.OrgQuestion;
import org.springframework.stereotype.Repository;


/**
* Created by lihao1 on 2015-10-20.
*/
@Repository("orgQuestionDao")
public class OrgQuestionDaoImpl extends BaseDaoImpl<OrgQuestion> implements OrgQuestionDao{

    public OrgQuestionDaoImpl() {
    super(OrgQuestion.class);
    }

}
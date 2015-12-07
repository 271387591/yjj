package com.ozstrategy.service.model.impl;

import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.dao.model.OrgDao;
import com.ozstrategy.dao.model.OrgQuestionDao;
import com.ozstrategy.model.Org;
import com.ozstrategy.model.OrgQuestion;
import com.ozstrategy.model.Survey;
import com.ozstrategy.service.impl.BaseManagerImpl;
import com.ozstrategy.service.model.OrgManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
* Created by lihao1 on 6/8/15.
*/
@Service("orgManager")
public class OrgManagerImpl extends BaseManagerImpl<Org> implements OrgManager {
    @Autowired
    private OrgDao orgDao;
    @Autowired
    private OrgQuestionDao orgQuestionDao;


    @Override
    public BaseDao<Org> baseDao() {
        return orgDao;
    }

    @Override
    public void delSurvey(Org org, Survey survey) {
        orgDao.delSurvey(org,survey);
    }

    @Override
    public void delOrg(Org org) {
        orgDao.delOrg(org);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("Q_orgId_EQ",org.getId());
        orgQuestionDao.deleteByParam(map);
        orgDao.delete(org);

    }

    @Override
    public void addSurvey(Long id, Set<Long> sids) {
        orgDao.deleteSurvey(id);
        orgDao.addSurvey(id,sids);
    }

    @Override
    public void removeSurvety(Long surveyId,Long orgId) {
        orgDao.removeSurvety(surveyId,orgId);
    }

    @Override
    public void deleteOrg(Long orgId) {
        orgDao.deleteSurvey(orgId);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("Q_orgId_EQ",orgId);
        orgQuestionDao.deleteByParam(map);
        orgDao.deleteById(orgId);
    }
}

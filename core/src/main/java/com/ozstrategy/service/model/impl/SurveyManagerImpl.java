package com.ozstrategy.service.model.impl;

import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.dao.model.OrgDao;
import com.ozstrategy.dao.model.OrgQuestionDao;
import com.ozstrategy.dao.model.QuestionDao;
import com.ozstrategy.dao.model.SurveyAuditDao;
import com.ozstrategy.dao.model.SurveyDao;
import com.ozstrategy.model.Org;
import com.ozstrategy.model.OrgQuestion;
import com.ozstrategy.model.Question;
import com.ozstrategy.model.Survey;
import com.ozstrategy.model.SurveyAudit;
import com.ozstrategy.service.impl.BaseManagerImpl;
import com.ozstrategy.service.model.SurveyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
* Created by lihao1 on 6/8/15.
*/
@Service("surveyManager")
public class SurveyManagerImpl extends BaseManagerImpl<Survey> implements SurveyManager {
    @Autowired
    private SurveyDao surveyDao;
    @Autowired
    private SurveyAuditDao surveyAuditDao;

    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private OrgQuestionDao orgQuestionDao;
    @Autowired
    private OrgDao orgDao;




    @Override
    public BaseDao<Survey> baseDao() {
        return surveyDao;
    }

    @Override
    public void saveSurvey(Survey survey, Set<Long> questions, Set<Long> orgs) {
        surveyDao.saveOrUpdate(survey);
        surveyDao.delOrg(survey);
        surveyDao.delQuestion(survey);
        for(Long id:questions){
            Question question=questionDao.get(id);
            if(question!=null)
            surveyDao.saveQuestion(survey,question);
        }
        for(Long id:orgs){
            Org question=orgDao.get(id);
            if(question!=null)
            surveyDao.saveOrg(survey, question);
        }

    }

    @Override
    public void delSurvey(Survey survey) {
        surveyDao.delOrg(survey);
        surveyDao.delQuestion(survey);
        surveyDao.delete(survey);
    }

    @Override
    public void verifySurvey(SurveyAudit surveyAudit) {
        Survey survey=surveyDao.get(surveyAudit.getSurveyId());
        survey.setVerify(surveyAudit.getVerify());
        survey.setLastUpdateDate(new Date());
        survey.setVerifyUserId(surveyAudit.getVerifyUserId());
        surveyDao.update(survey);
        surveyAuditDao.save(surveyAudit);
    }

    @Override
    public void deletensurvey(Long id) {
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("Q_surveyId_EQ",id);
        questionDao.deleteByParam(map);
        surveyDao.deleteById(id);
    }

    @Override
    public void deletepsurvey(Long id) {
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("Q_surveyId_EQ",id);
        questionDao.deleteByParam(map);
        orgQuestionDao.deleteByParam(map);
        orgDao.deleteSurveyBySid(id);
        surveyDao.deleteById(id);

    }
}

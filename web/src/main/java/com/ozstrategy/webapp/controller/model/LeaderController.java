package com.ozstrategy.webapp.controller.model;

import com.ozstrategy.model.Org;
import com.ozstrategy.model.Question;
import com.ozstrategy.model.Survey;
import com.ozstrategy.model.SurveyAudit;
import com.ozstrategy.model.user.User;
import com.ozstrategy.service.model.OrgManager;
import com.ozstrategy.service.model.OrgQuestionManager;
import com.ozstrategy.service.model.QuestionManager;
import com.ozstrategy.service.model.SurveyAuditManager;
import com.ozstrategy.service.model.SurveyManager;
import com.ozstrategy.service.user.UserManager;
import com.ozstrategy.webapp.command.JsonReaderResponse;
import com.ozstrategy.webapp.command.JsonReaderSingleResponse;
import com.ozstrategy.webapp.command.model.OrgCommand;
import com.ozstrategy.webapp.command.model.QuestionCommand;
import com.ozstrategy.webapp.command.model.SurveyAuditCommand;
import com.ozstrategy.webapp.command.model.SurveyCommand;
import com.ozstrategy.webapp.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihao1 on 10/21/15.
 */
@RestController
@RequestMapping("leader")
public class LeaderController extends BaseController{
    @Autowired
    private OrgManager orgManager;
    @Autowired
    private UserManager userManager;
    @Autowired
    private SurveyManager surveyManager;
    @Autowired
    private QuestionManager questionManager;
    @Autowired
    private OrgQuestionManager orgQuestionManager;
    @Autowired
    private SurveyAuditManager surveyAuditManager;

    @RequestMapping("index")
    public ModelAndView org(HttpServletRequest request){
        return new ModelAndView("leader/leader");
    }
    @RequestMapping("lsurvey")
    public ModelAndView lsurvey(HttpServletRequest request){
        return new ModelAndView("leader/lsurvey");
    }
    @RequestMapping("listsurvey")
    public JsonReaderResponse listsurvey(HttpServletRequest request){
        Map<String,Object> map=requestMap(request);
        try{
            map.put("Q_s.lastUpdateDate","DESC");
            List<Map<String,Object>> models= surveyManager.findByNamedQuery("listLeaderSurvey", map, obtainStart(request), obtainLimit(request));
            Integer count=surveyManager.findByNamedQueryClass("listLeaderSurveyCount", Integer.class, map);
            return new JsonReaderResponse(models,true,count,"");
        }catch (Exception e){
            logger.error("list fail",e);
        }
        return new JsonReaderResponse(Collections.emptyList(),false,"请求错误");
    }
    @RequestMapping("verifysurvey")
    public JsonReaderSingleResponse viergysurvey(@RequestBody SurveyAuditCommand command,HttpServletRequest request){
        try{
            SurveyAudit surveyAudit=new SurveyAudit();
            surveyAudit.setName(command.getName());
            surveyAudit.setVerify(command.getVerify());
            surveyAudit.setSurveyId(command.getSurveyId());
            surveyAudit.setCreateDate(new Date());
            surveyAudit.setUpdateDate(new Date());
            surveyAudit.setRemark(command.getRemark());
            User user=userManager.getUserByUsername(request.getRemoteUser());
            surveyAudit.setVerifyUserId(user.getId());
            surveyManager.verifySurvey(surveyAudit);
            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("list fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"审核失败");
    }

    @RequestMapping("looksurvey/{id}")
    public ModelAndView pjorgsurvey(@PathVariable Long id) {
        SurveyCommand command=null;
        try{
            Survey survey=surveyManager.get(id);
            if(survey!=null){
                command=new SurveyCommand(survey);
                Map map=new HashMap();
                map.put("surveyId",id);
                List<QuestionCommand> questionCommands=new ArrayList<QuestionCommand>();
                List<Question> questions=surveyManager.findByNamedQuery("listQuestions", Question.class,map);
                if(questions!=null && questions.size()>0){
                    for(Question question:questions){
                        questionCommands.add(new QuestionCommand(question));
                    }
                }
                List<OrgCommand> orgCommands=new ArrayList<OrgCommand>();
                List<Org> orgs=surveyManager.findByNamedQuery("listOrgs", Org.class,map);
                if(orgs!=null && orgs.size()>0){
                    for(Org question:orgs){
                        orgCommands.add(new OrgCommand(question));
                    }
                }
                command.getOrgs().clear();
                command.getOrgs().addAll(orgCommands);
                command.getQuestions().clear();
                command.getQuestions().addAll(questionCommands);
            }
        }catch (Exception e){
            logger.error("edit fail",e);
        }
        return new ModelAndView("leader/lsurveydetail","command",command);
    }
    @RequestMapping("lsurveytime/{id}")
    public ModelAndView surveytime(@PathVariable Long id,HttpServletRequest request) {
        SurveyCommand command=null;
        try{
            Map map=new HashMap();
            map.put("Q_id_EQ",id);
            Survey survey=surveyManager.getByParam(map);
            if(survey!=null){
                command=new SurveyCommand(survey);
                map=new HashMap();
                map.put("Q_surveyId_EQ",id);
                List<SurveyAudit> surveyAudits=surveyAuditManager.listAll(map);
                List<SurveyAuditCommand> surveyAuditCommands=new ArrayList<SurveyAuditCommand>();
                if(surveyAudits!=null && surveyAudits.size()>0){
                    for(SurveyAudit surveyAudit:surveyAudits){
                        surveyAuditCommands.add(new SurveyAuditCommand(surveyAudit));
                    }
                }
                command.getAudits().clear();
                command.getAudits().addAll(surveyAuditCommands);
            }
        }catch (Exception e){
            logger.error("edit fail",e);
        }
        return new ModelAndView("leader/lsurveytime","command",command);
    }




}

package com.ozstrategy.webapp.controller.model;

import com.ozstrategy.model.Advert;
import com.ozstrategy.model.News;
import com.ozstrategy.model.Notice;
import com.ozstrategy.model.Org;
import com.ozstrategy.model.OrgQuestion;
import com.ozstrategy.model.OrgSurveyAudit;
import com.ozstrategy.model.Survey;
import com.ozstrategy.model.user.User;
import com.ozstrategy.service.model.AdvertManager;
import com.ozstrategy.service.model.FriendLinkManager;
import com.ozstrategy.service.model.NewsManager;
import com.ozstrategy.service.model.NoticeManager;
import com.ozstrategy.service.model.OrgManager;
import com.ozstrategy.service.model.OrgQuestionManager;
import com.ozstrategy.service.model.OrgSurveyAuditManager;
import com.ozstrategy.service.model.QuestionManager;
import com.ozstrategy.service.model.SurveyManager;
import com.ozstrategy.service.user.UserManager;
import com.ozstrategy.webapp.command.JsonReaderResponse;
import com.ozstrategy.webapp.command.JsonReaderSingleResponse;
import com.ozstrategy.webapp.command.model.OrgCommand;
import com.ozstrategy.webapp.command.model.OrgQuestionCommand;
import com.ozstrategy.webapp.command.model.OrgSurveyAuditCommand;
import com.ozstrategy.webapp.command.model.SurveyCommand;
import com.ozstrategy.webapp.controller.BaseController;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihao1 on 10/17/15.
 */
@RequestMapping("web")
@RestController
public class WebMvcController extends BaseController{
    @Autowired
    private FriendLinkManager friendLinkManager;
    @Autowired
    private NewsManager newsManager;
    @Autowired
    private NoticeManager noticeManager;
    @Autowired
    private AdvertManager advertManager;
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
    private OrgSurveyAuditManager orgSurveyAuditManager;




//    private static String imgUrl=System.getProperty("img.url");
    @RequestMapping("index")
    public ModelAndView index(HttpServletRequest request){
        Map<String,Object> map=new HashMap<String, Object>();
//        map.put("imgUrl",imgUrl);
        return new ModelAndView("web/index","homeCommand",map);
    }
    @RequestMapping("listFried")
    public JsonReaderResponse listFried(HttpServletRequest request){
        Map<String,Object> map=requestMap(request);
        map.put("Q_publish_EQ",1);
        map.put("Q_publishDate","DESC");
        try{
            List<Map<String,Object>> models= friendLinkManager.listMap(map);
            return new JsonReaderResponse(models,true,0,"");
        }catch (Exception e){
            logger.error("list fail",e);
        }
        return new JsonReaderResponse(Collections.emptyList(),false,"请求错误");
    }
    @RequestMapping("listorg")
    public JsonReaderResponse list(HttpServletRequest request){
        Map<String,Object> map=requestMap(request);
        try{
            Integer page=parseInteger(request.getParameter("page"));

            map.put("Q_createDate","DESC");
            map.put("Q_type_NEQ","4");
            List<Map<String,Object>> models= orgManager.listPageMap(map, (page-1)*obtainLimit(request), obtainLimit(request), "id","picUrl",  "createDate", "name","orgNo","integrity","address","dbr","type","xkzNo","fzjg","fzDate","startDate","endDate","remark","cls");
            Integer count=orgManager.count(map);
            return new JsonReaderResponse(models,count,page,obtainLimit(request),"");
        }catch (Exception e){
            logger.error("list fail",e);
        }
        return new JsonReaderResponse(Collections.emptyList(),false,"请求错误");
    }
    @RequestMapping("org/{id}")
    public ModelAndView org(@PathVariable Long id){
        Org org=orgManager.get(id);
        return new ModelAndView("web/orgdetail","command",new OrgCommand(org));
    }
    @RequestMapping("orgsurvey/{id}")
    public ModelAndView orgsurvey(@PathVariable Long id){
        Org org=orgManager.get(id);
        return new ModelAndView("web/list","command",new OrgCommand(org));
    }
    @RequestMapping("listorgsurvey/{orgId}")
    public JsonReaderResponse listorgsurvey(HttpServletRequest request,@PathVariable Long orgId){
        Map<String,Object> map=requestMap(request);
        try{
            List<OrgSurveyAuditCommand> surveyCommands=new ArrayList<OrgSurveyAuditCommand>();
            map.put("Q_orgId_EQ",orgId);
            Integer page=parseInteger(request.getParameter("page"));
            List<OrgSurveyAudit> surveys=orgSurveyAuditManager.list(map, (page-1)*obtainLimit(request), obtainLimit(request));
            if(surveys!=null && surveys.size()>0){
                for(OrgSurveyAudit question:surveys){
                    OrgSurveyAuditCommand surveyCommand=new OrgSurveyAuditCommand(question);
                    map=new HashMap();
                    map.put("Q_orgId_EQ",orgId);
                    map.put("Q_surveyId_EQ",surveyCommand.getSurveyId());
                    List<OrgQuestion> orgQuestions=orgQuestionManager.list(map,0,Integer.MAX_VALUE);
                    Double allTotal=0d;
                    if(orgQuestions!=null && orgQuestions.size()>0){
                        for(OrgQuestion orgQuestion:orgQuestions){
                            allTotal+=Double.valueOf(orgQuestion.getQuestionRule());
                        }
                    }
                    surveyCommand.setAllTotal(allTotal.toString());
                    surveyCommands.add(surveyCommand);
                }
            }
            Integer count=orgSurveyAuditManager.count(map);
            return new JsonReaderResponse(surveyCommands,count,page,obtainLimit(request),"");
        }catch (Exception e){
            logger.error("list fail",e);
        }
        return new JsonReaderResponse(Collections.emptyList(),false,"请求错误");
    }

    @RequestMapping("orgquestion/{orgId}/{surveyId}")
    public ModelAndView orgquestion(@PathVariable Long orgId,@PathVariable Long surveyId,HttpServletRequest request){
        Map map=new HashMap();
        map.put("Q_orgId_EQ",orgId);
        map.put("Q_surveyId_EQ",surveyId);
        OrgCommand command=new OrgCommand(orgManager.get(orgId));
        List<OrgQuestionCommand> orgQuestionCommands=new ArrayList<OrgQuestionCommand>();
        List<OrgQuestion> orgQuestions=orgQuestionManager.list(map,obtainStart(request),obtainLimit(request));
        if(orgQuestions!=null && orgQuestions.size()>0){
            for(OrgQuestion orgQuestion:orgQuestions){
                orgQuestionCommands.add(new OrgQuestionCommand(orgQuestion));
            }
        }
        Survey survey=surveyManager.get(surveyId);
        command.setSurveyId(surveyId);
        command.setSurveyName(survey.getName());
        command.getOrgQuestions().clear();
        command.getOrgQuestions().addAll(orgQuestionCommands);
        return new ModelAndView("web/orgquestion","command",command);
    }



    @RequestMapping("listNews")
    public JsonReaderResponse listNews(HttpServletRequest request){
        Map<String,Object> map=requestMap(request);
        map.put("Q_publish_EQ",1);
        map.put("Q_publishDate","DESC");
        try{
            List<Map<String,Object>> models= newsManager.listPageMap(map, obtainStart(request), obtainLimit(request), "id", "publish", "createDate", "publishDate", "title", "idx", "pubUnit");
            Integer count=newsManager.count(map);
            return new JsonReaderResponse(models,true,count,"");
        }catch (Exception e){
            logger.error("list fail",e);
        }
        return new JsonReaderResponse(Collections.emptyList(),false,"请求错误");
    }
//    @RequestMapping("listNotice")
//    public JsonReaderResponse listNotice(HttpServletRequest request){
//        Map<String,Object> map=requestMap(request);
//        map.put("Q_publish_EQ",1);
//        map.put("Q_publishDate","DESC");
//        try{
//            List<Map<String,Object>> models= noticeManager.listPageMap(map, obtainStart(request), obtainLimit(request), "id", "publish", "createDate", "publishDate", "title", "idx", "pubUnit");
//            Integer count=noticeManager.count(map);
//            return new JsonReaderResponse(models,true,count,"");
//        }catch (Exception e){
//            logger.error("list fail",e);
//        }
//        return new JsonReaderResponse(Collections.emptyList(),false,"请求错误");
//    }
//    @RequestMapping("listAdvert")
//    public JsonReaderResponse listAdvert(HttpServletRequest request){
//        Map<String,Object> map=requestMap(request);
//        map.put("Q_publish_EQ",1);
//        map.put("Q_publishDate","DESC");
//        try{
//            List<Map<String,Object>> models= advertManager.listPageMap(map, obtainStart(request), obtainLimit(request), "id", "publish","createDate","publishDate","title","idx","pubUnit","picUrl","picName");
//            Integer count=advertManager.count(map);
//            return new JsonReaderResponse(models,true,count,"");
//        }catch (Exception e){
//            logger.error("list fail",e);
//        }
//        return new JsonReaderResponse(Collections.emptyList(),false,"请求错误");
//    }
//
//    @RequestMapping("list/{type}")
//    public ModelAndView list(@PathVariable String type){
//        Map map=new HashMap();
//        map.put("type",type);
//        return new ModelAndView("web/list","command",map);
//    }
//    @RequestMapping("search/{type}/{search}")
//    public ModelAndView search(@PathVariable String type,@PathVariable String search){
//        Map map=new HashMap();
//        map.put("type",type);
//        map.put("search",search);
//        return new ModelAndView("web/list","command",map);
//    }
//
//    @RequestMapping("detail/{type}/{id}")
//    public ModelAndView detail(@PathVariable String type,@PathVariable Long id){
//        Map map=new HashMap();
//        map.put("type",type);
////        map.put("imgUrl",imgUrl);
//        Map command=new HashMap();
//        if(StringUtils.equals(type,"news")){
//            command=newsManager.queryMap(id);
//        }else if(StringUtils.equals(type,"notice")){
//            command=noticeManager.queryMap(id);
//        }else if(StringUtils.equals(type,"advert")){
//            command=advertManager.queryMap(id);
//        }else if(StringUtils.equals(type,"friend")){
//            command=friendLinkManager.queryMap(id);
//        }
//        map.putAll(command);
//        return new ModelAndView("web/detail","command",map);
//    }
//    @RequestMapping("count/{type}/{id}")
//    public JsonReaderSingleResponse count(@PathVariable String type,@PathVariable Long id){
//        if(StringUtils.equals(type,"news")){
//            News news=newsManager.get(id);
//            Integer num = news.getReadNum();
//            if(num==null){
//                num=0;
//            }
//            num++;
//            news.setReadNum(num);
//            newsManager.update(news);
//        }else if(StringUtils.equals(type,"notice")){
//            Notice news=noticeManager.get(id);
//            Integer num = news.getReadNum();
//            if(num==null){
//                num=0;
//            }
//            num++;
//            news.setReadNum(num);
//            noticeManager.update(news);
//        }else if(StringUtils.equals(type,"advert")){
//            Advert news=advertManager.get(id);
//            Integer num = news.getReadNum();
//            if(num==null){
//                num=0;
//            }
//            num++;
//            news.setReadNum(num);
//            advertManager.update(news);
//        }
//        return new JsonReaderSingleResponse(true);
//    }
//    @RequestMapping("org")
//    public ModelAndView org(){
//        return new ModelAndView("web/org");
//    }
//    @RequestMapping("orgdetail/{id}")
//    public ModelAndView orgdetail(@PathVariable Long id){
//        Org org=orgManager.get(id);
//        OrgCommand command=new OrgCommand(org);
//        return new ModelAndView("web/orgdetail","command",command);
//    }
//
//    @RequestMapping("listOrg")
//    public JsonReaderResponse listOrg(HttpServletRequest request){
//        Map<String,Object> map=requestMap(request);
//        map.put("Q_orgNo","ASC");
//        try{
//            List<Map<String,Object>> models= orgManager.listPageMap(map, obtainStart(request), obtainLimit(request), "id","createDate","orgNo","name","integrity");
//            Integer count=orgManager.count(map);
//            return new JsonReaderResponse(models,true,count,"");
//        }catch (Exception e){
//            logger.error("list fail",e);
//        }
//        return new JsonReaderResponse(Collections.emptyList(),false,"请求错误");
//    }
//    @RequestMapping("orgsurvey/{id}")
//    public ModelAndView orgsurvey1(@PathVariable Long id){
//        Org org=orgManager.get(id);
//        OrgCommand command=new OrgCommand(org);
//        return new ModelAndView("web/orgsurvey","command",command);
//    }
//    @RequestMapping("listorgsurvey/{orgId}")
//    public JsonReaderResponse listorgsurvey(HttpServletRequest request,@PathVariable Long orgId){
//        Map<String,Object> map=requestMap(request);
//        try{
//            map.put("orgId",orgId);
//            map.put("Q_s.lastUpdateDate","DESC");
//            List<Map<String,Object>> models= surveyManager.findByNamedQuery("listallorgsurvey",map, obtainStart(request), obtainLimit(request));
//            Integer count=surveyManager.findByNamedQueryClass("listallorgsurveyCount", Integer.class, map);
//            return new JsonReaderResponse(models,true,count,"");
//        }catch (Exception e){
//            logger.error("list fail",e);
//        }
//        return new JsonReaderResponse(Collections.emptyList(),false,"请求错误");
//    }
//    @RequestMapping("rpjorgsurvey/{orgId}/{surveyId}")
//    public ModelAndView listorgquestion(@PathVariable Long orgId,@PathVariable Long surveyId,HttpServletRequest request){
//        SurveyCommand command=null;
//        try{
//            Map<String,Object> map=new HashMap<String, Object>();
//            map.put("Q_surveyId_EQ",surveyId);
//            map.put("Q_orgId_EQ",orgId);
//            Survey org=surveyManager.get(surveyId);
//            command=new SurveyCommand(org);
//            List<OrgQuestion> questions=orgQuestionManager.listAll(map);
//            List<OrgQuestionCommand> orgQuestionCommands=new ArrayList<OrgQuestionCommand>();
//            if(questions!=null && questions.size()>0){
//                for(OrgQuestion question:questions){
//                    OrgQuestionCommand questionCommand=new OrgQuestionCommand(question);
//                    orgQuestionCommands.add(questionCommand);
//                }
//            }
//        }catch (Exception e){
//            logger.error("save fail",e);
//        }
//        return new ModelAndView("web/osurveydetail","command",command);
//    }





}

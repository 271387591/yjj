package com.ozstrategy.webapp.controller.model;

import com.ozstrategy.Constants;
import com.ozstrategy.model.Org;
import com.ozstrategy.model.OrgQuestion;
import com.ozstrategy.model.OrgSurveyAudit;
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
import com.ozstrategy.util.ExcelUtils;
import com.ozstrategy.webapp.command.JsonReaderResponse;
import com.ozstrategy.webapp.command.JsonReaderSingleResponse;
import com.ozstrategy.webapp.command.model.OrgCommand;
import com.ozstrategy.webapp.command.model.OrgQuestionCommand;
import com.ozstrategy.webapp.command.model.QuestionCommand;
import com.ozstrategy.webapp.command.model.SurveyAuditCommand;
import com.ozstrategy.webapp.command.model.SurveyCommand;
import com.ozstrategy.webapp.controller.BaseController;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by lihao1 on 10/20/15.
 */
@RestController
@RequestMapping("assess")
public class AssessController extends BaseController{
    @Autowired
    private OrgManager orgManager;
    @Autowired
    private UserManager userManager;
    @Autowired
    private SurveyManager surveyManager;
    @Autowired
    private SurveyAuditManager surveyAuditManager;

    @Autowired
    private QuestionManager questionManager;
    @Autowired
    private OrgQuestionManager orgQuestionManager;
    private static final Integer XB=1;
    private static final Integer BG=2;
    private static final Integer YX=3;
    private static final Integer ZX=4;



    @RequestMapping("changeUserPwd")
    public JsonReaderSingleResponse changeUserPwd(HttpServletRequest request){
        try{
            String oldpwd=request.getParameter("oldpassword");
            String pwd=request.getParameter("password");
            User user=userManager.getUserByUsername(request.getRemoteUser());
            boolean reult = userManager.changePassword(user, pwd, oldpwd, true);
            return new JsonReaderSingleResponse(reult);
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"修改失败");
    }

    @RequestMapping("index")
    public ModelAndView index(HttpServletRequest request){
        return new ModelAndView("assess/assess");
    }
    @RequestMapping("org")
    public ModelAndView org(HttpServletRequest request){
        return new ModelAndView("assess/org");
    }
    @RequestMapping("addorg")
    public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("assess/addOrg","command",new Org());
    }
    @RequestMapping("imorg")
    public ModelAndView imorg(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("assess/imOrg","command",new Org());
    }

    @RequestMapping("editorg/{id}")
    public ModelAndView editUser(@PathVariable Long id) {
        Map<String,Object> command=null;
        try{
            Map map=new HashMap();
            map.put("Q_id_EQ",id);
            command=orgManager.queryMap(map);
        }catch (Exception e){
            logger.error("edit fail",e);
        }
        return new ModelAndView("assess/addOrg","command",command);
    }
    @RequestMapping("listorg")
    public JsonReaderResponse list(HttpServletRequest request){
        Map<String,Object> map=requestMap(request);
        try{
            User user=userManager.getUserByUsername(request.getRemoteUser());
            map.put("Q_userId_EQ",user.getId());
            map.put("Q_createDate","DESC");
            List<Map<String,Object>> models= orgManager.listPageMap(map, obtainStart(request), obtainLimit(request), "id","picUrl",  "createDate", "name","orgNo","integrity","address","dbr","type","xkzNo","fzjg","fzDate","startDate","endDate","remark","cls");
            Integer count=orgManager.count(map);
            return new JsonReaderResponse(models,true,count,"");
        }catch (Exception e){
            logger.error("list fail",e);
        }
        return new JsonReaderResponse(Collections.emptyList(),false,"请求错误");
    }
    @RequestMapping("uploadorg")
    public ModelAndView save(HttpServletRequest request,HttpServletResponse response){
        String name        = request.getParameter("name");
        String orgNo = request.getParameter("orgNo");
        String id = request.getParameter("id");
        String address = request.getParameter("address");
        String dbr = request.getParameter("dbr");
        String type = request.getParameter("type");
        String xkzNo = request.getParameter("xkzNo");
        String fzjg = request.getParameter("fzjg");
        String fzDate = request.getParameter("fzDate");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String remark = request.getParameter("remark");
        String description = request.getParameter("editorValue");
        String cls = request.getParameter("cls");
        if(isNotEmpty(description)){
            description=description.replaceAll("\r|\n","");
        }
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) { }

        if (writer == null) {
            return null;
        }
        Map<String,Object> objectMap=new HashMap<String, Object>();
        objectMap.put("Q_orgNo_EQ",orgNo);
        Org advert=null;


        if(StringUtils.isEmpty(id)){
            advert=orgManager.getByParam(objectMap);
            if(advert!=null){
                String msg = "该机构已存在";
                writer.print("{\"success\":false,\"msg\":\"" + msg + "!\"}");
                return null;
            }
            advert=new Org();
            advert.setRemark("新办");
            advert.setCreateDate(new Date());
            advert.setType(XB);
        }else{
            advert=orgManager.get(parseLong(id));
            Org org=orgManager.getByParam(objectMap);
            if(org!=null && !org.getId().equals(advert.getId())){
                String msg = "该机构已存在";
                writer.print("{\"success\":false,\"msg\":\"" + msg + "!\"}");
                return null;
            }
        }
        User user=userManager.getUserByUsername(request.getRemoteUser());
        if(user!=null){
            advert.setUserId(user.getId());
        }
        advert.setDescription(description);
        advert.setName(name);
        advert.setOrgNo(orgNo);
        advert.setAddress(address);
        advert.setDbr(dbr);

        advert.setXkzNo(xkzNo);
        advert.setFzjg(fzjg);
        advert.setFzDate(parseDate(fzDate));
        advert.setStartDate(parseDate(fzDate));
        advert.setEndDate(parseDate(endDate));
        advert.setCls(cls);
        String attachDir = randomAbsolutePath(request, Constants.updloadAdvert);
        File fileOnServer = null;
        String logo1Name=null,logo1Url=null,logo1Path=null;
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator list             = multipartRequest.getFileNames();
            while (list.hasNext()) {
                String               controlName = list.next().toString();
                MultipartFile file        = multipartRequest.getFile(controlName);
                CommonsMultipartFile cmf         = (CommonsMultipartFile) file;
                DiskFileItem fileItem    = (DiskFileItem) cmf.getFileItem();

                if(StringUtils.equals(controlName,"pic")){
                    logo1Name    = fileItem.getName();
                    if(isEmpty(logo1Name)){
                        continue;
                    }
                    String str         = randomName(logo1Name);
                    fileOnServer      = new File(attachDir,str);
                    logo1Path=fileOnServer.getAbsolutePath();
                    logo1Url=Constants.updloadAdvert+"/"+str;
                }
                fileItem.write(fileOnServer);
            }
            if(StringUtils.isNotEmpty(logo1Name)){
                try{
                    FileUtils.forceDelete(new File(advert.getPicPath()));
                }catch (Exception e){
                }
                advert.setPicName(logo1Name);
                advert.setPicPath(logo1Path);
                advert.setPicUrl(logo1Url);
            }
            orgManager.saveOrUpdate(advert);
            writer.print("{\"success\":true,\"msg\":\"" + "" + "\"}");

        } catch (Exception e) {
            logger.error("upload sensor fail", e);

            String msg = "上传失败";
            writer.print("{\"success\":false,\"msg\":\"" + msg + "!\"}");
            e.printStackTrace();

            if (fileOnServer != null) {
                try {
                    FileUtils.forceDelete(fileOnServer);
                } catch (IOException e1) { }
            }

        }
        writer.close();
        return null;
    }
    @RequestMapping("importorg")
    public ModelAndView importorg(HttpServletRequest request,HttpServletResponse response){

        User user=userManager.getUserByUsername(request.getRemoteUser());
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) { }

        if (writer == null) {
            return null;
        }

        String attachDir = randomAbsolutePath(request, Constants.updloadAdvert);
        File fileOnServer = null;
        String logo1Name=null,logo1Url=null,logo1Path=null;
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator list             = multipartRequest.getFileNames();
            while (list.hasNext()) {
                String               controlName = list.next().toString();
                MultipartFile file        = multipartRequest.getFile(controlName);
                CommonsMultipartFile cmf         = (CommonsMultipartFile) file;
                DiskFileItem fileItem    = (DiskFileItem) cmf.getFileItem();

                if(StringUtils.equals(controlName,"pic")){
                    logo1Name    = fileItem.getName();
                    if(isEmpty(logo1Name)){
                        continue;
                    }
                    String str         = randomName(logo1Name);
                    fileOnServer      = new File(attachDir,str);
                    logo1Path=fileOnServer.getAbsolutePath();
                    logo1Url=Constants.updloadAdvert+"/"+str;
                }
                fileItem.write(fileOnServer);
            }
            if(fileOnServer!=null){
                ExcelUtils.Column column1=new ExcelUtils.Column(0,"编号",0,0,"orgNo");
                ExcelUtils.Column column2=new ExcelUtils.Column(1,"单位名称",0,0,"name");
                ExcelUtils.Column column3=new ExcelUtils.Column(2,"机构",0,0,"address");
                ExcelUtils.Column column4=new ExcelUtils.Column(3,"法定代表人",0,0,"dbr");
                ExcelUtils.Column column5=new ExcelUtils.Column(4,"类别",0,0,"cls");
                ExcelUtils.Column column6=new ExcelUtils.Column(5,"备注",0,0,"remark");
                ExcelUtils.Column column7=new ExcelUtils.Column(6,"许可证编号",0,0,"xkzNo");
                ExcelUtils.Column column8=new ExcelUtils.Column(7,"发证机关",0,0,"fzjg");
                ExcelUtils.Column column9=new ExcelUtils.Column(8,"发证日期",0,0,"fzDate");
                ExcelUtils.Column column10=new ExcelUtils.Column(9,"过期日期",0,0,"endDate");
                ExcelUtils.Columns columns=new ExcelUtils.Columns();
                columns.addColumn(column1)
                        .addColumn(column2)
                        .addColumn(column3)
                        .addColumn(column4)
                        .addColumn(column5)
                        .addColumn(column6)
                        .addColumn(column7)
                        .addColumn(column8)
                        .addColumn(column9)
                        .addColumn(column10);
                ExcelUtils.Row row=new ExcelUtils.Row(0,columns);
                ExcelUtils.Rows rowss=new ExcelUtils.Rows();
                rowss.addRow(row);
                InputStream inputStream=new FileInputStream(fileOnServer);
                List<Map<String,String>> mapList=ExcelUtils.readExcel(rowss,inputStream,0);
                if(mapList!=null && mapList.size()>0){
                    for(Map<String,String> map:mapList){
                        String name=map.get("name");
                        String orgNo=map.get("orgNo");
                        String address = map.get("address");
                        String dbr = map.get("dbr");
                        String type = map.get("type");
                        String xkzNo = map.get("xkzNo");
                        String fzjg = map.get("fzjg");
                        String fzDate = map.get("fzDate");
                        String startDate = fzDate;
                        String endDate = map.get("endDate");
                        String remark = map.get("remark");
                        if(remark!=null){
                            remark=remark.trim();
                        }
                        String cls = map.get("cls");
                        if(cls!=null){
                            cls=cls.trim();
                        }



                        Map<String,Object> parmas=new HashMap<String, Object>();
                        parmas.put("Q_orgNo_EQ",orgNo);
                        Org org=orgManager.getByParam(parmas);
                        if(org==null){
                            org=new Org();
                            org.setOrgNo(orgNo);
                            org.setCreateDate(new Date());
                            org.setType(XB);
                        }
                        if(StringUtils.equals("食品生产企业",cls)){
                            org.setCls("1");
                        }else if(StringUtils.equals("食品销售企业",cls)){
                            org.setCls("2");
                        }else if(StringUtils.equals("餐饮企业",cls)){
                            org.setCls("3");
                        }else if(StringUtils.equals("药品经营企业",cls)){
                            org.setCls("4");
                        }

                        if(StringUtils.equals("变更",remark)){
                            org.setType(BG);
                        }else if(StringUtils.equals("延续",remark)){
                            org.setType(YX);
                        }else if(StringUtils.equals("注销",remark)){
                            org.setType(ZX);
                        }else{
                            org.setType(XB);
                        }
                        org.setName(name);
                        org.setAddress(address);
                        org.setLastUpdateDate(new Date());

                        org.setDbr(dbr);
                        org.setXkzNo(xkzNo);
                        org.setFzjg(fzjg);
                        org.setFzDate(parseDate(fzDate));
                        org.setStartDate(parseDate(startDate));
                        org.setEndDate(parseDate(endDate));
                        org.setRemark(remark);

                        org.setUserId(user.getId());
                        orgManager.saveOrUpdate(org);
                    }
                }
                inputStream.close();
                FileUtils.forceDelete(fileOnServer);
            }
            writer.print("{\"success\":true,\"msg\":\"" + "" + "\"}");

        } catch (Exception e) {
            logger.error("upload sensor fail", e);

            String msg = "上传失败";
            writer.print("{\"success\":false,\"msg\":\"" + msg + "!\"}");
            e.printStackTrace();

            if (fileOnServer != null) {
                try {
                    FileUtils.forceDelete(fileOnServer);
                } catch (IOException e1) { }
            }

        }
        writer.close();
        return null;
    }


    @RequestMapping("deleteorg/{id}")
    public JsonReaderSingleResponse delete(@PathVariable Long id){
        try{
            Org org=orgManager.get(id);
            if(org!=null){
                orgManager.delOrg(org);
            }
            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"删除失败");
    }
    @RequestMapping("zxorg/{id}")
    public JsonReaderSingleResponse zx(@PathVariable Long id){
        try{
            Org org=orgManager.get(id);
            if(org!=null){
                org.setType(ZX);
                org.setRemark("注销");
                orgManager.update(org);
            }
            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"注销失败");
    }
    @RequestMapping("bgorg/{id}")
    public ModelAndView bgorg(@PathVariable Long id) {
        Map<String,Object> command=null;
        try{
            Map map=new HashMap();
            map.put("Q_id_EQ",id);
            command=orgManager.queryMap(map);
        }catch (Exception e){
            logger.error("edit fail",e);
        }
        return new ModelAndView("assess/bgOrg","command",command);
    }
    @RequestMapping("bgorg")
    public JsonReaderSingleResponse bgorgs(@RequestBody OrgCommand command){
        try{
            Long id=command.getId();
            if(id!=null){
                Org org=orgManager.get(id);
                if(org!=null){
                    org.setName(command.getName());
                    org.setAddress(command.getAddress());
                    org.setFzjg(command.getFzjg());
                    org.setDbr(command.getDbr());
                    org.setCls(command.getCls());
                    org.setXkzNo(command.getXkzNo());
                    org.setFzDate(parseDate(command.getFzDateStr()));
                    org.setEndDate(parseDate(command.getEndDateStr()));
                    org.setType(BG);
                    org.setRemark("变更");
                    orgManager.update(org);
                }
            }
            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"注销失败");
    }
     @RequestMapping("yxorg")
    public JsonReaderSingleResponse yxorg(@RequestBody OrgCommand command){
        try{
            Long id=command.getId();
            if(id!=null){
                Org org=orgManager.get(id);
                if(org!=null){
                    org.setEndDate(parseDate(command.getEndDateStr()));
                    org.setType(YX);
                    org.setRemark("延续");
                    orgManager.update(org);
                }
            }
            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"延续失败");
    }
//question
    @RequestMapping("question")
    public ModelAndView question(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("assess/question","command",new Question());
    }
    @RequestMapping("addquestion")
    public ModelAndView addquestion(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("assess/addQuestion","command",new Question());
    }
    @RequestMapping("editquestion/{id}")
    public ModelAndView editquestion(@PathVariable Long id) {
        Map<String,Object> command=null;
        try{
            Map map=new HashMap();
            map.put("Q_id_EQ",id);
            command=questionManager.queryMap(map);
        }catch (Exception e){
            logger.error("edit fail",e);
        }
        return new ModelAndView("assess/addQuestion","command",command);
    }
    @RequestMapping("listquestion")
    public JsonReaderResponse listquestion(HttpServletRequest request){
        Map<String,Object> map=requestMap(request);
        try{
            User user=userManager.getUserByUsername(request.getRemoteUser());
            map.put("Q_userId_EQ",user.getId());
            map.put("Q_createDate","DESC");
            List<Map<String,Object>> models= questionManager.listPageMap(map, obtainStart(request), obtainLimit(request), "id",  "createDate", "name","rule");
            if(models!=null && models.size()>0){
                for(Map<String,Object> objectMap:models){
                    objectMap.put("questionDes", new StringBuffer().append(objectMap.get("name")).append("（").append(objectMap.get("rule")).append("分）").toString());
                }
            }
            Integer count=questionManager.count(map);
            return new JsonReaderResponse(models,true,count,"");
        }catch (Exception e){
            logger.error("list fail",e);
        }
        return new JsonReaderResponse(Collections.emptyList(),false,"请求错误");
    }
    @RequestMapping("savequestion")
    public JsonReaderSingleResponse savequestion(@RequestBody QuestionCommand command,HttpServletRequest request){
        try{
            String username=request.getRemoteUser();
            User user=userManager.getUserByUsername(username);
            Long id=command.getId();
            Question question=null;
            if(id!=null){
                question=questionManager.get(id);
            }else{
                question=new Question();
                question.setCreateDate(new Date());
            }
            question.setName(command.getName());
            question.setRule(command.getRule());
            question.setUserId(user.getId());
            questionManager.saveOrUpdate(question);
            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"保存失败");
    }
    @RequestMapping("deletequestion/{id}")
    public JsonReaderSingleResponse deletequestion(@PathVariable Long id){
        try{
            Question question=questionManager.get(id);
            if(question!=null){
                questionManager.delQuestion(question);
            }
            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"删除失败");
    }
//survey
    @RequestMapping("survey")
    public ModelAndView survey(HttpServletRequest request){
        return new ModelAndView("assess/survey");
    }
    @RequestMapping("addsurvey")
    public ModelAndView addsurvey(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("assess/addSurvey","command",new SurveyCommand());
    }

    @RequestMapping("deletensurvey/{id}")
    public JsonReaderSingleResponse deletensurvey(@PathVariable Long id){
        try{
            Survey survey=surveyManager.get(id);
            if(survey!=null){
                surveyManager.delSurvey(survey);
                return new JsonReaderSingleResponse(true);
            }
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"删除失败");
    }

    @RequestMapping("editsurvey/{id}")
    public ModelAndView editsurvey(@PathVariable Long id,HttpServletRequest request) {
        SurveyCommand command=null;
        try{
            Map map=new HashMap();
            map.put("Q_surveyId_EQ",id);
            Survey survey=surveyManager.get(id);
            if(survey!=null){
                command=new SurveyCommand(survey);
                map=new HashMap();
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
        return new ModelAndView("assess/addSurvey","command",command);
    }
    @RequestMapping("detailsurvey/{id}")
    public ModelAndView detailsurvey(@PathVariable Long id,HttpServletRequest request) {
        SurveyCommand command=null;
        try{
            Map map=new HashMap();
            map.put("Q_surveyId_EQ",id);
            Survey survey=surveyManager.get(id);
            if(survey!=null){
                command=new SurveyCommand(survey);
                map=new HashMap();
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
        return new ModelAndView("assess/surveydetail","command",command);
    }
    @RequestMapping("surveytime/{id}")
    public ModelAndView surveytime(@PathVariable Long id,HttpServletRequest request) {
        SurveyCommand command=null;
        try{
            User user=userManager.getUserByUsername(request.getRemoteUser());
            Map map=new HashMap();
            map.put("Q_id_EQ",id);
            map.put("Q_userId_EQ",user.getId());
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
        return new ModelAndView("assess/surveytime","command",command);
    }


    @RequestMapping("listsurvey")
    public JsonReaderResponse listsurvey(HttpServletRequest request){
        Map<String,Object> map=requestMap(request);
        try{
            User user=userManager.getUserByUsername(request.getRemoteUser());
            map.put("Q_userId_EQ",user.getId());
            map.put("Q_lastUpdateDate","DESC");
            List<Map<String,Object>> models= surveyManager.listPageMap(map, obtainStart(request), obtainLimit(request), "id","lastUpdateDate","createDate", "name","verify");
            Integer count=surveyManager.count(map);
            return new JsonReaderResponse(models,true,count,"");
        }catch (Exception e){
            logger.error("list fail",e);
        }
        return new JsonReaderResponse(Collections.emptyList(),false,"请求错误");
    }
    @RequestMapping("savesurvey")
    public JsonReaderSingleResponse<SurveyCommand> save(@RequestBody SurveyCommand command,HttpServletRequest request){
        try{
            Long id=command.getId();
            Survey news=null;
            if(id==null){
                news=new Survey();
                news.setCreateDate(new Date());
            }else{
                news=surveyManager.get(id);
            }
            User user=userManager.getUserByUsername(request.getRemoteUser());
            news.setName(command.getName());
            news.setUserId(user.getId());
            news.setLastUpdateDate(new Date());
            List<QuestionCommand> questionCommands=command.getQuestions();
            Set<Long> questions=new HashSet<Long>();
            if(questionCommands!=null && questionCommands.size()>0){
                for(QuestionCommand questionCommand:questionCommands){
                    questions.add(questionCommand.getId());
                }
            }
            List<OrgCommand> orgCommands=command.getOrgs();
            Set<Long> orgs=new HashSet<Long>();
            if(orgCommands!=null && orgCommands.size()>0){
                for(OrgCommand questionCommand:orgCommands){
                    orgs.add(questionCommand.getId());
                }
            }
            surveyManager.saveSurvey(news, questions,orgs);
            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"保存失败");
    }
    @RequestMapping("verifysurvey/{id}")
    public JsonReaderSingleResponse<SurveyCommand> verify(@PathVariable Long id){
        try{

            Survey news=surveyManager.get(id);
            news.setVerify(1);
            news.setLastUpdateDate(new Date());
            surveyManager.update(news);
            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"保存失败");
    }
    @RequestMapping("surveypj/{id}")
    public ModelAndView surveypj(@PathVariable Long id,HttpServletRequest request) {
        SurveyCommand command=null;
        try{
            Map map=new HashMap();
            map.put("Q_surveyId_EQ",id);
            Survey survey=surveyManager.get(id);
            if(survey!=null){
                command=new SurveyCommand(survey);
                map=new HashMap();
                map.put("surveyId",id);
                List<QuestionCommand> questionCommands=new ArrayList<QuestionCommand>();
                List<Question> questions=surveyManager.findByNamedQuery("listQuestions", Question.class,map);
                if(questions!=null && questions.size()>0){
                    for(Question question:questions){
                        questionCommands.add(new QuestionCommand(question));
                    }
                }
                List<Map<String,Object>> orgs=surveyManager.findByNamedQuery("listPjOrgs",map);
                command.getOrgps().clear();
                command.getOrgps().addAll(orgs);
                command.getQuestions().clear();
                command.getQuestions().addAll(questionCommands);
            }
        }catch (Exception e){
            logger.error("edit fail",e);
        }
        return new ModelAndView("assess/surveypj","command",command);
    }




//survey org
    @RequestMapping("orgsurvey/{id}")
    public ModelAndView orgsurvey(@PathVariable Long id) {
        Org org=orgManager.get(id);
        OrgCommand orgCommand=new OrgCommand(org);
        return new ModelAndView("assess/orgsurvey","command",orgCommand);
    }
    @RequestMapping("listorgsurvey/{orgId}")
    public JsonReaderResponse listorgsurvey(HttpServletRequest request,@PathVariable Long orgId){
        Map<String,Object> map=requestMap(request);
        try{
//            List<SurveyCommand> surveyCommands=new ArrayList<SurveyCommand>();
            map.put("orgId",orgId);
            List<Map<String,Object>> surveys=orgManager.findByNamedQuery("listSurveys", map);
            if(surveys!=null && surveys.size()>0){

                for(Map<String,Object> question:surveys){
                    question.put("orgId",orgId);
//                    SurveyCommand surveyCommand=new SurveyCommand(question);
//                    surveyCommand.setOrgId(orgId);
                    Map<String,Object> params=new HashMap<String, Object>();
                    params.put("Q_surveyId_EQ",question.get("id"));
                    params.put("Q_orgId_EQ",orgId);
                    Map orgq=orgQuestionManager.queryMap(params);
                    question.put("hasPj",orgq!=null&&orgq.size()>0);
//                    surveyCommand.setHasPj(orgq!=null&&orgq.size()>0);
//                    surveyCommands.add(surveyCommand);
                }
            }
            Integer count=orgManager.findByNamedQueryClass("listSurveysCount", Integer.class, map);
            return new JsonReaderResponse(surveys,true,count,"");
        }catch (Exception e){
            logger.error("list fail",e);
        }
        return new JsonReaderResponse(Collections.emptyList(),false,"请求错误");
    }

    @RequestMapping("saveorgsurvey/{id}")
    public JsonReaderSingleResponse<SurveyCommand> saveorgsurvey(@PathVariable Long id,HttpServletRequest request){
        try{
            String sid=request.getParameter("surveyIds");
            if(isNotEmpty(sid)){
                String[] sids=sid.split(",");
                Set<Long> ids=new HashSet<Long>();
                for(String s:sids){
                    ids.add(parseLong(s));
                }
                orgManager.addSurvey(id,ids);
            }
            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"保存失败");
    }
    @RequestMapping("removeorgsurvey/{id}/{orgId}")
    public JsonReaderSingleResponse<SurveyCommand> removeorgsurvey(@PathVariable Long id,@PathVariable Long orgId){
        try{
            Org org=orgManager.get(orgId);
            Survey survey=surveyManager.get(id);
            if(org!=null && survey!=null){
                orgManager.delSurvey(org,survey);
            }
            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"保存失败");
    }
    @RequestMapping("pjorgsurvey/{id}/{orgId}")
    public ModelAndView pjorgsurvey(@PathVariable Long id,@PathVariable Long orgId,HttpServletRequest request) {
        SurveyCommand command=null;
        try{
            Map map=new HashMap();
            map.put("Q_surveyId_EQ",id);
            Survey survey=surveyManager.get(id);
            if(survey!=null){
                command=new SurveyCommand(survey);
                command.setOrgId(orgId);
                map=new HashMap();
                map.put("surveyId",id);
                List<QuestionCommand> questionCommands=new ArrayList<QuestionCommand>();
                List<Question> questions=surveyManager.findByNamedQuery("listQuestions", Question.class,map);
                if(questions!=null && questions.size()>0){
                    for(Question question:questions){
                        questionCommands.add(new QuestionCommand(question));
                    }
                }

                command.getQuestions().clear();
                command.getQuestions().addAll(questionCommands);
            }
        }catch (Exception e){
            logger.error("edit fail",e);
        }
        return new ModelAndView("assess/pjorgsurvey","command",command);
    }
     @RequestMapping("submitorgquestion/{orgId}/{surveyId}")
    public JsonReaderSingleResponse submitorgquestion(@RequestBody List<OrgQuestionCommand> commands,@PathVariable Long orgId,@PathVariable Long surveyId,HttpServletRequest request){
        try{
            User user=userManager.getUserByUsername(request.getRemoteUser());
            List<OrgQuestion> questions=new ArrayList<OrgQuestion>();
            Double total=Double.valueOf(0);
            if(commands!=null && commands.size()>0){
                for(OrgQuestionCommand command:commands){
                    OrgQuestion question=new OrgQuestion();
                    question.setOrgId(orgId);
                    question.setSurveyId(surveyId);
                    question.setMark(command.getMark());
                    question.setCreateDate(new Date());
                    question.setQuestionId(command.getQuestionId());
                    question.setUserId(user.getId());
                    question.setSurveyName(command.getSurveyName());
                    question.setQuestionName(command.getQuestionName());
                    question.setQuestionRule(command.getQuestionRule());
                    questions.add(question);
                    total+=Double.valueOf(question.getMark());
                }
            }
            if(questions.size()>0){
                Survey survey=surveyManager.get(surveyId);
                Org org=orgManager.get(orgId);
                OrgSurveyAudit surveyAudit=new OrgSurveyAudit();
                surveyAudit.setOrgId(orgId);
                surveyAudit.setSurveyId(surveyId);
                surveyAudit.setUserId(user.getId());
                surveyAudit.setCreateDate(new Date());
                surveyAudit.setTotal(total);
                surveyAudit.setSurveyName(survey.getName());
                surveyAudit.setOrgName(org.getName());

                orgQuestionManager.submitorgquestion(questions, surveyAudit);
            }
            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"保存失败");
    }
     @RequestMapping("updateorgquestion/{orgId}/{surveyId}")
    public JsonReaderSingleResponse updateorgquestion(@RequestBody List<OrgQuestionCommand> commands,@PathVariable Long orgId,@PathVariable Long surveyId,HttpServletRequest request){
        try{
            User user=userManager.getUserByUsername(request.getRemoteUser());
            List<OrgQuestion> questions=new ArrayList<OrgQuestion>();
            Double total=Double.valueOf(0);
            if(commands!=null && commands.size()>0){
                for(OrgQuestionCommand command:commands){
                    OrgQuestion question=new OrgQuestion();
                    question.setOrgId(orgId);
                    question.setSurveyId(surveyId);
                    question.setMark(command.getMark());
                    question.setCreateDate(new Date());
                    question.setQuestionId(command.getQuestionId());
                    question.setUserId(user.getId());
                    question.setSurveyName(command.getSurveyName());
                    question.setQuestionName(command.getQuestionName());
                    question.setQuestionRule(command.getQuestionRule());
                    question.setId(command.getId());
                    questions.add(question);
                    total+=Double.valueOf(question.getMark());
                }
            }
            if(questions.size()>0){
                Survey survey=surveyManager.get(surveyId);
                Org org=orgManager.get(orgId);
                OrgSurveyAudit surveyAudit=new OrgSurveyAudit();
                surveyAudit.setOrgId(orgId);
                surveyAudit.setSurveyId(surveyId);
                surveyAudit.setUserId(user.getId());
                surveyAudit.setCreateDate(new Date());
                surveyAudit.setTotal(total);
                surveyAudit.setSurveyName(survey.getName());
                surveyAudit.setOrgName(org.getName());
                orgQuestionManager.updateorgquestion(questions,surveyAudit);
            }

            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"保存失败");
    }
    @RequestMapping("rpjorgsurvey/{orgId}/{surveyId}")
    public ModelAndView listorgquestion(@PathVariable Long orgId,@PathVariable Long surveyId,HttpServletRequest request){
        SurveyCommand command=null;
        try{
            User user=userManager.getUserByUsername(request.getRemoteUser());
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("Q_userId_EQ",user.getId());
            map.put("Q_surveyId_EQ",surveyId);
            map.put("Q_orgId_EQ",orgId);
            Survey org=surveyManager.get(surveyId);
            command=new SurveyCommand(org);
            command.setOrgId(orgId);
            List<OrgQuestion> questions=orgQuestionManager.listAll(map);
            List<OrgQuestionCommand> orgQuestionCommands=new ArrayList<OrgQuestionCommand>();
            if(questions!=null && questions.size()>0){
                for(OrgQuestion question:questions){
                    OrgQuestionCommand questionCommand=new OrgQuestionCommand(question);
                    orgQuestionCommands.add(questionCommand);
                }
            }
            command.getOrgQuestions().clear();
            command.getOrgQuestions().addAll(orgQuestionCommands);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new ModelAndView("assess/rpjorgsurvey","command",command);
    }
    @RequestMapping("cx/{orgId}/{type}")
    public JsonReaderSingleResponse cx(@PathVariable Long orgId,@PathVariable String type){
        try{
            Org org=orgManager.get(orgId);
            if(org!=null){
                org.setIntegrity(type);
                orgManager.update(org);
            }
            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("list fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"请求错误");
    }
    @RequestMapping("addOrgSurvey/{orgId}")
    public ModelAndView addOrgSurvey(@PathVariable Long orgId,HttpServletRequest request){
        OrgCommand command=null;
        try{
            User user=userManager.getUserByUsername(request.getRemoteUser());
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("Q_userId_EQ",user.getId());
            map.put("Q_orgId_EQ",orgId);
            Org org=orgManager.get(orgId);
            command=new OrgCommand(org);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new ModelAndView("assess/addOrgSurvey","command",command);
    }








}

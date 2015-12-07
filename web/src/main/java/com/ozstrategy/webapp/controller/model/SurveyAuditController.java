package com.ozstrategy.webapp.controller.model;

import com.ozstrategy.model.SurveyAudit;
import com.ozstrategy.service.model.SurveyAuditManager;
import com.ozstrategy.webapp.command.JsonReaderResponse;
import com.ozstrategy.webapp.command.JsonReaderSingleResponse;
import com.ozstrategy.webapp.command.model.SurveyAuditCommand;
import com.ozstrategy.webapp.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* Created by lihao1 on 2015-11-18.
*/
@RestController
@RequestMapping("surveyAudit")
public class SurveyAuditController extends BaseController {
    @Autowired
    private SurveyAuditManager surveyAuditManager;
    @RequestMapping("list")
    public JsonReaderResponse<SurveyAuditCommand> list(HttpServletRequest request){
        List<SurveyAuditCommand> commands=new ArrayList<SurveyAuditCommand>();
            Map<String,Object> map=requestMap(request);
            try{
            List<SurveyAudit> models= surveyAuditManager.list(map,obtainStart(request),obtainLimit(request));
                if(models!=null && models.size()>0){
                    for(SurveyAudit model:models){
                        SurveyAuditCommand command=new SurveyAuditCommand(model);
                        commands.add(command);
                    }
                }
                Integer count=surveyAuditManager.count(map);
                return new JsonReaderResponse(commands,true,count,"");
            }catch (Exception e){
                logger.error("list fail",e);
            }
            return new JsonReaderResponse(commands,false,"请求错误");
    }
    @RequestMapping("save")
    public JsonReaderSingleResponse<SurveyAuditCommand> save(HttpServletRequest request){
        try{

            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"保存失败");
    }
    @RequestMapping("delete")
    public JsonReaderSingleResponse<SurveyAuditCommand> delete(HttpServletRequest request){
        try{

            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"删除失败");
    }
}

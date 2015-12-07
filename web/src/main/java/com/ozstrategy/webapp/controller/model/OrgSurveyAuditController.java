package com.ozstrategy.webapp.controller.model;

import com.ozstrategy.model.OrgSurveyAudit;
import com.ozstrategy.service.model.OrgSurveyAuditManager;
import com.ozstrategy.webapp.command.JsonReaderResponse;
import com.ozstrategy.webapp.command.JsonReaderSingleResponse;
import com.ozstrategy.webapp.command.model.OrgSurveyAuditCommand;
import com.ozstrategy.webapp.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* Created by lihao1 on 2015-11-19.
*/
@RestController
@RequestMapping("orgSurveyAudit")
public class OrgSurveyAuditController extends BaseController {
    @Autowired
    private OrgSurveyAuditManager orgSurveyAuditManager;
    @RequestMapping("list")
    public JsonReaderResponse<OrgSurveyAuditCommand> list(HttpServletRequest request){
        List<OrgSurveyAuditCommand> commands=new ArrayList<OrgSurveyAuditCommand>();
            Map<String,Object> map=requestMap(request);
            try{
            List<OrgSurveyAudit> models= orgSurveyAuditManager.list(map,obtainStart(request),obtainLimit(request));
                if(models!=null && models.size()>0){
                    for(OrgSurveyAudit model:models){
                        OrgSurveyAuditCommand command=new OrgSurveyAuditCommand(model);
                        commands.add(command);
                    }
                }
                Integer count=orgSurveyAuditManager.count(map);
                return new JsonReaderResponse(commands,true,count,"");
            }catch (Exception e){
                logger.error("list fail",e);
            }
            return new JsonReaderResponse(commands,false,"请求错误");
    }
    @RequestMapping("save")
    public JsonReaderSingleResponse<OrgSurveyAuditCommand> save(HttpServletRequest request){
        try{

            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"保存失败");
    }
    @RequestMapping("delete")
    public JsonReaderSingleResponse<OrgSurveyAuditCommand> delete(HttpServletRequest request){
        try{

            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"删除失败");
    }
}

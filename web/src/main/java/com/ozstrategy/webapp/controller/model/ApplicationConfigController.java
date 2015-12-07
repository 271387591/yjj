package com.ozstrategy.webapp.controller.model;

import com.ozstrategy.model.ApplicationConfig;
import com.ozstrategy.service.model.ApplicationConfigManager;
import com.ozstrategy.webapp.command.JsonReaderResponse;
import com.ozstrategy.webapp.command.JsonReaderSingleResponse;
import com.ozstrategy.webapp.command.model.ApplicationConfigCommand;
import com.ozstrategy.webapp.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* Created by lihao1 on 2015-10-16.
*/
@RestController
@RequestMapping("applicationConfig")
public class ApplicationConfigController extends BaseController {
    @Autowired
    private ApplicationConfigManager applicationConfigManager;
    @RequestMapping("list")
    public JsonReaderResponse<ApplicationConfigCommand> list(HttpServletRequest request){
        List<ApplicationConfigCommand> commands=new ArrayList<ApplicationConfigCommand>();
            Map<String,Object> map=requestMap(request);
            try{
            List<ApplicationConfig> models= applicationConfigManager.list(map,obtainStart(request),obtainLimit(request));
                if(models!=null && models.size()>0){
                    for(ApplicationConfig model:models){
                        ApplicationConfigCommand command=new ApplicationConfigCommand(model);
                        commands.add(command);
                    }
                }
                Integer count=applicationConfigManager.count(map);
                return new JsonReaderResponse(commands,true,count,"");
            }catch (Exception e){
                logger.error("list fail",e);
            }
            return new JsonReaderResponse(commands,false,"请求错误");
    }
    @RequestMapping("save")
    public JsonReaderSingleResponse<ApplicationConfigCommand> save(HttpServletRequest request){
        try{

            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"保存失败");
    }
    @RequestMapping("delete")
    public JsonReaderSingleResponse<ApplicationConfigCommand> delete(HttpServletRequest request){
        try{

            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"删除失败");
    }
}

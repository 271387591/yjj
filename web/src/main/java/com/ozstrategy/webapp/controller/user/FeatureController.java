package com.ozstrategy.webapp.controller.user;

import com.ozstrategy.service.user.FeatureManager;
import com.ozstrategy.webapp.command.JsonReaderResponse;
import com.ozstrategy.webapp.controller.BaseController;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Created by lihao1 on 2015-08-08.
*/
@RestController
@RequestMapping("feature")
public class FeatureController extends BaseController {
    @Autowired
    private FeatureManager featureManager;
    @RequestMapping("list")
    public JsonReaderResponse list(HttpServletRequest request){
        Map<String,Object> map=requestMap(request);
        try{
            List<Map<String,Object>> models= featureManager.listMap(new HashMap<String, Object>());
            if(models!=null && models.size()>0){
                for(Map<String,Object> objectMap:models){
                    Boolean hasC= BooleanUtils.toBoolean(objectMap.get("hasChild").toString(),"1","0");
                    if(hasC){
                        objectMap.put("open",true);
                    }
                }
            }
            Integer count=featureManager.count(map);
            return new JsonReaderResponse(models,true,count,"");
        }catch (Exception e){
            logger.error("list fail",e);
        }
        return new JsonReaderResponse(new ArrayList(),false,"请求错误");
    }
}

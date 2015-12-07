package com.ozstrategy.webapp.controller.dictionary;

import com.ozstrategy.model.dictionary.DictionaryType;
import com.ozstrategy.service.dictionary.DictionaryTypeManager;
import com.ozstrategy.webapp.command.JsonReaderResponse;
import com.ozstrategy.webapp.command.JsonReaderSingleResponse;
import com.ozstrategy.webapp.command.dictionary.DictionaryCommand;
import com.ozstrategy.webapp.command.dictionary.DictionaryTypeCommand;
import com.ozstrategy.webapp.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Created by lihao1 on 2015-08-10.
*/
@RestController
@RequestMapping("dictionaryType")
public class DictionaryTypeController extends BaseController {
    @Autowired
    private DictionaryTypeManager dictionaryTypeManager;
    @RequestMapping("index")
    public ModelAndView tables() {
        return new ModelAndView("dictionary/dicType");
    }
    @RequestMapping("add")
    public ModelAndView games() {
        DictionaryTypeCommand command=null;
        try{
            command=new DictionaryTypeCommand();
        }catch (Exception e){
            logger.error("addAdvert fail",e);
        }
        return new ModelAndView("dictionary/addType","command",command);
    }
    @RequestMapping("edit/{id}")
    public ModelAndView games(@PathVariable Long id) {
        DictionaryTypeCommand command=null;
        try{
            command=new DictionaryTypeCommand(dictionaryTypeManager.get(id));
        }catch (Exception e){
            logger.error("addAdvert fail",e);
        }
        return new ModelAndView("dictionary/addType","command",command);
    }
    @RequestMapping("list")
    public JsonReaderResponse list(HttpServletRequest request){
            Map<String,Object> map=requestMap(request);
            try{
            List<DictionaryType> models= dictionaryTypeManager.list(map,obtainStart(request),obtainLimit(request));

                Integer count=dictionaryTypeManager.count(map);
                return new JsonReaderResponse(models,true,count,"");
            }catch (Exception e){
                logger.error("list fail",e);
            }
            return new JsonReaderResponse(new ArrayList(),false,"请求错误");
    }
    @RequestMapping("save")
    public JsonReaderSingleResponse<DictionaryTypeCommand> save(@RequestBody DictionaryTypeCommand command){
        try{
            Long id=command.getId();
            DictionaryType dictionaryType=null;
            String dtype=command.getDtype();
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("Q_dtype_EQ",dtype);
            if(id==null){
                dictionaryType=dictionaryTypeManager.getByParam(map);
                if(dictionaryType!=null){
                    return new JsonReaderSingleResponse(null,false,"该分类已存在");
                }
                dictionaryType=new DictionaryType();
                dictionaryType.setCreateDate(new Date());
            }else{
                dictionaryType=dictionaryTypeManager.get(id);
                DictionaryType dictionaryType1=dictionaryTypeManager.getByParam(map);
                if(dictionaryType1!=null && dictionaryType1.getId()!=id){
                    return new JsonReaderSingleResponse(null,false,"该分类已存在");
                }
            }
            dictionaryType.setDtype(dtype);
            dictionaryTypeManager.saveOrUpdate(dictionaryType);
            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"保存失败");
    }
    @RequestMapping("delete/{id}")
    public JsonReaderSingleResponse<DictionaryTypeCommand> delete(@PathVariable Long id){
        try{
            dictionaryTypeManager.deleteType(id);
            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"删除失败");
    }
}

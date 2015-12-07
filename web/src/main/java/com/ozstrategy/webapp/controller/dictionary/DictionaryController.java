package com.ozstrategy.webapp.controller.dictionary;

import com.ozstrategy.model.dictionary.Dictionary;
import com.ozstrategy.service.dictionary.DictionaryManager;
import com.ozstrategy.webapp.command.JsonReaderResponse;
import com.ozstrategy.webapp.command.JsonReaderSingleResponse;
import com.ozstrategy.webapp.command.dictionary.DictionaryCommand;
import com.ozstrategy.webapp.command.user.UserCommand;
import com.ozstrategy.webapp.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
* Created by lihao1 on 2015-07-13.
*/
@RestController
@RequestMapping("dictionary")
public class DictionaryController extends BaseController {
    @Autowired
    private DictionaryManager dictionaryManager;
    @RequestMapping("index")
    public ModelAndView tables() {
        return new ModelAndView("dictionary/dic");
    }
    @RequestMapping("addDic")
    public ModelAndView games() {
        DictionaryCommand command=null;
        try{
            command=new DictionaryCommand();
        }catch (Exception e){
            logger.error("addAdvert fail",e);
        }
        return new ModelAndView("dictionary/addDic","command",command);
    }
    @RequestMapping("editDic/{id}")
    public ModelAndView games(@PathVariable Long id) {
        DictionaryCommand command=null;
        try{
            command=new DictionaryCommand(dictionaryManager.get(id));
        }catch (Exception e){
            logger.error("addAdvert fail",e);
        }
        return new ModelAndView("dictionary/addDic","command",command);
    }

    @RequestMapping("list")
    public JsonReaderResponse list(HttpServletRequest request){
        Map<String,Object> map=requestMap(request);
        try{
            map.put("Q_r.createDate","DESC");
            List<Map<String,Object>> models= dictionaryManager.findByNamedQuery("getDics",map,obtainStart(request),obtainLimit(request));
            Integer count=dictionaryManager.findByNamedQueryClass("getDicsCount",Integer.class,map);
            return new JsonReaderResponse(models,true,count,"");
        }catch (Exception e){
            logger.error("list fail",e);
        }
        return new JsonReaderResponse(new ArrayList(),false,"请求错误");
    }
    @RequestMapping("save")
    public JsonReaderSingleResponse<DictionaryCommand> save(@RequestBody DictionaryCommand command){
        try{
            Dictionary dictionary=null;
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("Q_typeId_EQ",command.getTypeId());
            map.put("Q_keyValue_EQ",command.getKeyValue());

            if(command.getId()==null){
                dictionary=dictionaryManager.getByParam(map);
                if(dictionary!=null){
                    return new JsonReaderSingleResponse(null,false,"该值已存在");
                }
                dictionary=new Dictionary();
                dictionary.setCreateDate(new Date());
            }else{
                dictionary=dictionaryManager.get(command.getId());
                Dictionary dictionary1=dictionaryManager.getByParam(map);
                if(dictionary1!=null&&dictionary.getId()!=dictionary1.getId()){
                    return new JsonReaderSingleResponse(null,false,"该值已存在");
                }
            }
            dictionary.setTypeId(command.getTypeId());
            dictionary.setKeyValue(command.getKeyValue());
            dictionaryManager.saveOrUpdate(dictionary);
            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"保存失败");
    }
    @RequestMapping("delete/{id}")
    public JsonReaderSingleResponse<DictionaryCommand> delete(@PathVariable Long id){
        try{
            dictionaryManager.deleteById(id);
            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"删除失败");
    }
}

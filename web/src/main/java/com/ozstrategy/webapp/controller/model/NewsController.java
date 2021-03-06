package com.ozstrategy.webapp.controller.model;

import com.ozstrategy.model.News;
import com.ozstrategy.model.dictionary.Dictionary;
import com.ozstrategy.model.user.User;
import com.ozstrategy.service.model.NewsManager;
import com.ozstrategy.webapp.command.JsonReaderResponse;
import com.ozstrategy.webapp.command.JsonReaderSingleResponse;
import com.ozstrategy.webapp.command.model.NewsCommand;
import com.ozstrategy.webapp.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
* Created by lihao1 on 2015-10-15.
*/
@RestController
@RequestMapping("news")
public class NewsController extends BaseController {
    @Autowired
    private NewsManager newsManager;
    @RequestMapping("index")
    public ModelAndView tables(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("news/news");
    }
    @RequestMapping("add")
    public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("news/addNews","command",new News());
    }
    @RequestMapping("edit/{id}")
    public ModelAndView editUser(@PathVariable Long id) {
        Map<String,Object> command=null;
        try{
            Map map=new HashMap();
            map.put("Q_id_EQ",id);
            command=newsManager.queryMap(map);
        }catch (Exception e){
            logger.error("edit fail",e);
        }
        return new ModelAndView("news/addNews","command",command);
    }
    @RequestMapping("list")
    public JsonReaderResponse<NewsCommand> list(HttpServletRequest request){
        Map<String,Object> map=requestMap(request);
        try{
            List<Map<String,Object>> models= newsManager.listPageMap(map, obtainStart(request), obtainLimit(request), "id", "publish", "createDate", "publishDate", "title", "idx", "pubUnit");
            Integer count=newsManager.count(map);
            return new JsonReaderResponse(models,true,count,"");
        }catch (Exception e){
            logger.error("list fail",e);
        }
        return new JsonReaderResponse(Collections.emptyList(),false,"请求错误");
    }
    @RequestMapping("save")
    public JsonReaderSingleResponse<NewsCommand> save(@RequestBody NewsCommand command){
        try{
            Long id=command.getId();
            News news=null;
            if(id==null){
                news=new News();
                news.setCreateDate(new Date());
            }else{
                news=newsManager.get(id);
            }

            news.setTitle(command.getTitle());
            news.setTitle(command.getTitle());
            String description=command.getContent();
            if(isNotEmpty(description)){
                description=description.replaceAll("\r|\n","");
            }
            news.setContent(description);
            news.setPubUnit(command.getPubUnit());
            news.setPublish(command.getPublish());
            if(command.getPublish()){
                news.setPublishDate(new Date());
            }
            newsManager.saveOrUpdate(news);
            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"保存失败");
    }
    @RequestMapping("delete/{id}")
    public JsonReaderSingleResponse<NewsCommand> delete(@PathVariable Long id){
        try{
            newsManager.deleteById(id);
            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"删除失败");
    }
    @RequestMapping("pub/{id}")
    public JsonReaderSingleResponse<NewsCommand> pub(@PathVariable Long id){
        Boolean ret=Boolean.FALSE;
        try{
            News news=newsManager.get(id);
            if(news.getPublish()){
                news.setPublish(false);
            }else{
                news.setPublish(true);
                ret=Boolean.TRUE;
            }
            news.setPublishDate(new Date());
            newsManager.update(news);
            return new JsonReaderSingleResponse(ret,true);
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(ret,false,"发布失败");
    }

}

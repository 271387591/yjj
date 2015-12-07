package com.ozstrategy.webapp.controller.model;

import com.ozstrategy.model.Advert;
import com.ozstrategy.model.FriendLink;
import com.ozstrategy.model.News;
import com.ozstrategy.service.model.FriendLinkManager;
import com.ozstrategy.webapp.command.JsonReaderResponse;
import com.ozstrategy.webapp.command.JsonReaderSingleResponse;
import com.ozstrategy.webapp.command.model.FriendLinkCommand;
import com.ozstrategy.webapp.command.model.NewsCommand;
import com.ozstrategy.webapp.controller.BaseController;
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
import java.util.List;
import java.util.Map;

/**
* Created by lihao1 on 2015-10-16.
*/
@RestController
@RequestMapping("friend")
public class FriendLinkController extends BaseController {
    @Autowired
    private FriendLinkManager friendLinkManager;
    @RequestMapping("index")
    public ModelAndView tables(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("friend/friend");
    }
    @RequestMapping("add")
    public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("friend/addFriend","command",new FriendLink());
    }
    @RequestMapping("edit/{id}")
    public ModelAndView editUser(@PathVariable Long id) {
        Map<String,Object> command=null;
        try{
            Map map=new HashMap();
            map.put("Q_id_EQ",id);
            command=friendLinkManager.queryMap(map);
        }catch (Exception e){
            logger.error("edit fail",e);
        }
        return new ModelAndView("friend/addFriend","command",command);
    }
    @RequestMapping("list")
    public JsonReaderResponse<FriendLinkCommand> list(HttpServletRequest request){
        Map<String,Object> map=requestMap(request);
        try{
            List<Map<String,Object>> models= friendLinkManager.listPageMap(map, obtainStart(request), obtainLimit(request));
            Integer count=friendLinkManager.count(map);
            return new JsonReaderResponse(models,true,count,"");
        }catch (Exception e){
            logger.error("list fail",e);
        }
        return new JsonReaderResponse(Collections.emptyList(),false,"请求错误");
    }
    @RequestMapping("save")
    public JsonReaderSingleResponse<FriendLinkCommand> save(@RequestBody FriendLinkCommand command){
        try{
            Long id=command.getId();
            FriendLink news=null;
            if(id==null){
                news=new FriendLink();
                news.setCreateDate(new Date());
            }else{
                news=friendLinkManager.get(id);
            }
            news.setOutUrl(command.getOutUrl());
            news.setName(command.getName());
            news.setPublish(command.getPublish());
            if(command.getPublish()){
                news.setPublishDate(new Date());
            }
            friendLinkManager.saveOrUpdate(news);
            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"保存失败");
    }
    @RequestMapping("delete/{id}")
    public JsonReaderSingleResponse<NewsCommand> delete(@PathVariable Long id){
        try{
            friendLinkManager.deleteById(id);
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
            FriendLink news=friendLinkManager.get(id);
            if(news.getPublish()){
                news.setPublish(false);
            }else{
                news.setPublish(true);
                ret=Boolean.TRUE;
            }
            news.setPublishDate(new Date());
            friendLinkManager.update(news);
            return new JsonReaderSingleResponse(ret,true);
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(ret,false,"发布失败");
    }
}

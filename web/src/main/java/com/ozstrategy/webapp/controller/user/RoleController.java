package com.ozstrategy.webapp.controller.user;

import com.ozstrategy.model.user.Role;
import com.ozstrategy.model.user.User;
import com.ozstrategy.service.user.RoleManager;
import com.ozstrategy.webapp.command.JsonReaderResponse;
import com.ozstrategy.webapp.command.JsonReaderSingleResponse;
import com.ozstrategy.webapp.command.user.RoleCommand;
import com.ozstrategy.webapp.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Created by lihao1 on 2015-06-08.
*/
@RestController
@RequestMapping("role")
public class RoleController extends BaseController {
    @Autowired
    private RoleManager roleManager;
    @RequestMapping("index")
    public ModelAndView tables(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("user/role");
    }
    @RequestMapping("addRole")
    public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("user/addRole","command",new RoleCommand());
    }
    @RequestMapping("editRole/{id}")
    public ModelAndView editUser(@PathVariable Long id) {
        Map<String,Object> command=null;
        try{
            command=roleManager.queryMap(id);
            Map<String,Object> params=new HashMap<String, Object>();
            params.put("roleId",command.get("id"));
            List<Map<String,Object>> features=roleManager.findByNamedQuery("getFeatures",params);
            command.put("features",features);
        }catch (Exception e){
            logger.error("addAdvert fail",e);
        }
        return new ModelAndView("user/addRole","command",command);
    }
    @RequestMapping("list")
    public JsonReaderResponse list(HttpServletRequest request){
        Map<String,Object> map=requestMap(request);
        try{
            List<Map<String,Object>> models=roleManager.listPageMap(map,obtainStart(request),obtainLimit(request));
            Integer count=roleManager.count(map);
            return new JsonReaderResponse(models,true,count,"");
        }catch (Exception e){
            logger.error("list fail",e);
        }
        return new JsonReaderResponse(new ArrayList(),false,"请求错误");
    }
    @RequestMapping("save")
    public JsonReaderSingleResponse<RoleCommand> save(@RequestBody RoleCommand command){
        try{
            Long id=command.getId();
            Role role=null;
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("Q_name_EQ",command.getName());
            if(id==null){
                role=roleManager.getByParam(map);
                if(role!=null){
                    return new JsonReaderSingleResponse(null,false,"该角色已存在");
                }
                role=new Role();
                role.setCreateDate(new Date());
            }else{
                role=roleManager.get(id);
                Role exRole=roleManager.getByParam(map);
                if(exRole!=null && exRole.getId()!=id){
                    return new JsonReaderSingleResponse(null,false,"该角色已存在");
                }
            }
            role.setName(command.getName());
            role.setDisplayName(command.getName());
            role.setDescription(command.getDescription());
            role.setLastUpdateDate(new Date());
            roleManager.saveRole(role,command.getFeatures());
            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("save fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"保存失败");
    }
    @RequestMapping("delete/{id}")
    public JsonReaderSingleResponse<RoleCommand> delete(@PathVariable Long id){
        try{

            return new JsonReaderSingleResponse(true);
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(null,false,"删除失败");
    }
}

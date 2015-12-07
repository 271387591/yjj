package com.ozstrategy.webapp.controller;

import com.ozstrategy.model.user.User;
import com.ozstrategy.service.user.UserManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SimpleMVController implements InitializingBean {

    private final transient Log log = LogFactory.getLog(this.getClass());
    @Autowired
    private UserManager userManager;

    public void afterPropertiesSet() throws Exception {
    }
    @RequestMapping("/home")
    public ModelAndView tables(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("home");
    }
    @RequestMapping("/menu")
    public ModelAndView menu(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("menu");
    }
    @RequestMapping("/ck")
    public ModelAndView ck(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("ck");
    }
    @RequestMapping("/ext3")
    public ModelAndView ext3(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("ext3");
    }

    @RequestMapping("/access.js")
    public ModelAndView access(HttpServletRequest request,HttpServletResponse response) {
        String username=request.getRemoteUser();
        Map<String,Object> objectMap=new HashMap<String, Object>();
        objectMap.put("Q_f.username_EQ",username);
        Map<String,Object> map=userManager.findByNamedQueryMap("getUsers",objectMap);
        if(map!=null){
            objectMap=new HashMap<String, Object>();
            objectMap.put("userId",map.get("id"));
            List<Map<String,Object>> features=userManager.findByNamedQuery("getFeatures",objectMap);
            map.put("features",features);
        }
        return new ModelAndView("res/desktopRes");
    }







}

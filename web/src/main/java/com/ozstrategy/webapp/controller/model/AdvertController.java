package com.ozstrategy.webapp.controller.model;

import com.ozstrategy.Constants;
import com.ozstrategy.model.Advert;
import com.ozstrategy.model.News;
import com.ozstrategy.model.user.User;
import com.ozstrategy.service.model.AdvertManager;
import com.ozstrategy.webapp.command.JsonReaderResponse;
import com.ozstrategy.webapp.command.JsonReaderSingleResponse;
import com.ozstrategy.webapp.command.model.AdvertCommand;
import com.ozstrategy.webapp.command.model.NewsCommand;
import com.ozstrategy.webapp.controller.BaseController;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
* Created by lihao1 on 2015-10-15.
*/
@RestController
@RequestMapping("advert")
public class AdvertController extends BaseController {
    @Autowired
    private AdvertManager advertManager;
    @RequestMapping("index")
    public ModelAndView tables(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("advert/advert");
    }
    @RequestMapping("add")
    public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("advert/addAdvert","command",new Advert());
    }
    @RequestMapping("edit/{id}")
    public ModelAndView editUser(@PathVariable Long id) {
        Map<String,Object> command=null;
        try{
            Map map=new HashMap();
            map.put("Q_id_EQ",id);
            command=advertManager.queryMap(map);
        }catch (Exception e){
            logger.error("edit fail",e);
        }
        return new ModelAndView("advert/addAdvert","command",command);
    }
    @RequestMapping("list")
    public JsonReaderResponse list(HttpServletRequest request){
        Map<String,Object> map=requestMap(request);
        try{
            List<Map<String,Object>> models= advertManager.listPageMap(map, obtainStart(request), obtainLimit(request), "id", "publish","createDate","publishDate","title","idx","pubUnit","picUrl","picName");
            Integer count=advertManager.count(map);
            return new JsonReaderResponse(models,true,count,"");
        }catch (Exception e){
            logger.error("list fail",e);
        }
        return new JsonReaderResponse(Collections.emptyList(),false,"请求错误");
    }
    @RequestMapping("upload")
    public ModelAndView save(HttpServletRequest request,HttpServletResponse response){
        String title        = request.getParameter("title");
        String pubUnit = request.getParameter("pubUnit");
        String publish = request.getParameter("publish");
        String outUrl = request.getParameter("outUrl");
        String id = request.getParameter("id");
        String description = request.getParameter("editorValue");
        if(isNotEmpty(description)){
            description=description.replaceAll("\r|\n","");
        }
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("X-Frame-Options", "SAMEORIGIN");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) { }

        if (writer == null) {
            return null;
        }
        Advert advert=null;
        if(StringUtils.isEmpty(id)){
            advert=new Advert();
            advert.setCreateDate(new Date());
        }else{
            advert=advertManager.get(parseLong(id));
        }
        advert.setContent(description);
        advert.setTitle(title);
        advert.setOutUrl(outUrl);
        advert.setPubUnit(pubUnit);
        advert.setPublish(BooleanUtils.toBoolean(publish));
        if(advert.getPublish()){
            advert.setPublishDate(new Date());
        }
        String attachDir = randomAbsolutePath(request, Constants.updloadAdvert);
        File fileOnServer = null;
        String logo1Name=null,logo1Url=null,logo1Path=null;
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator list             = multipartRequest.getFileNames();
            while (list.hasNext()) {
                String               controlName = list.next().toString();
                MultipartFile file        = multipartRequest.getFile(controlName);
                CommonsMultipartFile cmf         = (CommonsMultipartFile) file;
                DiskFileItem fileItem    = (DiskFileItem) cmf.getFileItem();

                if(StringUtils.equals(controlName,"pic")){
                    logo1Name    = fileItem.getName();
                    if(isEmpty(logo1Name)){
                        continue;
                    }
                    String str         = randomName(logo1Name);
                    fileOnServer      = new File(attachDir,str);
                    logo1Path=fileOnServer.getAbsolutePath();
                    logo1Url=Constants.updloadAdvert+"/"+str;
                }
                fileItem.write(fileOnServer);
            }
            if(StringUtils.isNotEmpty(logo1Name)){
                try{
                    FileUtils.forceDelete(new File(advert.getPicPath()));
                }catch (Exception e){
                }
                advert.setPicName(logo1Name);
                advert.setPicPath(logo1Path);
                advert.setPicUrl(logo1Url);
            }
            advertManager.saveOrUpdate(advert);
            writer.print("{\"success\":true,\"msg\":\"" + "" + "\"}");

        } catch (Exception e) {
            logger.error("upload sensor fail", e);

            String msg = "上传失败";
            writer.print("{\"success\":false,\"msg\":\"" + msg + "!\"}");
            e.printStackTrace();

            if (fileOnServer != null) {
                try {
                    FileUtils.forceDelete(fileOnServer);
                } catch (IOException e1) { }
            }

        }
        writer.close();
        return null;
    }
    @RequestMapping("delete/{id}")
    public JsonReaderSingleResponse delete(@PathVariable Long id){
        try{
            advertManager.deleteById(id);
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
            Advert news=advertManager.get(id);
            if(news.getPublish()){
                news.setPublish(false);
            }else{
                news.setPublish(true);
                ret=Boolean.TRUE;
            }
            news.setPublishDate(new Date());
            advertManager.update(news);
            return new JsonReaderSingleResponse(ret,true);
        }catch (Exception e){
            logger.error("delete fail",e);
        }
        return new JsonReaderSingleResponse(ret,false,"发布失败");
    }
}

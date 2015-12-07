package com.ozstrategy.webapp.controller;

import com.ozstrategy.Constants;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

/**
 * Created by lihao on 7/4/14.
 */
@Controller
public class BaseController {
    protected final transient Log logger = LogFactory.getLog(getClass());
    @Autowired
    private ApplicationContext context;
    protected static final List emptyData= Collections.EMPTY_LIST;
    private static String uploadPath=System.getProperty("upPath");
    
    public String getMessage(String key,HttpServletRequest request) {
        return context.getMessage(key, null, Locale.SIMPLIFIED_CHINESE);
    }
    public String getMessage(String key, Object[] args) {
        return context.getMessage(key, args, Locale.getDefault());
    }
    public String getMessage(String key, Locale locale) {
        return context.getMessage(key, null, locale);
    }
    public String getMessage(String key, Object[] args, Locale locale) {
        return context.getMessage(key, args, locale);
    }
    public String getZhMessage(String key) {
        return context.getMessage(key, null, Locale.SIMPLIFIED_CHINESE);
    }
    public String getZhMessage(String key,Object[] args) {
        return context.getMessage(key, args, Locale.SIMPLIFIED_CHINESE);
    }
    public Map<String,Object> requestMap(HttpServletRequest request){
        Map<String,Object> map=new HashMap<String, Object>();
        Enumeration enumeration = request.getParameterNames();
        do{
            if (!enumeration.hasMoreElements())
                break;
            String property = (String)enumeration.nextElement();
            if(StringUtils.isNotEmpty(property) && property.startsWith("Q_")){
                String value = request.getParameter(property);
                map.put(property,value);
            }
        } while (true);

        return map;
    }
     public Map<String,Object> requestNoPreMap(HttpServletRequest request){
        Map<String,Object> map=new HashMap<String, Object>();
        Enumeration enumeration = request.getParameterNames();
        do{
            if (!enumeration.hasMoreElements())
                break;
            String property = (String)enumeration.nextElement();
            if(StringUtils.isNotEmpty(property)){
                String value = request.getParameter(property);
                map.put(property,value);
            }
        } while (true);

        return map;
    }

    public Integer initLimit(String limit){
        if(NumberUtils.isNumber(limit)){
            return parseInteger(limit);
        }
        return  Constants.LIMIT;
    }
    public Boolean isEmpty(String filed){
        return StringUtils.isEmpty(filed);
    }
    public Boolean isNotEmpty(String filed){
        return StringUtils.isNotEmpty(filed);
    }

    public Boolean checkIsNotNumber(String filed){
        if(NumberUtils.isNumber(filed)){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
    public String formatDateYMD(Date date){
        return DateFormatUtils.format(date, Constants.YMD);
    }
    public String formatDateYMDHMS(Date date){
        return DateFormatUtils.format(date, Constants.YMDHMS);
    }
    public Date parseDate(String str){
        try {
            Date date = DateUtils.parseDate(str, new String[]{Constants.YMD, Constants.YMDHMS});
            return date;
        } catch (Exception e) {
        }
        return null;
    }
    public Long parseLong(String filed){
        if(NumberUtils.isNumber(filed))
            return NumberUtils.toLong(filed);
        return null;
    }
    public Integer parseInteger(String filed){
        if(NumberUtils.isNumber(filed))
            return Integer.parseInt(filed);
        return null;
    }
    public Short parseShort(String filed){
        if(NumberUtils.isNumber(filed))
            return Short.parseShort(filed);
        return null;
    }
    public Double parseDouble(String filed){
        if(NumberUtils.isNumber(filed))
            return Double.parseDouble(filed);
        return null;
    }
    public Boolean parseBoolean(String filed){
        if(StringUtils.isNotEmpty(filed)){
            return BooleanUtils.toBooleanObject(filed);
        }
        return null;
    }

    
    public void ajax(String str,boolean result,HttpServletResponse response){
        try{
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("{\"communicate\":"+str+",\"result\":\""+result+"\"}");
        }catch(IOException e){
            logger.error(e.getMessage(),e);
        }
    }
    public String obtain(HttpServletRequest request,String params){
        return request.getParameter(params);
    }
    public Integer obtainStart(HttpServletRequest request){
        String start=request.getParameter("start");
        if(NumberUtils.isNumber(start)){
            return Integer.parseInt(start);
        }
        return 0;
    }
    public Integer obtainLimit(HttpServletRequest request){
        String limit=request.getParameter("limit");
        if(NumberUtils.isNumber(limit)){
            return parseInteger(limit);
        }
        return  Constants.LIMIT;
    }
    public String toHttpUrl(HttpServletRequest request,boolean hasPort){
        String host=request.getServerName();
        String contextPath=request.getContextPath();
//        int port = request.getServerPort();
//        if(hasPort){
//            return  "http://"+host+":"+port+contextPath+"/";
//        }
        return  "http://"+host+contextPath+"/";

    }
    public String randomAbsolutePath(HttpServletRequest request,String dir){
        String attachDir=null;
        if(isEmpty(uploadPath)){
            attachDir = request.getSession().getServletContext().getRealPath("/") + File.separator + dir + File.separator;
        }else{
            attachDir = uploadPath + File.separator + dir + File.separator;
        }
        attachDir = FilenameUtils.normalize(attachDir);
        File fileDir = new File(attachDir);
        if (fileDir.exists() == false) {
            fileDir.mkdirs();
        }
        return attachDir;
    }
    public String randomName(String fileName) {
        Random random = new Random();
        return "" + random.nextInt(10000)+ System.currentTimeMillis() + getExtension(fileName);
    }
    public String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
}

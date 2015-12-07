package com.ozstrategy.webapp.controller.ue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ozstrategy.Constants;
import com.ozstrategy.webapp.controller.BaseController;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by lihao1 on 7/15/15.
 */
@RestController
@RequestMapping(value = "ue")
public class UeditorController extends BaseController {
    private final static String imgUrl=System.getProperty("img.url");

    String[] picFileType = {".gif" , ".png" , ".jpg" , ".jpeg" , ".bmp"};
    @RequestMapping(value = "security/upload")
    public ModelAndView upload(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("X-Frame-Options", "SAMEORIGIN");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) { }

        if (writer == null) {
            return null;
        }

        String attachDir = randomAbsolutePath(request, Constants.updloadUe);
        File fileOnServer = null;

        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator list             = multipartRequest.getFileNames();
            while (list.hasNext()) {
                String               controlName = list.next().toString();
                MultipartFile file        = multipartRequest.getFile(controlName);
                CommonsMultipartFile cmf         = (CommonsMultipartFile) file;
                DiskFileItem fileItem    = (DiskFileItem) cmf.getFileItem();
                String fileName=null,str=null,ext=null;
                fileName    = fileItem.getName();
                if(isEmpty(fileName)){
                    continue;
                }
                str         = randomName(fileName);
                ext=getExtension(fileName);
                if(Arrays.binarySearch(picFileType, ext)==-1){
                    continue;
                }
                fileOnServer      = new File(attachDir,str);
                fileItem.write(fileOnServer);

                String httpPath= Constants.updloadUe+"/"+str;
                String callback = request.getParameter("callback");
                String result = "{\"name\":\""+ fileOnServer.getName() +"\", \"originalName\": \""+ fileItem.getName() +"\", \"size\": "+ fileItem.getSize() +", \"state\": \"SUCCESS\", \"type\": \""+ ext +"\", \"url\": \""+ httpPath +"\"}";
                result = result.replaceAll( "\\\\", "\\\\" );
                if( callback == null ){
                    writer.print( result);
                }else{
                    writer.print("<script>" + callback + "(" + result + ")</script>");
                }
            }

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
        return null;
    }
    @RequestMapping(value = "security/ueditor")
    public ModelAndView ueditor(HttpServletRequest request,HttpServletResponse response){
        PrintWriter writer=null;
        try{
            request.setCharacterEncoding("utf-8");
            response.setHeader("Content-Type" , "text/html");
            response.setHeader("X-Frame-Options", "SAMEORIGIN");
            writer = response.getWriter();
            String action = request.getParameter("action");
            if(StringUtils.equals("uploadimage",action)){
                try {
                    String attachDir = randomAbsolutePath(request, Constants.updloadUe);
                    MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
                    Iterator list             = multipartRequest.getFileNames();
                    while (list.hasNext()) {
                        String               controlName = list.next().toString();
                        MultipartFile file        = multipartRequest.getFile(controlName);
                        CommonsMultipartFile cmf         = (CommonsMultipartFile) file;
                        DiskFileItem fileItem    = (DiskFileItem) cmf.getFileItem();
                        if(fileItem!=null && fileItem.getSize()>2*1024*1024){
                            writer.print("<script>alert('文件不能大于2M')</script>");
                            return null;
                        }
                        String fileName=null,str=null,ext=null;
                        fileName    = fileItem.getName();
                        if(isEmpty(fileName)){
                            continue;
                        }
                        str         = randomName(fileName);
                        ext=getExtension(fileName);
                        if(Arrays.binarySearch(picFileType, ext)==-1){
                            continue;
                        }
                        File fileOnServer      = new File(attachDir,str);
                        fileItem.write(fileOnServer);

                        String httpPath= Constants.updloadUe+"/"+str;
                        String callback = request.getParameter("callback");
                        String result = "{\"name\":\""+ fileOnServer.getName() +"\", \"original\": \""+ fileItem.getName() +"\", \"size\": "+ fileItem.getSize() +", \"state\": \"SUCCESS\", \"type\": \""+ ext +"\", \"url\": \""+ httpPath +"\"}";
                        result = result.replaceAll( "\\\\", "\\\\" );
                        if( callback == null ){
                            writer.print( result);
                        }else{
                            writer.print("<script>" + callback + "(" + result + ")</script>");
                        }
                    }

                } catch (Exception e) {
                    logger.error("upload sensor fail", e);
                    String msg = "上传失败";
                    writer.print("{\"success\":false,\"msg\":\"" + msg + "!\"}");
                    e.printStackTrace();

                }
            }else if(StringUtils.equals("config",action)){
                ObjectMapper objectMapper=new ObjectMapper();
                ObjectNode node = objectMapper.createObjectNode();
                node.put("imageActionName","uploadimage");
                node.put("upfile","upfile");
                node.put("imageMaxSize",2048000);
                ArrayNode arrayNode= objectMapper.createArrayNode();
                arrayNode.add(".png").add(".jpg").add(".jpeg").add(".gif").add(".bmp");
                node.put("imageAllowFiles",arrayNode);
                node.put("imageCompressEnable",true);
                node.put("imageCompressBorder",1600);
                node.put("imageInsertAlign","none");

                node.put("imageUrlPrefix",imgUrl);
                node.put("imagePathFormat","/updload/ue/{yyyy}{mm}{dd}{time}{rand:6}");

                String configContent=node.toString();

//                InputStream inputStream=UeditorController.class.getResourceAsStream("/config.json");
//                String configContent=readFile(inputStream);
                writer.write(configContent);
            }

        }catch (Exception e){
            if(writer!=null){
                writer.close();
            }
        }
        return null;
    }
    private String readFile(InputStream inputStream) throws IOException {
        StringBuilder builder = new StringBuilder();

        try {
            InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bfReader = new BufferedReader(reader);
            String tmpContent = null;

            while((tmpContent = bfReader.readLine()) != null) {
                builder.append(tmpContent);
            }

            bfReader.close();
        } catch (UnsupportedEncodingException var6) {
            ;
        }

        return this.filter(builder.toString());
    }
    private String filter(String input) {
        return input.replaceAll("/\\*[\\s\\S]*?\\*/", "");
    }
}

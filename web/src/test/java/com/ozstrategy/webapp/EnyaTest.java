package com.ozstrategy.webapp;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.ozstrategy.util.Base64Utils;
import org.apache.commons.io.IOUtils;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lihao on 12/30/14.
 */
public class EnyaTest {
    private String baseUrl="http://120.24.228.68/Tenant/html/app/";
//    private String baseUrl="http://120.24.228.68:8080/Enya/app/";
    /***
     * 密码正则 :6-16位字母或数字
     */
    @Test
    public void testPassword(){
        Pattern pattern=Pattern.compile("^[0-9a-zA-Z]{6,16}$");
        Matcher matcher = pattern.matcher("sdfdsfsf");
        System.out.println(matcher.matches());

    }


    /***
     * 邮件正则
     */
    @Test
    public void testEmail(){
        Pattern pattern=Pattern.compile("^((([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6}\\;))*(([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})))$");
        Matcher matcher = pattern.matcher("sdfdsfsf");
        System.out.println(matcher.matches());
    }
    /***
     * 用户名：3-16位字母或数字或下划线
     */
    @Test
    public void testUsername(){
        Pattern pattern=Pattern.compile("^[a-zA-Z0-9_]{3,16}$");
        Matcher matcher = pattern.matcher("sdfdsfsf");
        System.out.println(matcher.matches());
    }


    /**
     * 接口连接：http://121.42.153.185:8080/Alizee
     *
     * 后台登录地址：http://121.42.153.185:8080/Alizee/login
     * 用户名：admin密码：tomcat
     * 广告和美食故事，登录后台自己添加和修改
     *
     */


//    -----------------用户模块--------------------------------------------------
    /**
     * 注册
     * 接口参数：username,password,email,nickName(可选)
     * 请求方式：POST
     *
     * @throws Exception
     */
    @Test
    public void testRegister() throws Exception{
        String url=baseUrl+"register";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("username", "lihao1"));
        nvps.add(new BasicNameValuePair("password", "tomcat"));
        nvps.add(new BasicNameValuePair("nickName", "李浩"));
        nvps.add(new BasicNameValuePair("email", "639143@qq.com"));
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();
        String body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();
    }
    /**
     * 登录
     * 接口参数：username(用户名或邮件),password
     * 请求方式：POST
     *
     * @throws Exception
     */
    @Test
    public void testLogin() throws Exception{
        String url=baseUrl+"login";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("username", "admin"));
//        nvps.add(new BasicNameValuePair("username", "1333@qq.com"));
        nvps.add(new BasicNameValuePair("password", "tomcat"));
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();
        //这里获取cookie
        String coockie = response.getFirstHeader("Set-Cookie").getValue();
        coockie=coockie.substring(0,coockie.indexOf(";"));
        System.out.println("coockie==="+coockie);
        String charset = EntityUtils.getContentCharSet(entity);

        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();
        
    }
    /**
     * 修改个人资料，所有参数可选
     * 登录cookie
     *nickName,昵称，不做重复判断，昵称是可以重名的如果昵称为空，你前端直接显示“游客”
     * gender，性别，取值：M,表示男，F，表示女，
     * birth，生日，格式：2015-04-23，
     * province，省
     * city，市
     * country，县
     * address，县以下的地区
     * postalCode，邮编，
     *
     * 请求方式：POST/get
     *
     * @throws Exception
     */
    @Test
    public void testUpdateUser() throws Exception{
        String url=baseUrl+"updateUser";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("gender", "F"));
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        httpost.addHeader("Cookie", "JSESSIONID=16tjer2b2wujo");
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();

        String charset = EntityUtils.getContentCharSet(entity);

        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();

    }
    /**
     * 上传头像
     * 登录cookie
     * 请求方式：POST
     * @throws Exception
     */
    @Test
    public void testPortrait() throws Exception{
//        String url=baseUrl+"portrait";
//        HttpPost httpost = new HttpPost(url);
//        FileBody bin = new FileBody(new File("/Users/lihao1/Pictures/871B841438B9CA8E34710197EB41E296.png"));
//        StringBody comment = new StringBody("871B841438B9CA8E34710197EB41E296.png");//文件名
//        MultipartEntity reqEntity = new MultipartEntity();
//        reqEntity.addPart("file", bin);
//        reqEntity.addPart("filename", comment);//filename为请求后台的普通参数;属性
//        httpost.setEntity(reqEntity);
//        httpost.addHeader("Cookie", "JSESSIONID=1no7lwgjxxe0g");
//        DefaultHttpClient httpclient = new DefaultHttpClient();
//        HttpResponse response = null;
//        response = httpclient.execute(httpost);
//        HttpEntity entity = response.getEntity();
//        String charset = EntityUtils.getContentCharSet(entity);
//        String body = null;
//        body = EntityUtils.toString(entity);
//        System.out.println(body);
//        httpclient.getConnectionManager().shutdown();



        String url=baseUrl+"portrait";
        HttpPost httpost = new HttpPost(url);
        File file=new File("/Users/lihao1/Pictures/C4FAC095C2F109943D07A5D36C16DDF2.png");
        FileInputStream fileInputStream=new FileInputStream(file);
        byte[] bytes = IOUtils.toByteArray(fileInputStream);
        //转成base64,请限制用户文件不能大于200K，格式为png/jpg/jpeg/bmp
        String str= Base64Utils.encode(bytes);

        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("file", str));
        nvps.add(new BasicNameValuePair("fileName", "C4FAC095C2F109943D07A5D36C16DDF2.png"));
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        httpost.addHeader("Cookie", "JSESSIONID=lbod1yahjg3n");

        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();
        String charset = EntityUtils.getContentCharSet(entity);
        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();




    }
    //-------------展馆相关-------------------------------------------------------------------------------------------

    /**
     * 获取展馆列表，获取热门展馆
     * Q_t.hot_EQ:是否为热门，1表示是，0表示否，如果不传递该参数则获取全部,（注意这里的命名）
     * start:数据起始量，比如：从第0条数据开始，start=0,从第34条数据开始：start=34 （必须，并且为数字）
     * limit:每次获取的数据量,默认每次25条，（可以不传，默认25条）
     *
     * 参数示例：比如每页显示30条数据，参数传递为：
     * 第一页：start=0&limit=30
     * 第二页：start=31&limit=30
     * 第三页：start=61&limit=30
     * .......
     *
     * 根据客户端需要获取几条，列表会返回所有基础信息，点击详情无需再访问网路
     *
     * 请求方式：POST
     *
     * @throws Exception
     */
    @Test
    public void testGetHalls() throws Exception{
        String url=baseUrl+"getHalls";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        nvps.add(new BasicNameValuePair("Q_t.hot_EQ", "0"));
        nvps.add(new BasicNameValuePair("start", "0"));
        nvps.add(new BasicNameValuePair("limit", "2"));
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();

        String charset = EntityUtils.getContentCharSet(entity);

        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();
    }
    /**
     * 获取单个展馆
     * id,注意这里的参数，采用rest风格
     */
    @Test
    public void testGetHall() throws Exception{
        Long id=13l;
        String url=baseUrl+"getHall/"+id;//getHall/3
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();
        String charset = EntityUtils.getContentCharSet(entity);
        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();
    }

    //-------------------------展会相关-----------------------------------------------------------------------------

    /**
     * 获取展会列表
     * 排序方式参数：
     * Q_exh.createDate:取值：DESC降序，ASC升序.时间排序
     * Q_h.name:取值：DESC降序，ASC升序，展馆排序
     * Q_exh.tradeNames:取值：DESC降序，ASC升序，行业排序
     *
     * 这三种排序每次请求只保证传一个，三个参数都可选。如果都不传默认按时间降序排列
     * ***************
     * 搜索功能参数
     * Q_exh.name_LK:根据展会名称模糊查询，目前暂定只更加名称查询，（可选参数，用户搜索时用）
     *
     *
     *
     *
     * 在首页的广告区，可以调用该接口，使用时间降序，limit=3就是取最近3条，具体条数暂定3条
     *
     * start:数据起始量，比如：从第0条数据开始，start=0,从第34条数据开始：start=34 （必须，并且为数字）
     * limit:每次获取的数据量,默认每次25条，（可以不传，默认25条）
     *
     * 参数示例：比如每页显示30条数据，参数传递为：
     * 第一页：start=0&limit=30
     * 第二页：start=31&limit=30
     * 第三页：start=61&limit=30
     * .......
     *
     * 根据客户端需要获取几条，列表会返回所有基础信息，点击详情无需再访问网路
     *
     * 请求方式：POST
     *
     * @throws Exception
     */
    @Test
    public void testGetExhs() throws Exception{
        String url=baseUrl+"getExhs";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        nvps.add(new BasicNameValuePair("Q_h.name", "DESC"));
//        nvps.add(new BasicNameValuePair("Q_exh.createDate", "ASC"));
//        nvps.add(new BasicNameValuePair("Q_exh.tradeNames", "DESC"));
//        nvps.add(new BasicNameValuePair("Q_exh.name_LK", "水水"));
        nvps.add(new BasicNameValuePair("start", "0"));
        nvps.add(new BasicNameValuePair("limit", "5"));
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();

        String charset = EntityUtils.getContentCharSet(entity);

        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();
    }
    /**
     * 获取单个展会
     * id,注意这里的参数，采用rest风格,参照获取单个展馆
     */
    @Test
    public void testGetExh() throws Exception{
        Long id=5l;
        String url=baseUrl+"getExh/"+id;//getExh/3
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();
        String charset = EntityUtils.getContentCharSet(entity);
        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();
    }
    /**
     * 获取评论列表
     * type，表示评论的分类，目前只有展会，后续如有增加再定，分类取值：展会：Exhibition
     * itemId,展会的ID，
     * * start:数据起始量，比如：从第0条数据开始，start=0,从第34条数据开始：start=34 （必须，并且为数字）
     * limit:每次获取的数据量,默认每次25条，（可以不传，默认25条）
     *
     * 参数示例：比如每页显示30条数据，参数传递为：
     * 第一页：start=0&limit=30
     * 第二页：start=31&limit=30
     * 第三页：start=61&limit=30
     * .......
     *
     * 根据客户端需要获取几条，
     *
     *
     * 请求方式：POST
     *
     * @throws Exception
     */
    @Test
    public void testGetComments() throws Exception{
        String url=baseUrl+"getComments";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("type", "Exhibition"));
        nvps.add(new BasicNameValuePair("itemId", "8"));
        nvps.add(new BasicNameValuePair("start", "0"));
        nvps.add(new BasicNameValuePair("limit", "2"));
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();

        String charset = EntityUtils.getContentCharSet(entity);

        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();

    }
    /**
     * 通用评论接口
     * 登录时的cookie
     * type，表示评论的分类，目前只有展会，后续如有增加再定，分类取值：展会：Exhibition
     * itemId,评论的记录ID，这里传该展会的ID
     * rank,评价等级，由客户端自己设计，取值为正数，例如：1、2、3、4、5个等级，（这个暂时可以不传）
     * content，评价内容
     * 请求方式：POST
     *如果statusCode返回为302，表示需要用户登录
     * @throws Exception
     */
    @Test
    public void testComment() throws Exception{
        String url=baseUrl+"comment";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();

        nvps.add(new BasicNameValuePair("type", "Exhibition"));
        nvps.add(new BasicNameValuePair("itemId", "8"));
        nvps.add(new BasicNameValuePair("rank", "1"));
        nvps.add(new BasicNameValuePair("content", "1111111eewew111"));
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        //这里添加cookie，如何获取cookie请查看登录接口注释
        httpost.addHeader("Cookie", "JSESSIONID=184wf0zt02msq");
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("statusCode===="+statusCode);
        HttpEntity entity = response.getEntity();

        String charset = EntityUtils.getContentCharSet(entity);

        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();
    }
    /**
     * 获取参展指南
     * 需要该展会的ID
     */
    @Test
    public void testGetExhGuide() throws Exception{
        Long exh=7l;
        String url=baseUrl+"getExhGuide/"+exh;//getExhGuide/3
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();
        String charset = EntityUtils.getContentCharSet(entity);
        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();
    }
    /**
     * 获取出行指南
     * 需要该展会的ID
     */
    @Test
    public void testGetExhGuideTo() throws Exception{
        Long exh=7l;
        String url=baseUrl+"getExhGuideTo/"+exh;//getExhGuideTo/3
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();
        String charset = EntityUtils.getContentCharSet(entity);
        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();
    }
    /**
     * 获取观展攻略
     * 需要该展会的ID
     */
    @Test
    public void testGetExhTravel() throws Exception{
        Long exh=7l;
        String url=baseUrl+"getExhTravel/"+exh;//getExhGuideTo/3
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();
        String charset = EntityUtils.getContentCharSet(entity);
        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();
    }
    /**
     * 获取展会介绍
     * 需要该展会的ID
     */
    @Test
    public void testGetExhDescription() throws Exception{
        Long exh=7l;
        String url=baseUrl+"getExhDescription/"+exh;//getExhGuideTo/3
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();
        String charset = EntityUtils.getContentCharSet(entity);
        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();
    }
    /**
     * 获取主办信息
     * 需要该展会的ID
     */
    @Test
    public void testGetExhSponsor() throws Exception{
        Long exh=7l;
        String url=baseUrl+"getExhSponsor/"+exh;//getExhGuideTo/3
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();
        String charset = EntityUtils.getContentCharSet(entity);
        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();
    }
    /**
     * 获取展馆介绍，这里可以调用获取单个展馆信息接口，需要传入展馆的ID，展馆ID在展会列表里会返回，不是展会的ID
     */
    @Test
    public void testGetHallDescription() throws Exception{

    }
    /**
     * 获取展会资讯列表
     * Q_exhId_EQ:展会ID
     * * start:数据起始量，比如：从第0条数据开始，start=0,从第34条数据开始：start=34 （必须，并且为数字）
     * limit:每次获取的数据量,默认每次25条，（可以不传，默认25条）
     *
     * 参数示例：比如每页显示30条数据，参数传递为：
     * 第一页：start=0&limit=30
     * 第二页：start=31&limit=30
     * 第三页：start=61&limit=30
     * .......
     *
     * 根据客户端需要获取几条，
     *
     *
     * 请求方式：POST
     *
     * @throws Exception
     */
    @Test
    public void testGetExhNews() throws Exception{
        String url=baseUrl+"getExhNews";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("Q_exhId_EQ", "7"));
        nvps.add(new BasicNameValuePair("start", "0"));
        nvps.add(new BasicNameValuePair("limit", "2"));
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();

        String charset = EntityUtils.getContentCharSet(entity);

        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();
    }
    /**
     * 获取行业资讯列表
     * exhId:展会ID
     * * start:数据起始量，比如：从第0条数据开始，start=0,从第34条数据开始：start=34 （必须，并且为数字）
     * limit:每次获取的数据量,默认每次25条，（可以不传，默认25条）
     *
     * 参数示例：比如每页显示30条数据，参数传递为：
     * 第一页：start=0&limit=30
     * 第二页：start=31&limit=30
     * 第三页：start=61&limit=30
     * .......
     *
     * 根据客户端需要获取几条，
     *
     *
     * 请求方式：POST
     *
     * @throws Exception
     */
    @Test
    public void testGetExhTrade() throws Exception{
        String url=baseUrl+"getExhTrade";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("exhId", "7"));
        nvps.add(new BasicNameValuePair("start", "0"));
        nvps.add(new BasicNameValuePair("limit", "2"));
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();

        String charset = EntityUtils.getContentCharSet(entity);

        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();
    } /**
     * 版本升级
     * type:APP类型，取值为：大众版：Common，管理人员版本：Admin
     * 返回的version。你自己设定对比规则，version 的格式由客户端自定义,pacUrl为下载地址
     *
     * 请求方式：POST
     *
     * @throws Exception
     */
    @Test
    public void testGetLastVersion() throws Exception{
        String url=baseUrl+"getLastVersion/Common";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//        nvps.add(new BasicNameValuePair("type", "Common"));
        httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        response = httpclient.execute(httpost);
        HttpEntity entity = response.getEntity();

        String charset = EntityUtils.getContentCharSet(entity);

        String body = null;
        body = EntityUtils.toString(entity);
        System.out.println(body);
        httpclient.getConnectionManager().shutdown();
    }

}

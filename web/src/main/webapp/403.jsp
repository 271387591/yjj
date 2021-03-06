<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
    <title>很抱歉，该资源不允许访问！</title>
    <meta http-equiv="X-UA-Compatible" content="IE=9;Chrome=1" />
    <style type="text/css">
        body {margin: 0px; padding:0px; font-family:"微软雅黑", Arial, "Trebuchet MS", Verdana, Georgia,Baskerville,Palatino,Times; font-size:16px;}
        div{margin-left:auto; margin-right:auto;}
        a {text-decoration: none; color: #1064A0;}
        a:hover {color: #0078D2;}
        img { border:none; }
        h1,h2,h3,h4 {
            /*	display:block;*/
            margin:0;
            font-weight:normal;
            font-family: "微软雅黑", Arial, "Trebuchet MS", Helvetica, Verdana ;
        }
        h1{font-size:44px; color:#0188DE; padding:20px 0px 10px 0px;}
        h2{color:#0188DE; font-size:16px; padding:10px 0px 40px 0px;}

        #page{width:910px; padding:20px 20px 40px 20px; margin-top:80px;}
        .button{width:180px; height:28px; margin-left:0px; margin-top:10px; background:#009CFF; border-bottom:4px solid #0188DE; text-align:center;}
        .button a{width:180px; height:28px; display:block; font-size:14px; color:#fff; }
        .button a:hover{ background:#5BBFFF;}
    </style>
</head>

<body>
<div id="page" style="border-style:dashed;border-color:#e4e4e4;line-height:30px;background:url(sorry.png) no-repeat right;">
    <h1>抱歉，该资源不允许访问，或者你没有访问的权限~</h1>
    <font color="#666666">我们建议你返回首页官网进行浏览，谢谢！</font><br /><br />
    <div class="button">
        <a href="<c:url value="/login.jsp"/>" title="返回登录">返回登录</a>
    </div>
</div>
</body>
</html>

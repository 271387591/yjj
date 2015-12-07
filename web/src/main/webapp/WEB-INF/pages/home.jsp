<%--
  Created by IntelliJ IDEA.
  User: lihao1
  Date: 7/31/15
  Time: 19:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=9;Chrome=1" />
    <title></title>
    <%@include file="/common/common.jsp"%>
    <%
        String gloableImgUrl=System.getProperty("img.url");
        session.setAttribute("imgUrl",gloableImgUrl);
    %>
    <style>
        .tdwidth-100:{
            width: 100px;
        }
    </style>
</head>
<body>
<div id="wrapper">
    <%@include file="menu.jsp"%>
    <div id="page-wrapper" class="gray-bg dashbard-1">
       <%@include file="header.jsp"%>
        <div id="content-wrapper">

        </div>
        <%@include file="footer.jsp"%>
    </div>
</div>
<script type="text/javascript">

    $(window).load(function(event){
        var hash = window.location.hash;
        if(!hash)return;
        ajaxReloadPage("content-wrapper","html/"+hash.substr(1));
        if($('ul[id=side-menu] li a[href="'+hash+'"]').size()>0){
            $('ul[id=side-menu] li').removeClass('active');
            $('ul[id=side-menu] li a[href="'+hash+'"]').parents('li').last().addClass('active');
        }
//        $('ul[id=side-menu] li').removeClass('active');
//        $('ul[id=side-menu] li a[href="'+hash+'"]').parents('li').last().addClass('active');

    });
    $(window).bind('hashchange',function(){
        var hash = window.location.hash;
        if(!hash)return;
        ajaxReloadPage("content-wrapper","html/"+hash.substr(1));
        if($('ul[id=side-menu] li a[href="'+hash+'"]').size()>0){
            $('ul[id=side-menu] li').removeClass('active');
            $('ul[id=side-menu] li a[href="'+hash+'"]').parents('li').last().addClass('active');
        }
    });

</script>
</body>
</html>

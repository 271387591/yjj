<%--
  Created by IntelliJ IDEA.
  User: lihao1
  Date: 6/4/15
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="bjMod">
    <div class="foot">
        <div class="foot_t">
            <div class="foot_tl"></div>
            <div class="foot_c" id="newsListDiv">
                <script id="newsListTpl" type="text/template">
                    <span>友情链接</span>
                    {{ if(it.data && it.data.length>0) { }}
                    {{ for (var i = 0, l = it.data.length; i < l; i++) { }}
                    <a href="{{=it.data[i].outUrl}}">{{=it.data[i].name}}</a><font>|</font>
                    {{ } }}
                    {{ } }}
                </script>

            </div>
            <div class="foot_tr"></div>
        </div>
        <div class="foot_d">
            <p>蜀ICP备12013692号</p>
            <p>Copyright 2015 版权所有 www.ljxfda.gov.cn All Rights Reserved, Power By </p>
        </div>
    </div>
</div>
<script>
    $.post(appPath+'html/web/listFried',function(result){
        var tmpl = document.getElementById('newsListTpl').innerHTML;
        var doTtmpl = doT.template(tmpl);
        $('#newsListDiv').html(doTtmpl(result));
    });
</script>

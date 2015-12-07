<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="taglibs.jsp"%>

<%
    String imgUrl=System.getProperty("img.url");
    request.getSession().setAttribute("imgUrl",imgUrl);
%>
<link href="<c:url value="/resources/web/zj.css"/> " rel="stylesheet" />
<script type="text/javascript">
    var appPath='<c:url value="/"/>';
    var imgUrl='${imgUrl}';
    Date.prototype.format = function(format){
        var d=this;
        var o = {
            "M+" : d.getMonth()+1, //month
            "d+" : d.getDate(), //day
            "h+" : d.getHours(), //hour
            "m+" : d.getMinutes(), //minute
            "s+" : d.getSeconds(), //second
            "q+" : Math.floor((d.getMonth()+3)/3), //quarter
            "S" : d.getMilliseconds() //millisecond
        }

        if(/(y+)/.test(format)) {
            format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
        }

        for(var k in o) {
            if(new RegExp("("+ k +")").test(format)) {
                format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
            }
        }
        return format;
    };
</script>
<script src="<c:url value="/resources/web/jquery-1.8.2.min.js"/>"></script>
<script src="<c:url value="/resources/web/doT.js"/>"></script>
<script src="<c:url value="/resources/web/laypage/laypage.js"/>"></script>



<%--
  Created by IntelliJ IDEA.
  User: lihao1
  Date: 10/17/15
  Time: 23:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=9;Chrome=1" />
  <title></title>
  <%@include file="/common/web.jsp"%>

</head>
<body >
<%@include file="header.jsp"%>

<div class="bjMod">
  <div class="lb">
    <div class="lb_t">
      <div class="lb_h ny"><h3><a href="<c:url value="/html/web/index"/>">网站首页</a></h3></div>
    </div>
    <div class="jtgg">
      <img style="height: 400px;width: 100%" src="<c:url value="/${command.picUrl}"/>">
    </div>
    <div class="mbx">您当前位置：<a href="<c:url value="/html/web/index"/>">网站首页</a>&gt;&gt;<span>企业详情</span></div>
  </div>
</div>

<div class="bjMod">
  <div class="tabMod">
    <h3 class="top_h3">${command.name}</h3>
    <div class="tab_fbt">来源：罗江县食品药品监督管理局 发布时间：<fmt:formatDate value="${command.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/> </div>
    <div class="tab_st">
      ${command.description}
    </div>
  </div>
</div>
<!--footer s-->
<%@include file="footer.jsp"%>
<!--footer e-->

</body>
</html>

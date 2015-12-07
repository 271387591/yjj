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
      <img src="<c:url value="/${command.picUrl}"/>">
    </div>
    <div class="mbx">您当前位置：<a href="<c:url value="/html/web/index"/>">网站首页</a>&gt;&gt;<a href="<c:url value="/html/web/orgsurvey/${command.id}"/>">诚信记录</a>&gt;&gt;<span>得分细则</span></div>
  </div>
</div>

<div class="bjMod">
  <div class="tabMod">
    <h3 class="top_h3">${command.surveyName}</h3>
    <div class="tab_st">
      <div class="tabMod">
        <table class="cxtab" cellpadding="0" cellspacing="0" border="1" bordercolor="#000" style="width: 100%">
          <tbody>
          <tr align="center">
            <td width="112">检查项目</td>
            <td width="136">得分</td>
            <td width="120">方案细则</td>
          </tr>

          <c:forEach var="org" items="${command.orgQuestions}">
            <tr align="center">
              <td>${org.questionName}</td>
              <td>${org.mark}</td>
              <td>${org.questionRule}</td>
            </tr>
          </c:forEach>

          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>
<!--footer s-->
<%@include file="footer.jsp"%>
<!--footer e-->

</body>
</html>

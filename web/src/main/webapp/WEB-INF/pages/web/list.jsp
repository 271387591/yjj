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
<body  style="">
<%@include file="header.jsp"%>

<div class="bjMod">
  <div class="lb">
    <div class="lb_t">
      <div class="lb_h ny"><h3><a href="<c:url value="/html/web/index"/>">网站首页</a></h3></div>
    </div>
    <div class="jtgg">
      <img src="<c:url value="/${command.picUrl}"/>">
    </div>
    <div class="mbx">您当前位置：<a href="<c:url value="/html/web/index"/>">网站首页</a>&gt;&gt;<span>诚信记录</span></div>
  </div>
</div>

<div class="bjMod" id="orgSurveyDiv">

</div>
<!--footer s-->
<%@include file="footer.jsp"%>
<!--footer e-->
<script id="orgSurveyTpl" type="text/template">
  <div class="tabMod">
    <table class="cxtab" cellpadding="0" cellspacing="0" border="1" bordercolor="#000" style="width: 100%">
      <tbody>
      <tr align="center">
        <td width="112">检查项目</td>
        <td width="112">检查时间</td>
        <td width="112">实际得分</td>
        <td width="112">总分</td>
        <td width="136">最终得分</td>
        <td width="120">&nbsp;</td>
      </tr>

      {{ if(it.data && it.data.length>0) { }}
      {{ for (var i = 0, l = it.data.length; i < l; i++) { }}
      <tr align="center">
        <td>{{=it.data[i].surveyName||''}}</td>
        <td>{{=new Date(it.data[i].createDate).format('yyyy-MM-dd')}}</td>
        <td>{{=it.data[i].total||''}}</td>
        <td>{{=parseInt(it.data[i].allTotal)||''}}</td>
        <td>{{=parseInt((it.data[i].allTotal/it.data[i].total)*100)}}</td>
        <td><a class="cxtab_a" href="<c:url value="/html/web/orgquestion/{{=it.data[i].orgId}}/{{=it.data[i].surveyId}}"/>">查看得分细则</a></td>
      </tr>
      {{ } }}
      {{ }else{ }}
      <tr><td colspan="13">暂无数据</td></tr>
      {{}}}
      </tbody>
    </table>
    <div class="pageM">
      <div class="page_l" id="biuuu_city">
      </div>
    </div>
      <h3 style="color: #008855">
          备注：<br>得分计算公式为，最终得分 =（实际得分/总分）*100。例如，总分50分，您得了40分，那么您的得分是40除以50再乘以100，最终得分80分
      </h3>


  </div>
</script>

<script>
  function getOrgSurvey(page,params){
    var p= $.extend({page:page,limit:10},params);
    $.get(appPath+'html/web/listorgsurvey/${command.id}',p,function(result){
      if(result.success){
        var tmpl = document.getElementById('orgSurveyTpl').innerHTML;
        var doTtmpl = doT.template(tmpl);
        $('#orgSurveyDiv').html(doTtmpl(result));
        var pages=Math.ceil(result.total / 10);
        laypage({
          cont: 'biuuu_city',
          pages: pages,
            curr:result.page,
          jump: function(obj,first){
              if(!first){
                  getOrgSurvey(obj.curr,params);
              }
          }
        });
      }
    });
  }
  getOrgSurvey(1)
</script>



</body>
</html>

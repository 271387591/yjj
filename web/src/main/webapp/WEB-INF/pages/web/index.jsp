<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<body>
<div>
    <div class="bjMod">
        <img style="width: 1024px" src="<c:url value="/resources/images/banner.png"/>"/>
    </div>
    <%--dd--%>
    <div class="bjMod">
        <div class="nav">
            <div class="lb_h">
                <div class="nav_l">
                    <ul class="nav_lul">
                        <li><a href="javascript:void(0)" id="xb">食品生产企业</a></li>
                        <li><a href="javascript:void(0)" id="bg">食品销售企业</a></li>
                        <li><a href="javascript:void(0)" id="yx">餐饮企业</a></li>
                        <li><a href="javascript:void(0)" id="zx">药品经营企业</a></li>
                    </ul>
                </div>
                <div class="nav_r">
                    <div class="nav_r_l">
                        <span class="sl"></span>
          <span class="sc">
              <input id="searchText" style="color:#8F8FAD;" type="text" size="22" maxlength="64" value="Search" class="input1" onfocus="if(this.value=='Search'){this.value=''}" onblur="if(this.value==''){this.value='Search'}">
          </span>
                        <span class="sbtn" id="searchBtn"><input name="imageField" type="image" src="<c:url value="/resources/web/images/search.gif"/>"></span>
                        <span class="sr"></span>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="bjMod" id="orgDiv">
    </div>
    <script id="orgTpl" type="text/template">
        <div class="tabMod">
            <h1 class="top_h1" id="top_h1_title">企业（变更）公示</h1>
            <div class="tab_fbt">来源：罗江县食品药品监督管理局 </div>
            <div class="tab_st">
                <table class="tab_lb" cellpadding="0" cellspacing="0" border="1" bordercolor="#666464">
                    <tbody>
                    <tr class="tab_trt" align="center" style="background: #199ed7;">
                        <td width="30px">编号</td>
                        <td>单位名称</td>
                        <td>地址</td>
                        <td>法定代表人或负责人</td>
                        <td>类别</td>
                        <td>许可证编号</td>
                        <td>发证机关</td>
                        <td>发证日期</td>
                        <td>有限日期</td>
                        <td>诚信等级</td>
                        <td>诚信等级</td>
                        <td>企业介绍</td>
                    </tr>


                    {{ if(it.data && it.data.length>0) { }}
                    {{ for (var i = 0, l = it.data.length; i < l; i++) { }}
                    <tr align="center" style="background: {{=(i%2==0)?'#ceffff':'#f2f2f2'}}" >
                        <td>{{=it.data[i].orgNo||''}}</td>
                        <td>{{=it.data[i].name||''}}</td>
                        <td>{{=it.data[i].address||''}}</td>
                        <td>{{=it.data[i].dbr||''}}</td>
                        <td>
                            {{if(it.data[i].cls==1){ }}
                            食品生产企业
                            {{ }else if(it.data[i].cls==2){ }}
                            食品销售企业
                            {{}else if(it.data[i].cls==3){}}
                            餐饮企业
                            {{}else if(it.data[i].cls==4){}}
                            药品经营企业
                            {{}}}
                        </td>
                        <td>{{=it.data[i].xkzNo||''}}</td>
                        <td>{{=it.data[i].fzjg||''}}</td>
                        <td>{{=it.data[i].fzDate||''}}</td>
                        <td>{{=it.data[i].fzDate||''}}至{{=it.data[i].endDate||''}}</td>
                        <td>{{=it.data[i].integrity||''}}</td>
                        <td><a href="<c:url value="/html/web/orgsurvey/{{=it.data[i].id}}"/>"  class="tab_a">查看诚信记录</a></td>
                        <td><a  href="<c:url value="/html/web/org/{{=it.data[i].id}}"/>" class="tab_a">查看详情</a></td>

                    </tr>
                    {{ } }}
                    {{ }else{ }}
                    <tr><td colspan="13">暂无数据</td></tr>
                    {{}}}
                    </tbody>
                </table>
            </div>
            <div class="pageM">
                <div class="page_l" id="biuuu_city">
                </div>
            </div>
        </div>
</div>
</script>
<%@include file="footer.jsp"%>
<%--<script src="<c:url value="/resources/web/lb.js"/>"></script>--%>
<script>
//  $.post(appPath+'html/web/listorg',{limit:999999},function(result){
//    var tmpl = document.getElementById('advertUlTpl').innerHTML;
//    var doTtmpl = doT.template(tmpl);
//    $('#advertUl').html(doTtmpl(result));
//    animateOrg();
//  });
  function getOrg(page,type,params){
    var p= $.extend({page:page,limit:10,Q_cls_EQ:type},params);
    $.get(appPath+'html/web/listorg',p,function(result){
      if(result.success){
        var tmpl = document.getElementById('orgTpl').innerHTML;
        var doTtmpl = doT.template(tmpl);
        $('#orgDiv').html(doTtmpl(result));
        if(type==1){
          $('#top_h1_title').html('食品生产企业');
        }else if(type==2){
          $('#top_h1_title').html('食品销售企业');
        }else if(type==3){
          $('#top_h1_title').html('餐饮企业');
        }else if(type==4){
          $('#top_h1_title').html('药品经营企业');
        }
        var pages=Math.ceil(result.total / 10);
        laypage({
          cont: 'biuuu_city',
          pages: pages,
            curr:result.page,
          jump: function(obj,first){
            if(!first){
              getOrg(obj.curr,type,params);
            }
          }
        });
      }
    });
  }
  getOrg(1,1);
changeStatus($('#xb'));
  $('#xb').click(function(){
    getOrg(1,1);
    changeStatus($(this));
  });
  $('#bg').click(function(){
    getOrg(1,2);
    changeStatus($(this));
  });
  $('#yx').click(function(){
    getOrg(1,3);
    changeStatus($(this));
  });
  $('#zx').click(function(){
    getOrg(1,4);
    changeStatus($(this));
  });
  function changeStatus(a){
    $('.nav_lul').find('li a').not(a).css('color','#ffffff');
    $('.nav_lul').find('li a').not(a).removeClass('active');
    a.css('color','#ffcc00');
    a.addClass('active');
  }
  $('#searchBtn').click(function(){
    var search=$('#searchText').val();
    var p={};
    if(search!='Search'){
      p.Q_name_LK=search;
    }
    if($('#xb').hasClass('active')){
      getOrg(1,1,p);
    }else if($('#bg').hasClass('active')){
      getOrg(1,2,p);
    }else if($('#yx').hasClass('active')){
      getOrg(1,3,p);
    }else if($('#zx').hasClass('active')){
      getOrg(1,4,p);
    }else{
      getOrg(1,1,p);
    }
  });




</script>

</body>
</html>

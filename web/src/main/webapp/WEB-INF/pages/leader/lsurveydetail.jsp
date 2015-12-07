<%--
  Created by IntelliJ IDEA.
  User: lihao1
  Date: 7/31/15
  Time: 19:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@include file="/common/taglibs.jsp"%>
<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-lg-10">
        <h3>${command.name}</h3>
    </div>
    <div class="col-lg-2">

    </div>
</div>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-lg-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>基本信息</h5>
                    <div class="ibox-tools">
                        <div><a href="#leader/lsurvey"  class="btn btn-primary btn-xs">返回</a></div>
                    </div>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal m-t" id="userForm" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="id" value="${command.id}">
                        <div id="lookInfo" style="display: none">
                            <div class="form-group">
                                <label class="col-sm-2 col-lg-2 col-md-2 col-xs-2 control-label">审核意见：</label>
                                <div class="col-sm-8 col-lg-8 col-xs-8 col-md-8">
                                    <textarea class="form-control" name="remark"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-4 col-lg-4 col-md-4 col-xs-4 col-lg-offset-3 col-sm-offset-3 col-md-offset-3 col-xs-offset-3">
                                    <c:if test="${param.t eq 'h'}">
                                        <a onclick="sclick();"  class="btn btn-primary" type="button" style="width: 100px">审核通过</a>
                                        <a onclick="fclick();"  class="btn btn-default" type="button" style="width: 100px"> 不通过</a>
                                    </c:if>
                                    <a href="#leader/lsurvey"  class="btn btn-white" type="button" style="width: 100px">返回</a>
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>

                        </div>



                        <div class="form-group">
                            <label class="col-sm-2 col-lg-2 col-md-2 col-xs-2 control-label">方案名称：</label>
                            <div class="col-sm-8 col-lg-8 col-xs-8 col-md-8">
                                <input disabled="disabled" name="name" value="${command.name}" type="text"  class="form-control" data-validate="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-lg-2 col-md-2 col-xs-2 control-label">审核状态：</label>
                            <div class="col-sm-8 col-lg-8 col-xs-8 col-md-8">
                                <input disabled="disabled" name="name" value="${command.verify==0?'未审核':(command.verify==1?'待审核':(command.verify==2?'审核通过':'审核未通过'))}" type="text"  class="form-control" data-validate="required">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 col-lg-2 col-md-2 col-xs-2 control-label">参检机构：</label>
                            <div class="col-sm-8 col-lg-8 col-xs-8 col-md-8">
                                <table class="table table-bordered">
                                    <thead>
                                    <tr class="tr-tit">
                                        <th class="text-center">机构名称</th>
                                    </tr>
                                    </thead>
                                    <tbody>


                                    <c:forEach var="an" items="${command.orgs}">
                                        <tr class="tr-data" data-anid="${an.id}">
                                            <td >
                                                    ${an.name}
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-lg-2 col-md-2 col-xs-2 control-label">检查内容：</label>
                            <div class="col-sm-8 col-lg-8 col-xs-8 col-md-8">
                                <table class="table table-bordered" id="answerTable">
                                    <thead>
                                    <tr class="tr-tit">
                                        <th class="text-center">方案细则</th>
                                        <th class="text-center">分数</th>
                                    </tr>
                                    </thead>
                                    <tbody>


                                    <c:forEach var="an" items="${command.questions}">
                                        <tr class="tr-data" data-anid="${an.id}">
                                            <td >
                                                    ${an.name}
                                            </td>
                                            <td >
                                                    ${an.rule}
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>





                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    if(${param.t eq 'h'}){
        $('#lookInfo').show();
    }
    function sclick(){
        createConfirm(2,'你确定要通过该方案',verifysurvey);
    }
    function fclick(){
        createConfirm(3,'你确定该方案不通过',verifysurvey);
    }
    function verifysurvey(verify){
        var obj={
            name:'${command.name}',
            surveyId:'${command.id}',
            remark:$('textarea[name=remark]').val(),
            verify:verify
        }
        var result=requestJSONData('html/leader/verifysurvey',obj);
        if(result.success){
            setTimeout(function(){
                reloadHash('#leader/lsurvey');
            },300);
        }else{
            alertError(result.message);
        }
    }

    function viergysurveys(){
        var result=requestStringData('html/leader/viergysurveys/${command.id}');
        if(result.success){
            setTimeout(function(){
                reloadHash('#leader/lsurvey');
            },300);
        }else{
            alertError(result.message);
        }
    }
     function viergysurveyf(){
        var result=requestStringData('html/leader/viergysurveyf/${command.id}');
        if(result.success){
            setTimeout(function(){
                reloadHash('#leader/lsurvey');
            },300);
        }else{
            alertError(result.message);
        }
    }

</script>



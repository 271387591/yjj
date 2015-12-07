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
        <h3>重新评价</h3>
    </div>
    <div class="col-lg-2">

    </div>
</div>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-lg-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>方案打分</h5>
                    <div class="ibox-tools">
                        <div><a onclick="backWitch()" href="javascript:void(0)"  class="btn btn-primary btn-xs">返回</a></div>
                    </div>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal m-t" id="userForm" method="post" enctype="multipart/form-data">
                        <table class="table table-bordered" id="answerTable">
                            <tbody>
                            <tr class="tr-tit">
                                <th class="text-center" colspan="3">${command.name}</th>
                            </tr>
                            <tr class="tr-tit">
                                <th >考评标题</th>
                                <th >评价</th>
                                <th >评分细则</th>
                            </tr>
                            <c:forEach var="an" items="${command.orgQuestions}">
                                <tr class="tr-data" data-anid="${an.id}">
                                    <td>
                                        <input class="hidden" value="${an.questionName}" name="name">
                                            ${an.questionName}
                                    </td>
                                    <td  style="width: 100px">
                                        <input   name="mark" type="text" value="${an.mark}">
                                    </td>
                                    <td >
                                        <input class="hidden" value="${an.questionRule}" name="rule">
                                            ${an.questionRule}
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <div class="form-group">
                            <div class="col-sm-4 col-lg-4 col-md-4 col-xs-4 col-lg-offset-3 col-sm-offset-3 col-md-offset-3 col-xs-offset-3">
                                <a href="javascript:void(0);" id="submitOrgQuestion"  class="btn btn-primary" type="button">提交</a>
                                <a onclick="backWitch()" href="javascript:void(0)" class="btn btn-white" type="button">返回</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $('#submitOrgQuestion').click(function(){
        var table=$('#answerTable').find('tbody');
        var answers=[];
        table.find('tr:gt(1)').each(function(){
            var obj={};
            var tr=$(this);
            var mark=tr.find('input[name=mark]').val();
            var name=tr.find('input[name=name]').val();
            var rule=tr.find('input[name=rule]').val();
            obj.id=tr.data('anid');
            obj.mark=mark;
            obj.surveyName='${command.name}';
            obj.questionName=name;
            obj.questionRule=rule;
            obj.questionId=tr.data('anid');
            answers.push(obj);
        });
        var result=requestJSONData('html/assess/updateorgquestion/${command.orgId}/${command.id}',answers);
        if(result.success){
            alertSuccess('评价成功');
            backWitch();
            <%--reloadHash('#assess/orgsurvey/${command.orgId}')--%>
        }else{
            alertError(result.message);
        }
    });
    function backWitch(){
        if(${param.s==0}){
            reloadHash('#assess/surveypj/${command.id}');
        }else{
            reloadHash('#assess/orgsurvey/${command.orgId}');
        }
    }

</script>



<%--
  Created by IntelliJ IDEA.
  User: lihao1
  Date: 7/31/15
  Time: 19:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@include file="/common/taglibs.jsp"%>
<div class="row ">
    <div class="col-sm-12 col-lg-12 col-md-12 col-xs-12">
        <ol class="breadcrumb" style="border-radius:0;padding: 10px">
            <li>
                <a href="#assess/org">部门机构</a>
            </li>
            <li>
                <a href="#assess/orgsurvey/${command.id}">机构考评方案</a>
            </li>
            <li>
                <strong>添加方案</strong>
            </li>
        </ol>
    </div>
    <div class="col-xs-2">

    </div>
</div>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-10 col-lg-6 col-md-8 col-xs-12 col-lg-offset-3 col-sm-offset-3 col-md-offset-2">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <div class="ibox-tools">
                        <div><a href="#assess/orgsurvey/${command.id}"  class="btn btn-primary btn-xs">返回</a></div>
                    </div>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal m-t" id="userForm" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="id" value="${command.id}">
                        <div class="form-group">
                            <label class="col-sm-2 col-lg-2 col-md-2 col-xs-2 control-label">方案名称：</label>
                            <div class="col-sm-8 col-lg-8 col-md-8 col-xs-8">
                                <select multiple="multiple" id="orgSurveySelector" name="roles">
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-4 col-lg-4 col-md-4 col-xs-4 col-lg-offset-3 col-sm-offset-3 col-md-offset-3 col-xs-offset-3">
                                <button class="btn btn-primary" type="button" onclick="addOrgSurvey();">保存</button>
                                <a href="#assess/orgsurvey/${command.id}"  class="btn btn-white" type="button">返回</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    initComboData($('#orgSurveySelector'),'html/assess/listsurvey',{start:0,limit:2000,Q_verify_EQ:2},'id','name');
    $.get(appPath+'html/assess/listorgsurvey/${command.id}',{start:0,limit:10000},function(result){
        var list=result.data;
        var sel=$('#orgSurveySelector');
        var ts=[];
        for(var i=0;i<list.length;i++){
            ts.push(list[i].id);
        }
        sel.val(ts);
        searchMultiSelect(sel,{
            width:550,
            selectableFooter: "<div class='blue-bg label-default'>可选方案</div>",
            selectionFooter: "<div class='blue-bg label-default'>已选方案</div>"
        });
    });

    function addOrgSurvey(){
        var sel=$('#orgSurveySelector').val();
        if(sel && sel.length>0){
            var surveyIds=sel.join(",");
            var obj={
                surveyIds:surveyIds
            }
            var result=requestStringData('html/assess/saveorgsurvey/${command.id}',obj);
            if(result.success){
                setTimeout(function(){
                    reloadHash('#assess/orgsurvey/${command.id}')
                },300);
            }else{
                alertError(result.message);
            }
        }else{
            alertError('请选择方案');
        }
    }


</script>


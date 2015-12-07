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
        <h3>机构考评方案</h3>
    </div>
    <div class="col-lg-2">

    </div>
</div>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-lg-12">
            <div class="ibox">
                <div class="ibox-title">
                    <h5>该机构考评方案列表</h5>
                    <div class="ibox-tools">
                        <div><a href="#assess/org"  class="btn btn-primary btn-xs">返回</a></div>
                    </div>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal" id="userSearch">
                        <div class="col-sm-4 col-lg-4 col-md-4 col-xs-4">
                            <div class="form-group">
                                <label class="col-sm-4 col-lg-4 col-md-4 col-xs-4 control-label">方案名称</label>
                                <div class="col-sm-8 col-lg-8 col-xs-8 col-md-8">
                                    <input type="text" name="user.Q_s.name_LK" class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4 col-lg-4 col-md-4 col-xs-4 col-sm-offset-2">
                                <button onclick="searchForm($('#userSearch'),'user',listFTable);" class="btn btn-primary btn-sm" id="searchBtn" type="button" >搜索</button>
                                <button onclick="clearSearchForm($('#userSearch'),listFTable);" class="btn btn-white btn-sm" id="clearnBtn" type="button" >清空</button>
                            </div>
                        </div>
                    </form>

                    <table class="table table-bordered table-hover" id="pubTable" >
                        <thead>
                        <tr>
                            <th colspan="3" style="text-align: center;">${command.name}</th>
                        </tr>
                        <tr>
                            <th>方案名称</th>
                            <th>得分</th>
                            <th style="width: 100px">操作</th>
                        </tr>

                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                    <div class="row">
                        <div id="pubTablepager" class="paging"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var orgId='${command.id}';
    var orgName='${command.id}';
</script>
<script type="text/javascript" src="<c:url value="/resources/js/org.js"/>"></script>
<script type="text/javascript">

    jQuery(function(){
        listFTable();
//        $('#addOrgSurveyModal').find('#saveOrgSurvey').click(function(){
//            var sel=$('#addOrgSurveyModal').find('#orgSurveySelector').val();
//            if(sel && sel.length>0){
//                var surveyIds=sel.join(",");
//                var obj={
//                    surveyIds:surveyIds
//                }
//                var result=requestStringData('html/assess/saveorgsurvey/'+orgId,obj);
//                if(result.success){
//                    $('#addOrgSurveyModal').find('#orgSurveySelector').val('');
//                    $('#addOrgSurveyModal').modal('hide');
//                    setTimeout(function(){
//                        listFTable();
//                    },300);
//                }else{
//                    alertError(result.message);
//                }
//            }else{
//                alertError('请选择方案');
//            }
//        })
//        $('#addOrgSurveyModal').on('shown.bs.modal',function(){
//            $(this).find('#orgSurveySelector').val();
//        });
    });
</script>




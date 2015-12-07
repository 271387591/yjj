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
    <div class="col-sm-10 col-lg-10 col-md-10 col-xs-10">
        <h3>${command.id!=null?"编辑方案":"添加方案"}</h3>
        <ol class="breadcrumb">

            <li>
                <a href="#assess/survey">方案列表</a>
            </li>
            <li>
                <strong>${command.id!=null?"编辑方案":"添加方案"}</strong>
            </li>
        </ol>
    </div>
    <div class="col-xs-2">

    </div>
</div>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12 col-lg-12 col-md-12 col-xs-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>基本信息</h5>
                    <div class="ibox-tools">
                        <div><a href="#assess/survey"  class="btn btn-primary btn-xs">返回</a></div>
                    </div>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal m-t" id="userForm" method="post" >
                        <input type="hidden" name="id" value="${command.id}">
                        <div class="form-group">
                            <label class="col-sm-2 col-lg-2 col-md-2 col-xs-2 control-label">方案名称：</label>
                            <div class="col-sm-8 col-lg-8 col-md-8 col-xs-8">
                                <input name="name" value="${command.name}" type="text"  class="form-control" data-validate="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-lg-2 col-md-2 col-xs-2 control-label">参检机构：</label>
                            <div class="col-sm-8 col-lg-8 col-md-8 col-xs-8">
                                <select name="orgs" multiple class="form-control" data-validate="required"></select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-lg-2 col-md-2 col-xs-2 control-label">检查内容：</label>
                            <div class="col-sm-8 col-lg-8 col-md-8 col-xs-8">
                                <select multiple name="questions" class="form-control" data-validate="required"></select>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-4 col-lg-4 col-md-4 col-xs-4 col-lg-offset-3 col-sm-offset-3 col-md-offset-3 col-xs-offset-3">
                                <button class="btn btn-primary" type="button" onclick="saveModel();">保存</button>
                                <a href="#assess/survey"  class="btn btn-white" type="button">返回</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="<c:url value="/resources/js/survey.js"/>"></script>
<script type="text/javascript">
    (function(){
        var oss=[];
        <c:forEach var="s" items="${command.orgs}">
        oss.push('${s.id}');
        </c:forEach>
        var qss=[];
        <c:forEach var="s" items="${command.questions}">
        qss.push('${s.id}');
        </c:forEach>
        initComboData($('select[name=orgs]'),'html/assess/listorg',{},"id","name",oss);
        initComboData($('select[name=questions]'),'html/assess/listquestion',{},"id","questionDes",qss);
    })();
    jQuery(function(){
        keyupform($('#userForm'));
        hchosen($('select'))
    });
</script>


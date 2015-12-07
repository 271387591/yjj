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
        <h3>${command.id!=null?"编辑分类":"添加分类"}</h3>
        <ol class="breadcrumb">
            <li>
                <a href="<c:url value="/html/dictionaryType/index"/>">字典分类列表</a>
            </li>
            <li>
                <strong>${command.id!=null?"编辑分类":"添加分类"}</strong>
            </li>
        </ol>
    </div>
    <div class="col-lg-2">

    </div>
</div>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-lg-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>字典分类</h5>
                    <div class="ibox-tools">
                        <div><a onclick="ajaxReloadPage('content-wrapper','html/dictionaryType/index');" href="#dictionaryType" class="btn btn-primary btn-xs">返回</a></div>
                    </div>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal m-t" id="userForm" method="post">
                        <input type="hidden" name="id" value="${command.id}">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">分类名称：</label>
                            <div class="col-sm-8">
                                <input type="text" value="${command.dtype}" class="form-control" name="dtype" data-validate="required">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-4 col-sm-offset-3">
                                <button class="btn btn-primary" type="button" onclick="saveModel();">保存内容</button>
                                <button class="btn btn-white" type="button" onclick="ajaxReloadPage('content-wrapper','html/dictionaryType/index');">返回</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="<c:url value="/resources/js/dictionary/dictionaryType.js"/>"></script>
<script type="text/javascript">
    jQuery(function(){
        keyupform($('#userForm'));
    });
</script>



<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
    <%--<meta charset="utf-8">--%>
    <%--<meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
    <%--<title>${command.id!=null?"编辑分类":"添加分类"}</title>--%>
    <%--<%@include file="/common/taglibs.jsp"%>--%>
<%--</head>--%>
<%--<body >--%>
<%--<div id="wrapper">--%>
    <%--<%@include file="../menu.jsp"%>--%>
    <%--<div id="page-wrapper" class="gray-bg dashbard-1">--%>
        <%--<%@include file="../header.jsp"%>--%>
        <%--<div class="row wrapper border-bottom white-bg page-heading">--%>
            <%--<div class="col-lg-10">--%>
                <%--<h3>${command.id!=null?"编辑分类":"添加分类"}</h3>--%>
                <%--<ol class="breadcrumb">--%>
                    <%--<li>--%>
                        <%--<a href="<c:url value="/html/dictionaryType/index"/>">字典分类列表</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<strong>${command.id!=null?"编辑分类":"添加分类"}</strong>--%>
                    <%--</li>--%>
                <%--</ol>--%>
            <%--</div>--%>
            <%--<div class="col-lg-2">--%>

            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="wrapper wrapper-content animated fadeInRight">--%>
            <%--<div class="row">--%>
                <%--<div class="col-lg-12">--%>
                    <%--<div class="ibox float-e-margins">--%>
                        <%--<div class="ibox-title">--%>
                            <%--<h5>字典分类</h5>--%>
                            <%--<div class="ibox-tools">--%>
                                <%--<div><a href="<c:url value="/html/dictionaryType/index"/>" class="btn btn-primary btn-xs">返回</a></div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="ibox-content">--%>
                            <%--<form class="form-horizontal m-t" id="userForm" method="post">--%>
                                <%--<input type="hidden" name="id" value="${command.id}">--%>
                                <%--<div class="form-group">--%>
                                    <%--<label class="col-sm-3 control-label">分类名称：</label>--%>
                                    <%--<div class="col-sm-8">--%>
                                        <%--<input type="text" value="${command.dtype}" class="form-control" name="dtype" data-validate="required">--%>
                                    <%--</div>--%>
                                <%--</div>--%>

                                <%--<div class="form-group">--%>
                                    <%--<div class="col-sm-4 col-sm-offset-3">--%>
                                        <%--<button class="btn btn-primary" type="button" onclick="saveModel();">保存内容</button>--%>
                                        <%--<button class="btn btn-white" type="button" onclick="reloadPage('html/dictionaryType/index')">返回</button>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</form>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<%@include file="../footer.jsp"%>--%>
    <%--</div>--%>
<%--</div>--%>
<%--<script type="text/javascript" src="<c:url value="/resources/js/dictionary/dictionaryType.js"/>"></script>--%>
<%--<script type="text/javascript">--%>
    <%--changeMenu('menu-dic');--%>
    <%--jQuery(function(){--%>
        <%--keyupform($('#userForm'));--%>
    <%--});--%>
<%--</script>--%>

<%--</body>--%>
<%--</html>--%>

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
        <h3>${command.id!=null?"编辑字典值":"添加字典值"}</h3>
        <ol class="breadcrumb">
            <li>
                <a href="<c:url value="/html/dictionary/index"/>">字典值列表</a>
            </li>
            <li>
                <strong>${command.id!=null?"编辑字典值":"添加字典值"}</strong>
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
                    <h5>字典值</h5>
                    <div class="ibox-tools">
                        <div><a  href="#dictionary/index" class="btn btn-primary btn-xs">返回</a></div>
                    </div>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal m-t" id="userForm" method="post">
                        <input type="hidden" name="id" value="${command.id}">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">分类：</label>
                            <div class="col-sm-8">
                                <select name="typeId" class="width-100" data-validate="required" id="roleSelector">
                                    <option value="">请选择</option>
                                    <option value="0">项目关键字</option>
                                    <option value="4">行业</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">值：</label>
                            <div class="col-sm-8">
                                <input type="text" value="${command.keyValue}" class="form-control" name="keyValue" data-validate="required">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-4 col-sm-offset-3">
                                <button class="btn btn-primary" type="button" onclick="saveModel();">保存内容</button>
                                <a class="btn btn-white" href="#dictionary/index">返回</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="<c:url value="/resources/js/dictionary/dictionary.js"/>"></script>
<script type="text/javascript">
    initComboData($('#roleSelector'),'html/dictionaryType/list',{start:0,limit:200},'id','dtype','${command.typeId}');
    jQuery(function(){
        keyupform($('#userForm'));
        hchosen($('#roleSelector'));
    });
</script>


<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
    <%--<meta charset="utf-8">--%>
    <%--<meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
    <%--<title>${command.id!=null?"编辑字典值":"添加字典值"}</title>--%>
    <%--<%@include file="/common/taglibs.jsp"%>--%>
<%--</head>--%>
<%--<body >--%>
<%--<div id="wrapper">--%>
    <%--<%@include file="../menu.jsp"%>--%>
    <%--<div id="page-wrapper" class="gray-bg dashbard-1">--%>
        <%--<%@include file="../header.jsp"%>--%>
        <%--<div class="row wrapper border-bottom white-bg page-heading">--%>
            <%--<div class="col-lg-10">--%>
                <%--<h3>${command.id!=null?"编辑字典值":"添加字典值"}</h3>--%>
                <%--<ol class="breadcrumb">--%>
                    <%--<li>--%>
                        <%--<a href="<c:url value="/html/dictionary/index"/>">字典值列表</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<strong>${command.id!=null?"编辑字典值":"添加字典值"}</strong>--%>
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
                            <%--<h5>字典值</h5>--%>
                            <%--<div class="ibox-tools">--%>
                                <%--<div><a href="<c:url value="/html/dictionary/index"/>" class="btn btn-primary btn-xs">返回</a></div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="ibox-content">--%>
                            <%--<form class="form-horizontal m-t" id="userForm" method="post">--%>
                                <%--<input type="hidden" name="id" value="${command.id}">--%>
                                <%--<div class="form-group">--%>
                                    <%--<label class="col-sm-3 control-label">分类：</label>--%>
                                    <%--<div class="col-sm-8">--%>
                                        <%--<select name="typeId" class="width-100" data-validate="required" id="roleSelector">--%>
                                            <%--<option value="">请选择</option>--%>
                                            <%--<option value="0">项目关键字</option>--%>
                                            <%--<option value="4">行业</option>--%>
                                        <%--</select>--%>
                                    <%--</div>--%>
                                <%--</div>--%>

                                <%--<div class="form-group">--%>
                                    <%--<label class="col-sm-3 control-label">值：</label>--%>
                                    <%--<div class="col-sm-8">--%>
                                        <%--<input type="text" value="${command.keyValue}" class="form-control" name="keyValue" data-validate="required">--%>
                                    <%--</div>--%>
                                <%--</div>--%>

                                <%--<div class="form-group">--%>
                                    <%--<div class="col-sm-4 col-sm-offset-3">--%>
                                        <%--<button class="btn btn-primary" type="button" onclick="saveModel();">保存内容</button>--%>
                                        <%--<button class="btn btn-white" type="button" onclick="reloadPage('html/dictionary/index')">返回</button>--%>
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
<%--<script type="text/javascript" src="<c:url value="/resources/js/dictionary/dictionary.js"/>"></script>--%>
<%--<script type="text/javascript">--%>
    <%--changeMenu('menu-dic');--%>
    <%--initComboData($('#roleSelector'),'html/dictionaryType/list',{start:0,limit:200},'id','dtype','${command.typeId}');--%>
    <%--jQuery(function(){--%>
        <%--keyupform($('#userForm'));--%>
        <%--hchosen($('#roleSelector'));--%>
    <%--});--%>
<%--</script>--%>

<%--</body>--%>
<%--</html>--%>

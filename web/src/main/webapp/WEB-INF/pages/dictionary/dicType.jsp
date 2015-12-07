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
        <h3>数据字典</h3>
        <ol class="breadcrumb">
            <li>
                <strong>字典分类</strong>
            </li>
        </ol>
    </div>
    <div class="col-lg-2">

    </div>
</div>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-lg-12">
            <div class="ibox">
                <div class="ibox-title">
                    <h5>字典分类</h5>
                    <div class="ibox-tools">
                        <div><a href="javascript:void(0);" onclick="edit();" class="btn btn-primary btn-xs ${oz:contains(userinfo.features, 'addUser')?'':'hidden'}">添加</a></div>
                    </div>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal" id="userSearch">
                        <div class="col-sm-5">
                            <div class="form-group">
                                <label class="col-sm-4 control-label">分类</label>
                                <div class="col-sm-8">
                                    <input type="text" name="user.Q_dtype_LK" class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4 col-sm-offset-2">
                                <button class="btn btn-primary" type="button" onclick="searchForm($('#userSearch'),'user',listTable);">搜索</button>
                                <button class="btn btn-white" type="button" onclick="clearSearchForm($('#userSearch'),listTable);">清空</button>
                            </div>
                        </div>
                    </form>
                    <table class="table table-bordered table-hover" id="userTable" style="overflow-x: scroll">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>分类</th>
                            <th>创建时间</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                    <div class="row">
                        <div id="pager" class="paging"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="<c:url value="/resources/js/dictionary/dictionaryType.js"/>"></script>
<script type="text/javascript">
    jQuery(function(){
        listTable();
    })

</script>


<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
    <%--<meta charset="utf-8">--%>
    <%--<meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
    <%--<title>字典分类</title>--%>
    <%--<%@include file="/common/taglibs.jsp"%>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div id="wrapper">--%>
    <%--<%@include file="../menu.jsp"%>--%>
    <%--<div id="page-wrapper" class="gray-bg dashbard-1">--%>
        <%--<%@include file="../header.jsp"%>--%>
        <%--<div class="row wrapper border-bottom white-bg page-heading">--%>
            <%--<div class="col-lg-10">--%>
                <%--<h3>数据字典</h3>--%>
                <%--<ol class="breadcrumb">--%>
                    <%--<li>--%>
                        <%--<strong>字典分类</strong>--%>
                    <%--</li>--%>
                <%--</ol>--%>
            <%--</div>--%>
            <%--<div class="col-lg-2">--%>

            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="wrapper wrapper-content animated fadeInRight">--%>
            <%--<div class="row">--%>
                <%--<div class="col-lg-12">--%>
                    <%--<div class="ibox">--%>
                        <%--<div class="ibox-title">--%>
                            <%--<h5>字典分类</h5>--%>
                            <%--<div class="ibox-tools">--%>
                                <%--<div><a href="javascript:void(0);" onclick="edit();" class="btn btn-primary btn-xs ${oz:contains(userinfo.features, 'addUser')?'':'hidden'}">添加</a></div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="ibox-content">--%>
                            <%--<form class="form-horizontal" id="userSearch">--%>
                                <%--<div class="col-sm-5">--%>
                                    <%--<div class="form-group">--%>
                                        <%--<label class="col-sm-4 control-label">分类</label>--%>
                                        <%--<div class="col-sm-8">--%>
                                            <%--<input type="text" name="user.Q_dtype_LK" class="form-control">--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="form-group">--%>
                                    <%--<div class="col-sm-4 col-sm-offset-2">--%>
                                        <%--<button class="btn btn-primary" type="button" onclick="searchForm($('#userSearch'),'user',listTable);">搜索</button>--%>
                                        <%--<button class="btn btn-white" type="button" onclick="clearSearchForm($('#userSearch'),listTable);">清空</button>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</form>--%>
                            <%--<table class="table table-bordered table-hover" id="userTable" style="overflow-x: scroll">--%>
                                <%--<thead>--%>
                                <%--<tr>--%>
                                    <%--<th>ID</th>--%>
                                    <%--<th>分类</th>--%>
                                    <%--<th>创建时间</th>--%>
                                    <%--<th></th>--%>
                                <%--</tr>--%>
                                <%--</thead>--%>
                                <%--<tbody>--%>
                                <%--</tbody>--%>
                            <%--</table>--%>
                            <%--<div class="row">--%>
                                <%--<div id="pager" class="paging"></div>--%>
                            <%--</div>--%>
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
        <%--listTable();--%>
    <%--})--%>

<%--</script>--%>

<%--</body>--%>
<%--</html>--%>

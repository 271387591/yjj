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
    <div class="col-lg-10 col-sm-10 col-xs-10 col-md-10">
        <h3>用户模块</h3>
        <ol class="breadcrumb">
            <li>
                <a href="#">主页</a>
            </li>
            <li>
                <strong>用户管理</strong>
            </li>
        </ol>
    </div>
    <div class="col-lg-2">

    </div>
</div>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-lg-12 col-sm-12 col-xs-12 col-md-12">
            <div class="ibox">
                <div class="ibox-title">
                    <h5>用户列表</h5>
                    <div class="ibox-tools">
                        <%--<div><a href="javascript:void(0);" onclick="edit();" class="btn btn-primary btn-xs ${oz:contains(userinfo.features, 'addUser')?'':'hidden'}">添加</a></div>--%>
                        <div><a href="#/user/addUser"  class="btn btn-primary btn-xs ">添加</a></div>
                    </div>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal" id="userSearch">
                        <div class="col-sm-4 col-lg-4 col-xs-4 col-md-4">
                            <div class="form-group">
                                <label class="col-sm-4 col-lg-4 col-xs-4 col-md-4 control-label">用户名</label>
                                <div class="col-sm-8 col-lg-8 col-xs-8 col-md-8">
                                    <input type="text" name="user.Q_f.username_LK" class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-4 col-lg-4 col-xs-4 col-md-4">
                            <div class="form-group">
                                <label class="col-sm-4 col-lg-4 col-xs-4 col-md-4 control-label">姓名</label>
                                <div class="col-sm-8 col-lg-8 col-xs-8 col-md-8">
                                    <input type="text" name="user.Q_f.nickName_LK" class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4 col-sm-offset-2 col-lg-offset-2 col-xs-offset-2 col-md-offset-2 col-lg-4 col-xs-4 col-md-4">
                                <button class="btn btn-primary" type="button" onclick="searchForm($('#userSearch'),'user',listTable);">搜索</button>
                                <button class="btn btn-white" type="button" onclick="clearSearchForm($('#userSearch'),listTable);">清空</button>
                            </div>
                        </div>
                    </form>
                    <table class="table table-bordered table-hover" id="userTable" style="overflow-x: scroll">
                        <thead>
                        <tr>
                            <th>用户名</th>
                            <th>姓名</th>
                            <th>电子邮件</th>
                            <th>创建时间</th>
                            <th>是否可用</th>
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

<div id="changeUserPasswordModel" class="modal fade" role="dialog" aria-labelledby="gridSystemModalLabel" aria-hidden="true">
    <div class="modal-dialog width-35">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="gridSystemModalLabel"><i class="icon-cog"></i>修改密码</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <form class="form-horizontal" id="updateUserPwd">
                        <div class="form-group">
                            <div>
                                <input type="hidden" name="id" id="changePwdUserId">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label">新密码:</label>
                            <div>
                                <input autocomplete="off" type="password" class="form-control" name="password" data-validate="password" id="inputUserPassword">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label">确认密码:</label>
                            <div>
                                <input type="password" class="form-control" data-validate="passwordHit" data-hit="inputUserPassword">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" id="changePasswordBtn" class="btn btn-primary" onclick="changeUserPwd($('#changeUserPasswordModel'))">确定</button>
                <button type="button" class="btn" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="<c:url value="/resources/js/user/user.js"/>"></script>
<script type="text/javascript">
    jQuery(function(){
        keyupform($('#changeUserPasswordModel #updateUserPwd'));
        $('#changeUserPasswordModel').on('show.bs.modal', function (event) {
            clearForm($('#updateUserPwd'));
            $('#changePwdUserId').val($(event.relatedTarget).attr('userId'));
        });
        listTable();
    })

</script>



<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
    <%--<meta charset="utf-8">--%>
    <%--<meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
    <%--<title>用户模块</title>--%>
    <%--<%@include file="/common/taglibs.jsp"%>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div id="wrapper">--%>
    <%--<%@include file="../menu.jsp"%>--%>
    <%--<div id="page-wrapper" class="gray-bg dashbard-1">--%>
        <%--<%@include file="../header.jsp"%>--%>
        <%--<div class="row wrapper border-bottom white-bg page-heading">--%>
            <%--<div class="col-lg-10">--%>
                <%--<h3>用户</h3>--%>
                <%--<ol class="breadcrumb">--%>
                    <%--<li>--%>
                        <%--<a href="<c:url value="/html/home"/>">主页</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<strong>用户管理</strong>--%>
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
                            <%--<h5>用户列表</h5>--%>
                            <%--<div class="ibox-tools">--%>
                                <%--<div><a href="javascript:void(0);" onclick="edit();" class="btn btn-primary btn-xs ${oz:contains(userinfo.features, 'addUser')?'':'hidden'}">添加</a></div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="ibox-content">--%>
                            <%--<form class="form-horizontal" id="userSearch">--%>
                                <%--<div class="col-sm-4">--%>
                                    <%--<div class="form-group">--%>
                                        <%--<label class="col-sm-4 control-label">用户名</label>--%>
                                        <%--<div class="col-sm-8">--%>
                                            <%--<input type="text" name="user.Q_f.username_LK" class="form-control">--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="col-sm-4">--%>
                                    <%--<div class="form-group">--%>
                                        <%--<label class="col-sm-4 control-label">姓名</label>--%>
                                        <%--<div class="col-sm-8">--%>
                                            <%--<input type="text" name="user.Q_f.nickName_LK" class="form-control">--%>
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
                                    <%--<th>用户名</th>--%>
                                    <%--<th>姓名</th>--%>
                                    <%--<th>电子邮件</th>--%>
                                    <%--<th>创建时间</th>--%>
                                    <%--<th>是否可用</th>--%>
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
<%--<div id="changeUserPasswordModel" class="modal fade" role="dialog" aria-labelledby="gridSystemModalLabel" aria-hidden="true">--%>
    <%--<div class="modal-dialog width-35">--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
                <%--<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>--%>
                <%--<h4 class="modal-title" id="gridSystemModalLabel"><i class="icon-cog"></i>修改密码</h4>--%>
            <%--</div>--%>
            <%--<div class="modal-body">--%>
                <%--<div class="container-fluid">--%>
                    <%--<form class="form-horizontal" id="updateUserPwd">--%>
                        <%--<div class="form-group">--%>
                            <%--<div>--%>
                                <%--<input type="hidden" name="id" id="changePwdUserId">--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="form-group">--%>
                            <%--<label class="control-label">新密码:</label>--%>
                            <%--<div>--%>
                                <%--<input autocomplete="off" type="password" class="form-control" name="password" data-validate="password" id="inputUserPassword">--%>
                            <%--</div>--%>
                        <%--</div>--%>

                        <%--<div class="form-group">--%>
                            <%--<label class="control-label">确认密码:</label>--%>
                            <%--<div>--%>
                                <%--<input type="password" class="form-control" data-validate="passwordHit" data-hit="inputUserPassword">--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</form>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="modal-footer">--%>
                <%--<button type="button" id="changePasswordBtn" class="btn btn-primary" onclick="changeUserPwd($('#changeUserPasswordModel'))">确定</button>--%>
                <%--<button type="button" class="btn" data-dismiss="modal">关闭</button>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>
<%--<script type="text/javascript" src="<c:url value="/resources/js/user/user.js"/>"></script>--%>
<%--<script type="text/javascript">--%>
    <%--changeMenu('menu-user');--%>
    <%--jQuery(function(){--%>
        <%--keyupform($('#changeUserPasswordModel #updateUserPwd'));--%>
        <%--$('#changeUserPasswordModel').on('show.bs.modal', function (event) {--%>
            <%--clearForm($('#updateUserPwd'));--%>
            <%--$('#changePwdUserId').val($(event.relatedTarget).attr('userId'));--%>
        <%--});--%>
        <%--listTable();--%>
    <%--})--%>

<%--</script>--%>

<%--</body>--%>
<%--</html>--%>

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
        <h3>${command.id!=null?"编辑用户":"添加用户"}</h3>
        <ol class="breadcrumb">
            <li>
                <a href="#">主页</a>
            </li>
            <li>
                <a href="#/user/index">用户列表</a>
            </li>
            <li>
                <strong>${command.id!=null?"编辑用户":"添加用户"}</strong>
            </li>
        </ol>
    </div>
    <div class="col-lg-2">

    </div>
</div>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-lg-12 col-sm-12  col-xs-12 col-md-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>用户基本信息</h5>
                    <div class="ibox-tools">
                        <%--<div><a href="#user" onclick="ajaxReloadPage('content-wrapper','html/user/index');" class="btn btn-primary btn-xs">返回</a></div>--%>
                        <div><a href="#/user/index"  class="btn btn-primary btn-xs">返回</a></div>
                    </div>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal m-t" id="userForm" method="post">
                        <input type="hidden" name="id" value="${command.id}">
                        <div class="form-group">
                            <label class="col-sm-3 control-label  col-lg-3 col-xs-3 col-md-3">用户名：</label>
                            <div class="col-sm-8 col-lg-8 col-xs-8 col-md-8 ">
                                <input name="username" value="${command.username}" type="text" ${command.id!=null?"readonly":""} class="form-control" data-validate="username">
                            </div>
                        </div>
                        <c:if test="${command.id==null}">
                            <div class="form-group">
                                <label class="col-sm-3 control-label  col-lg-3 col-xs-3 col-md-3">密码：</label>
                                <div class="col-sm-8 col-lg-8 col-xs-8 col-md-8">
                                    <input name="password"  type="password" class="form-control" data-validate="password" id="userPassword">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label  col-lg-3 col-xs-3 col-md-3">确认密码：</label>
                                <div class="col-sm-8 col-lg-8 col-xs-8 col-md-8">
                                    <input name="password" data-validate="passwordHit" data-hit="userPassword" type="password" class="form-control">
                                </div>
                            </div>
                        </c:if>
                        <div class="form-group">
                            <label class="col-sm-3 control-label  col-lg-3 col-xs-3 col-md-3">姓名：</label>
                            <div class="col-sm-8 col-lg-8 col-xs-8 col-md-8">
                                <input type="text" value="${command.nickName}" class="form-control" name="nickName" data-validate="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label  col-lg-3 col-xs-3 col-md-3">电子邮件：</label>
                            <div class="col-sm-8 col-lg-8 col-xs-8 col-md-8">
                                <input type="text" value="${command.email}" class="form-control" name="email" data-validate="email">
                            </div>
                        </div>
                        <div class="form-group">
                        <label class="col-sm-3 control-label col-lg-3 col-xs-3 col-md-3">选择角色：</label>
                        <div class="col-sm-8 col-lg-8 col-xs-8 col-md-8">
                        <select class="chosen-select form-control"  tabindex="2" id="roleSelector" data-validate="required" name="roleId">
                        </select>
                        </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4 col-sm-offset-3 col-lg-offset-3 col-xs-offset-3 col-md-offset-3  col-lg-4 col-xs-4 col-md-4">
                                <button class="btn btn-primary" type="button" onclick="saveModel();">保存内容</button>
                                <%--<button class="btn btn-white" type="button" onclick="ajaxReloadPage('content-wrapper','html/user/index');">返回</button>--%>
                                <a href="#/user/index" class="btn btn-white" type="button">返回</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="<c:url value="/resources/js/user/user.js"/>"></script>
<script type="text/javascript">
    initComboData($("#roleSelector"),'html/role/list',{start:0,limit:20},'id','displayName');
    if(${command.id!=null}){
        var rs=[];
        <c:forEach var="r" items="${command.roles}">
        rs.push(${r.id});
        </c:forEach>
        $('#roleSelector').val(rs);
    }

    jQuery(function(){
        keyupform($('#userForm'));
        hchosen($('.chosen-select'));
//        searchMultiSelect($('#roleSelector'),{
//            width:550,
//            selectableFooter: "<div class='blue-bg label-default'>可选角色</div>",
//            selectionFooter: "<div class='blue-bg label-default'>已选角色</div>"
//        })
    });
</script>


<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
    <%--<meta charset="utf-8">--%>
    <%--<meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
    <%--<title>${command.id!=null?"编辑用户":"添加用户"}</title>--%>
    <%--<%@include file="/common/taglibs.jsp"%>--%>
<%--</head>--%>
<%--<body >--%>
<%--<div id="wrapper">--%>
    <%--<%@include file="../menu.jsp"%>--%>
    <%--<div id="page-wrapper" class="gray-bg dashbard-1">--%>
        <%--<%@include file="../header.jsp"%>--%>
        <%--<div class="row wrapper border-bottom white-bg page-heading">--%>
            <%--<div class="col-lg-10">--%>
                <%--<h3>${command.id!=null?"编辑用户":"添加用户"}</h3>--%>
                <%--<ol class="breadcrumb">--%>
                    <%--<li>--%>
                        <%--<a href="<c:url value="/html/home"/>">主页</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="<c:url value="/html/user/index"/>">用户列表</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<strong>${command.id!=null?"编辑用户":"添加用户"}</strong>--%>
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
                            <%--<h5>用户基本信息</h5>--%>
                            <%--<div class="ibox-tools">--%>
                                <%--<div><a href="<c:url value="/html/user/index"/>" class="btn btn-primary btn-xs">返回</a></div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="ibox-content">--%>
                            <%--<form class="form-horizontal m-t" id="userForm" method="post">--%>
                                <%--<input type="hidden" name="id" value="${command.id}">--%>
                                <%--<div class="form-group">--%>
                                    <%--<label class="col-sm-3 control-label">用户名：</label>--%>
                                    <%--<div class="col-sm-8">--%>
                                        <%--<input name="username" value="${command.username}" type="text" ${command.id!=null?"readonly":""} class="form-control" data-validate="username">--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--<c:if test="${command.id==null}">--%>
                                    <%--<div class="form-group">--%>
                                        <%--<label class="col-sm-3 control-label">密码：</label>--%>
                                        <%--<div class="col-sm-8">--%>
                                            <%--<input name="password"  type="password" class="form-control" data-validate="password" id="userPassword">--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                    <%--<div class="form-group">--%>
                                        <%--<label class="col-sm-3 control-label">确认密码：</label>--%>
                                        <%--<div class="col-sm-8">--%>
                                            <%--<input name="password" data-validate="passwordHit" data-hit="userPassword" type="password" class="form-control">--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</c:if>--%>
                                <%--<div class="form-group">--%>
                                    <%--<label class="col-sm-3 control-label">姓名：</label>--%>
                                    <%--<div class="col-sm-8">--%>
                                        <%--<input type="text" value="${command.nickName}" class="form-control" name="nickName" data-validate="required">--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="form-group">--%>
                                    <%--<label class="col-sm-3 control-label">电子邮件：</label>--%>
                                    <%--<div class="col-sm-8">--%>
                                        <%--<input type="text" value="${command.email}" class="form-control" name="email" data-validate="email">--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--&lt;%&ndash;<div class="form-group">&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;<label class="col-sm-3 control-label">默认角色：</label>&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;<div class="col-sm-8">&ndash;%&gt;--%>
                                        <%--&lt;%&ndash;<select class="chosen-select" style="width:350px;" tabindex="2" id="defaultRoleSelector" data-validate="required" name="roleId">&ndash;%&gt;--%>
                                        <%--&lt;%&ndash;</select>&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<div class="form-group">&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;<label class="col-sm-3 control-label">其他角色：</label>&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;<div class="col-sm-8">&ndash;%&gt;--%>
                                        <%--&lt;%&ndash;<select class="chosen-select form-control" multiple  tabindex="2" id="roleSelector">&ndash;%&gt;--%>
                                        <%--&lt;%&ndash;</select>&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                                <%--<div class="form-group">--%>
                                    <%--<label class="col-sm-3 control-label">选择角色：</label>--%>
                                    <%--<div class="col-sm-8">--%>
                                        <%--<select multiple="multiple" id="roleSelector" name="roles">--%>
                                        <%--</select>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="form-group">--%>
                                    <%--<div class="col-sm-4 col-sm-offset-3">--%>
                                        <%--<button class="btn btn-primary" type="button" onclick="saveModel();">保存内容</button>--%>
                                        <%--<button class="btn btn-white" type="button" onclick="reloadPage('html/user/index')">返回</button>--%>
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
<%--<script type="text/javascript" src="<c:url value="/resources/js/user/user.js"/>"></script>--%>
<%--<script type="text/javascript">--%>
    <%--changeMenu('menu-user');--%>
    <%--initComboData($("#roleSelector"),'html/role/list',{start:0,limit:20},'id','name');--%>
    <%--if(${command.id!=null}){--%>
        <%--var rs=[];--%>
        <%--<c:forEach var="r" items="${command.roles}">--%>
        <%--rs.push(${r.id});--%>
        <%--</c:forEach>--%>
        <%--$('#roleSelector').val(rs);--%>
    <%--}--%>

    <%--jQuery(function(){--%>
        <%--keyupform($('#userForm'));--%>
        <%--searchMultiSelect($('#roleSelector'),{--%>
            <%--width:550,--%>
            <%--selectableFooter: "<div class='blue-bg label-default'>可选角色</div>",--%>
            <%--selectionFooter: "<div class='blue-bg label-default'>已选角色</div>"--%>
        <%--})--%>
    <%--});--%>
<%--</script>--%>

<%--</body>--%>
<%--</html>--%>

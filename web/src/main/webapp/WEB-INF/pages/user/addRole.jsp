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
        <h3>${command.id!=null?"编辑角色":"添加角色"}</h3>
        <ol class="breadcrumb">
            <li>
                <a href="#">主页</a>
            </li>
            <li>
                <a href="#/role/index">角色列表</a>
            </li>
            <li>
                <strong>${command.id!=null?"编辑角色":"添加角色"}</strong>
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
                    <h5>角色信息</h5>
                    <div class="ibox-tools">
                        <div><a href="#/role/index"  class="btn btn-primary btn-xs">返回</a></div>
                    </div>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal m-t" id="userForm" method="post">
                        <input type="hidden" name="id" value="${command.id}">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">角色名称：</label>
                            <div class="col-sm-8">
                                <input name="name" value="${command.name}" type="text" class="form-control" data-validate="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">描述：</label>
                            <div class="col-sm-8">
                                <textarea  name="description" class="form-control" data-validate="required">${command.description}</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">权限分配：</label>
                            <div class="col-sm-8" style="border: 1px solid;height: 400px;overflow-y: scroll;">
                                <ul id="treeDemo" class="ztree"></ul>
                            </div>
                        </div>


                        <div class="form-group">
                            <div class="col-sm-4 col-sm-offset-3">
                                <button class="btn btn-primary" id="roleBtn" type="button" onclick="saveModel();">保存内容</button>
                                <%--<button class="btn btn-white" type="button" onclick="ajaxReloadPage('content-wrapper','html/role/index');">返回</button>--%>
                                <a href="#/role/index" class="btn btn-white" type="button" >返回</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="<c:url value="/resources/js/user/role.js"/>"></script>
<script type="text/javascript">
    var f=[];
    if(${command.id!=null}){
        <c:forEach var="ff" items="${command.features}">
        f.push(${ff.id});
        </c:forEach>
    }

    jQuery(function(){
        keyupform($('#userForm'));
        initZtree(f);

    });
</script>

<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
    <%--<meta charset="utf-8">--%>
    <%--<meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
    <%--<title>${command.id!=null?"编辑角色":"添加角色"}</title>--%>
    <%--<%@include file="/common/taglibs.jsp"%>--%>
<%--</head>--%>
<%--<body >--%>
<%--<div id="wrapper">--%>
    <%--<%@include file="../menu.jsp"%>--%>
    <%--<div id="page-wrapper" class="gray-bg dashbard-1">--%>
        <%--<%@include file="../header.jsp"%>--%>
        <%--<div class="row wrapper border-bottom white-bg page-heading">--%>
            <%--<div class="col-lg-10">--%>
                <%--<h3>${command.id!=null?"编辑角色":"添加角色"}</h3>--%>
                <%--<ol class="breadcrumb">--%>
                    <%--<li>--%>
                        <%--<a href="<c:url value="/html/home"/>">主页</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="<c:url value="/html/role/index"/>">角色列表</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<strong>${command.id!=null?"编辑角色":"添加角色"}</strong>--%>
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
                            <%--<h5>角色信息</h5>--%>
                            <%--<div class="ibox-tools">--%>
                                <%--<div><a href="<c:url value="/html/role/index"/>" class="btn btn-primary btn-xs">返回</a></div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="ibox-content">--%>
                            <%--<form class="form-horizontal m-t" id="userForm" method="post">--%>
                                <%--<input type="hidden" name="id" value="${command.id}">--%>
                                <%--<div class="form-group">--%>
                                    <%--<label class="col-sm-3 control-label">角色名称：</label>--%>
                                    <%--<div class="col-sm-8">--%>
                                        <%--<input name="name" value="${command.name}" type="text" class="form-control" data-validate="required">--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="form-group">--%>
                                    <%--<label class="col-sm-3 control-label">描述：</label>--%>
                                    <%--<div class="col-sm-8">--%>
                                        <%--<textarea  name="description" class="form-control" data-validate="required">${command.description}</textarea>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="form-group">--%>
                                    <%--<label class="col-sm-3 control-label">权限分配：</label>--%>
                                    <%--<div class="col-sm-8" style="border: 1px solid;height: 400px;overflow-y: scroll;">--%>
                                        <%--<ul id="treeDemo" class="ztree"></ul>--%>
                                    <%--</div>--%>
                                <%--</div>--%>


                                <%--<div class="form-group">--%>
                                    <%--<div class="col-sm-4 col-sm-offset-3">--%>
                                        <%--<button class="btn btn-primary" id="roleBtn" type="button" onclick="saveModel();">保存内容</button>--%>
                                        <%--<button class="btn btn-white" type="button" onclick="reloadPage('html/role/index')">返回</button>--%>
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
<%--<script type="text/javascript" src="<c:url value="/resources/js/user/role.js"/>"></script>--%>
<%--<script type="text/javascript">--%>
    <%--changeMenu('menu-user');--%>
    <%--var f=[];--%>
    <%--if(${command.id!=null}){--%>
        <%--<c:forEach var="ff" items="${command.features}">--%>
        <%--f.push(${ff.id});--%>
        <%--</c:forEach>--%>
    <%--}--%>

    <%--jQuery(function(){--%>
        <%--keyupform($('#userForm'));--%>
        <%--initZtree(f);--%>

    <%--});--%>
<%--</script>--%>

<%--</body>--%>
<%--</html>--%>

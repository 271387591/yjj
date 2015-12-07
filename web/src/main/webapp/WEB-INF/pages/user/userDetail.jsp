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
        <h3>用户</h3>
        <ol class="breadcrumb">
            <li>
                <a href="<c:url value="/html/home"/>">主页</a>
            </li>
            <li>
                <strong>用户详情</strong>
            </li>
        </ol>
    </div>
    <div class="col-lg-2 col-sm-12 col-xs-12 col-md-12">

    </div>
</div>
<div class="row">
    <div class="col-lg-12 col-sm-12 col-xs-12 col-md-12">
        <div class="wrapper wrapper-content animated fadeInUp">
            <div class="ibox">
                <div class="ibox-content">
                    <div class="row">
                        <div class="col-lg-12 col-sm-12 col-xs-12 col-md-12">
                            <div class="m-b-md m-b-sm m-b-lg m-b-xs">
                                <%--<a href="#user" onclick="ajaxReloadPage('content-wrapper','html/user/index');" class="btn btn-white btn-xs pull-right" style="margin-left: 2px">返回</a>--%>
                                <a href="#/user/index" class="btn btn-white btn-xs pull-right" style="margin-left: 2px">返回</a>
                                <a href="#/user/editUser/${command.id}"  class="btn btn-primary btn-xs pull-right ${oz:contains(userinfo.features,'updateUser')?'':'hidden'}">编辑用户</a>
                                <h2>用户(<span class="red">${command.username}</span>)详细信息</h2>
                            </div>
                            <dl class="dl-horizontal">
                                <dt>用户名：</dt>
                                <dd>
                                    <span>${command.username}</span>
                                </dd>
                            </dl>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-5 col-sm-5 col-xs-5 col-md-5">
                            <dl class="dl-horizontal">
                                <dt>姓名：</dt>
                                <dd>${command.nickName}</dd>
                                <dt>电子邮件：</dt>
                                <dd>${command.email}</dd>
                                <dt>性别：</dt>
                                <dd>${empty command.gender?'': command.gender eq "M"?"男":"女"}</dd>
                                <dt>出生年月：</dt>
                                <dd>${command.birth}</dd>
                            </dl>
                        </div>
                        <div class="col-lg-7" id="cluster_info">
                            <dl class="dl-horizontal">
                                <dt>手机号码：</dt>
                                <dd>${command.mobile}</dd>
                                <dt>联系地址：</dt>
                                <dd>${command.province}${command.city}${command.country}${command.address}</dd>
                                <dt>头像：</dt>
                                <dd class="project-people">
                                    <a href="project_detail.html">
                                        <img alt="image" class="img-circle" src="${command.portraitUrl}">
                                    </a>
                                </dd>
                            </dl>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <dl class="dl-horizontal">
                                <dt>用户角色：</dt>
                                <dd>
                                    <c:forEach var="r" items="${command.roles}" varStatus="status">
                                        ${r.name}<c:if test="${!status.last}">,</c:if>
                                    </c:forEach>
                                </dd>
                            </dl>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<script type="text/javascript" src="<c:url value="/resources/js/user/user.js"/>"></script>

<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
    <%--<meta charset="utf-8">--%>
    <%--<meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
    <%--<title>用户详情</title>--%>
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
                        <%--<strong>用户详情</strong>--%>
                    <%--</li>--%>
                <%--</ol>--%>
            <%--</div>--%>
            <%--<div class="col-lg-2">--%>

            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="row">--%>
            <%--<div class="col-lg-12">--%>
                <%--<div class="wrapper wrapper-content animated fadeInUp">--%>
                    <%--<div class="ibox">--%>
                        <%--<div class="ibox-content">--%>
                            <%--<div class="row">--%>
                                <%--<div class="col-lg-12">--%>
                                    <%--<div class="m-b-md">--%>
                                        <%--<a href="<c:url value="/html/user/index"/>" class="btn btn-white btn-xs pull-right" style="margin-left: 2px">返回</a>--%>
                                        <%--<a href="javascript:void(0);" onclick="edit(${command.id})" class="btn btn-primary btn-xs pull-right ${oz:contains(userinfo.features,'updateUser')?'':'hidden'}">编辑用户</a>--%>
                                        <%--<h2>用户(<span class="red">${command.username}</span>)详细信息</h2>--%>
                                    <%--</div>--%>
                                    <%--<dl class="dl-horizontal">--%>
                                        <%--<dt>用户名：</dt>--%>
                                        <%--<dd>--%>
                                            <%--<span>${command.username}</span>--%>
                                        <%--</dd>--%>
                                    <%--</dl>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="row">--%>
                                <%--<div class="col-lg-5">--%>
                                    <%--<dl class="dl-horizontal">--%>
                                        <%--<dt>姓名：</dt>--%>
                                        <%--<dd>${command.nickName}</dd>--%>
                                        <%--<dt>电子邮件：</dt>--%>
                                        <%--<dd>${command.email}</dd>--%>
                                        <%--<dt>性别：</dt>--%>
                                        <%--<dd>${empty command.gender?'': command.gender eq "M"?"男":"女"}</dd>--%>
                                        <%--<dt>出生年月：</dt>--%>
                                        <%--<dd>${command.birth}</dd>--%>
                                    <%--</dl>--%>
                                <%--</div>--%>
                                <%--<div class="col-lg-7" id="cluster_info">--%>
                                    <%--<dl class="dl-horizontal">--%>
                                        <%--<dt>手机号码：</dt>--%>
                                        <%--<dd>${command.mobile}</dd>--%>
                                        <%--<dt>联系地址：</dt>--%>
                                        <%--<dd>${command.province}${command.city}${command.country}${command.address}</dd>--%>
                                        <%--<dt>头像：</dt>--%>
                                        <%--<dd class="project-people">--%>
                                            <%--<a href="project_detail.html">--%>
                                                <%--<img alt="image" class="img-circle" src="${command.portraitUrl}">--%>
                                            <%--</a>--%>
                                        <%--</dd>--%>
                                    <%--</dl>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="row">--%>
                                <%--<div class="col-lg-12">--%>
                                    <%--<dl class="dl-horizontal">--%>
                                        <%--<dt>用户角色：</dt>--%>
                                        <%--<dd>--%>
                                            <%--<c:forEach var="r" items="${command.roles}" varStatus="status">--%>
                                                <%--${r.name}<c:if test="${!status.last}">,</c:if>--%>
                                            <%--</c:forEach>--%>
                                        <%--</dd>--%>
                                    <%--</dl>--%>
                                <%--</div>--%>
                            <%--</div>--%>
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
    <%--jQuery(function(){--%>

    <%--})--%>

<%--</script>--%>

<%--</body>--%>
<%--</html>--%>

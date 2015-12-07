<%--
  Created by IntelliJ IDEA.
  User: lihao1
  Date: 6/4/15
  Time: 21:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar-default navbar-static-side" role="navigation">
    <div class="sidebar-collapse">
        <ul class="nav" id="side-menu">
            <li class="nav-header">
                <div class="dropdown profile-element"> <span>
                             </span>
                    <a data-toggle="dropdown" class="dropdown-toggle" href="<c:url value="/html/home"/>">
                                <span class="clear"> <span class="block m-t-xs"> <strong class="font-bold">${userinfo.username}</strong>
                             </span> <span class="text-muted text-xs block">${userinfo.roleDisplayName} <b class="caret"></b></span> </span>
                    </a>
                    <ul class="dropdown-menu animated fadeInRight m-t-xs">

                        <li><a data-toggle="modal" href="#changePasswordModel">修改密码</a>
                        </li>
                        <li class="divider"></li>
                        <li><a onclick="logout()" href="javascript:void(0)">安全退出</a>
                        </li>
                    </ul>
                </div>
            </li>
            <c:if test="${userinfo.roleName eq 'ROLE_ADMIN'}">
                <li class="hidden">
                    <a href="#"><i class="fa fa-dashboard"></i> <span class="nav-label">系统配置</span> </a>
                </li>
                <li id="menu-user" class="active">
                    <a href="#user/index"><i class="fa fa-user"></i> <span class="nav-label">用户管理</span></a>
                </li>
                <li id="menu-advert" class="hidden">
                    <a href="#advert/index"><i class="fa fa-rss "></i> <span class="nav-label">广告管理</span></a>
                </li>
                <li id="menu-news" class="hidden">
                    <a href="#news/index"><i class="fa fa-hacker-news "></i> <span class="nav-label">新闻管理</span></a>
                </li>
                <li id="menu-notice" class="hidden">
                    <a href="#notice/index"><i class="fa fa-recycle "></i> <span class="nav-label">公告管理</span></a>
                </li>
                <li id="menu-friend">
                    <a href="#friend/index"><i class="fa fa-link"></i> <span class="nav-label">友情链接</span></a>
                </li>
            </c:if>
            <c:if test="${userinfo.roleName eq 'ROLE_USER'}">
                <li id="menu-org">
                    <a href="#assess/org"><i class="fa fa-link"></i> <span class="nav-label">部门机构</span></a>
                </li>
                <li id="menu-question">
                    <a href="#assess/question"><i class="fa fa-link"></i> <span class="nav-label">方案细则</span></a>
                </li>
                <li id="menu-question">
                    <a href="#assess/survey"><i class="fa fa-link"></i> <span class="nav-label">检查方案</span></a>
                </li>
            </c:if>
            <c:if test="${userinfo.roleName eq 'ROLE_TENANT'}">
                <li id="menu-org">
                    <a href="#leader/lsurvey"><i class="fa fa-link"></i> <span class="nav-label">方案审核</span></a>
                </li>
            </c:if>




        </ul>

    </div>
</nav>
<script type="text/javascript">


</script>



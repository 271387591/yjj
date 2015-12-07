<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta charset="utf-8" />
    <title>后台登陆</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9;Chrome=1" />
    <title>office</title>
    <%@include file="/common/common.jsp"%>

</head>
<body class="gray-bg">
<div class="middle-box text-center loginscreen  animated fadeInDown">
    <div>
        <div>

            <h1 class="logo-name">H+</h1>

        </div>
        <h3>网站后台管理</h3>

        <form class="m-t" role="form" action="j_security_check" method="post">
            <input type="hidden" name="platform" value="PC"/>
            <div class="form-group">
                <input type="text" class="form-control" placeholder="用户名" name="username">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" placeholder="密码" name="password">
            </div>
            <button type="submit" class="btn btn-primary block full-width m-b">登 录</button>

            <p class="text-muted text-center">
                <c:if test="${param.error == true}">
                    <font color="red">用户名或密码错误</font>
                </c:if>
            </p>
        </form>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function (e) {
        $('input[name=username]').bind('keydown', function (e) {
            var key = e.which;
            if (key == 13) {
                $('#loginForm').submit();
            }
        });
        $('input[name=password]').bind('keydown', function (e) {
            var key = e.which;
            if (key == 13) {
                $('#loginForm').submit();
            }
        });
        $("#username").focus();
    });
</script>
</body>
</html>

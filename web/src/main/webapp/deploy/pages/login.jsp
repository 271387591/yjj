<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta charset="utf-8" />
    <title>后台登陆</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>office</title>

    <link href="<c:url value="/runtime/css/runtime.css"/> " rel="stylesheet" />

    <!--[if lt IE 9]-->
    <script src="<c:url value="/resources/lib/ie/html5shiv.js"/>"></script>
    <script src="<c:url value="/resources/lib/ie/respond.min.js"/>"></script>
    <!--[endif]-->
    <!--[if !IE]> -->
    <script src="<c:url value="/resources/lib/jquery/jquery-2.0.3.min.js"/>"></script>
     <!--<![endif]-->

    <!--[if IE]-->
    <script src="<c:url value="/resources/lib/jquery/jquery-1.10.2.min.js"/>"></script>
    <!--[endif]-->

</head>
<body style="background:#2a2f33;">
<div class="login-box">
    <div class="logo logo-login" id="logo">
    </div>
    <form action="<c:url value="/j_security_check"/>" method="post" id="loginForm">
        <input type="hidden" name="platform" value="PC"/>
        <div class="form">
            <div class="username">
                <input placeholder="用户名" type="text" name='username' value="${sessionScope['SPRING_SECURITY_LAST_USERNAME']}" />
            </div>
            <div class="password">
                <input class="password" placeholder="密码" type="password" name="password" />
            </div>
            <div>
                <c:if test="${param.error == true}">
                    <font color="red">用户名或密码错误</font>
                </c:if>
            </div>
            <button class="btn btn-login" type="submit">登录</button>
        </div>
    </form>
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
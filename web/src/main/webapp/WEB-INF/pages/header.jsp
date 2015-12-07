<%--
  Created by IntelliJ IDEA.
  User: lihao1
  Date: 6/4/15
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>--%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%

%>
<div class="row border-bottom">
    <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="javascript:void(0)"><i class="fa fa-bars"></i> </a>
        </div>
        <ul class="nav navbar-top-links navbar-right">
            <li>
                <span class="m-r-sm text-muted welcome-message">网站后台管理</span>
            </li>

            <li>
                <a href="javascript:void(0);" onclick="logout();">
                    <i class="fa fa-sign-out"></i> 退出
                </a>
            </li>
        </ul>

    </nav>
</div>

<div id="changePasswordModel" class="modal fade" role="dialog" aria-labelledby="gridSystemModalLabel" aria-hidden="true">
    <div class="modal-dialog width-35">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="gridSystemModalLabel"><i class="icon-cog"></i>修改密码</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <form class="form-horizontal" id="updatePwdForm">
                        <div class="form-group">
                            <label for="inputPassword" class="control-label">新密码:</label>
                            <div>
                                <input type="password" class="form-control" data-validate="password" id="inputPassword" placeholder="新密码">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputPasswordHit" class="control-label">确认密码:</label>
                            <div>
                                <input type="password" class="form-control" data-validate="passwordHit" data-hit="inputPassword" id="inputPasswordHit" placeholder="确认密码">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" id="changePasswordBtn" class="btn btn-primary" onclick="changeAdminPassword()">确定</button>
                <button type="button" class="btn" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var globalRes ={
        userId:'${userinfo.id}',
        username:'${userinfo.username}',
        roleId:'${userinfo.roleId}',
        roleName:'${userinfo.roleName}',
        features:'${userinfo.features}'
    };
    var accessRes = {
        <c:forEach var="feature" items="${userinfo.features}" varStatus="status">
        ${feature}: true<c:if test="${!status.last}">,</c:if>
        </c:forEach>
    };
    $('#changePasswordModel').on('show.bs.modal', function (event) {
        clearForm($('#updatePwdForm'));
    });
    function changeAdminPassword(){
        if(!checkForm($('#updatePwdForm'))){
            return;
        }
        var val=$('#inputPassword').val();

        $('#changePasswordModel').modal('hide');
        var result=requestStringData('html/user/security/changeAdminPwd',{password:val});
        if(result.success){
            alertSuccess('操作成功');
        }else{
            alertError(result.message);
        }
    }
    function logout(){
        createCustomConfirm2({},'你确定要退出？',['确定','取消'],function(){
            window.location.href='<c:url value="/logout.jsp"/>';
        });
    }

</script>

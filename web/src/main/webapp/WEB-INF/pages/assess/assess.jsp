<%--
  Created by IntelliJ IDEA.
  User: lihao1
  Date: 7/31/15
  Time: 19:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=9;Chrome=1" />
    <title></title>
    <%@include file="/common/common.jsp"%>
    <%
        String gloableImgUrl=System.getProperty("img.url");
        session.setAttribute("imgUrl",gloableImgUrl);
    %>
</head>
<body style="background:#f3f3f4">
<div class="modal inmodal" id="pingjrwsaveModal1" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog width-20">
        <div class="modal-content animated fadeIn">
            <div class="modal-header" style="padding: 2px 1px">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                </button>
                <h6 class="modal-title" style="font-size: 17px">诚信评级</h6>
            </div>
            <div class="modal-body col-xs-12" style="padding: 1px">
                <form class="form-horizontal m-t" id="saveModalForm" method="post">
                    <input type="hidden" name="task_id">
                    <div class="col-xs-4 col-xs-offset-2">
                        <div class="form-group">
                            <label><input class="i-checks" type="radio"  value="A"  name="progress">A</label>
                        </div>
                        <div class="form-group">
                            <label><input class="i-checks" type="radio"  value="B"  name="progress">B</label>
                        </div>
                    </div>
                    <div class="col-xs-4 col-xs-offset-2">
                        <div class="form-group">
                            <label><input class="i-checks" type="radio"  value="C"  name="progress">C</label>
                        </div>
                        <div class="form-group">
                            <label><input class="i-checks" type="radio"  value="D"  name="progress">D</label>
                        </div>
                    </div>
                    <div class="col-xs-4 col-xs-offset-2">
                        <div class="form-group">
                            <label><input class="i-checks" type="radio"  value="E"  name="progress">E</label>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="pingjrwsaveModalSaveBtn">确定</button>
            </div>
        </div>
    </div>
</div>

<div class="modal inmodal" id="logoutModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog width-20">
        <div class="modal-content animated bounceInRight">
            <div class="modal-header" style="padding: 5px 15px">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                </button>
                <h6 class="modal-title" style="font-size: 16px">退出系统</h6>
            </div>
            <div class="modal-body" style="padding: 2px 3px 3px 30px;">
                <h4>您确定要退出？</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="logout();">确定</button>
            </div>
        </div>
    </div>
</div>

<div class="modal inmodal" id="changePasswordModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog width-20" style="margin:130px auto;">
        <div class="modal-content animated fadeIn">
            <div class="modal-header" style="padding: 2px 1px">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
                </button>
                <h6 class="modal-title" style="font-size: 17px">修改密码</h6>
            </div>
            <div class="modal-body col-sm-12 col-lg-12 col-md-12 col-xs-12" style="padding: 1px">
                <form class="form-horizontal m-t" id="changePasswordModalForm" method="post">
                    <div class="form-group" style="margin-left: 0;margin-right: 0">
                        <label class="control-label">原密码:</label>
                        <div>
                            <input type="password" name="oldpassword" class="form-control" data-validate="password" placeholder="原密码">
                        </div>
                    </div>
                    <div class="form-group" style="margin-left: 0;margin-right: 0">
                        <label class="control-label">新密码:</label>
                        <div>
                            <input type="password" name="password" class="form-control" id="userPassword" data-validate="password" placeholder="新密码">
                        </div>
                    </div>
                    <div class="form-group" style="margin-left: 0;margin-right: 0">
                        <label class="control-label">确认密码:</label>
                        <div>
                            <input name="password1" data-validate="passwordHit" data-hit="userPassword" type="password" class="form-control">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button"  class="btn btn-white" data-dismiss="modal">关闭</button>
                <button type="button" onclick="changePwd();" class="btn btn-primary">确定</button>
            </div>
        </div>
    </div>
</div>



<div id="wrapper">
    <div class="row border-bottom">
        <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom:0">
            <div class="navbar-header" style="background: #6693cc">
                <button aria-controls="navbar" aria-expanded="false" data-target="#navbar" data-toggle="collapse" class="navbar-toggle collapsed" type="button">
                    <i class="fa fa-reorder"></i>
                </button>
                <a href="<c:url value="/html/web/index"/>" class="navbar-brand" style="color: white;margin: 0 25px"><i class="fa fa-arrow-circle-o-left">回到主页</i></a>

            </div>
            <div class="navbar-collapse collapse"  style="background: #6693cc;color: white" id="navbar">
                <ul class="nav navbar-nav " id="side-menu">

                    <li>
                        <a class="navbar-link" style="color: #fff;"   href="#assess/org"  > 部门机构 </a>
                    </li>
                    <li >
                        <a style="color: #fff"   href="#assess/survey"> 考核方案 </a>
                    </li>
                </ul>
                <ul class="nav navbar-top-links navbar-right">
                    <li>
                        <a style="color: white;padding: 10px 15px" href="#changePasswordModal" data-toggle="modal">
                            <i class="fa fa-user"></i>修改密码
                        </a>
                    </li>
                    <li>
                        <a style="color: white;padding: 10px 15px" href="#logoutModal" data-toggle="modal">
                            <i class="fa fa-sign-out"></i> 退出
                        </a>
                    </li>
                </ul>

            </div>
        </nav>
    </div>
    <div  class="gray-bg dashbard-1">
        <div class="wrapper wrapper-content animated fadeInRight" style="padding: 5px 10px 40px;">
            <div class="row" id="wrapper-content">


            </div>

        </div>


    </div>
</div>

<script type="text/javascript">
    function logout(){
        window.location.href='<c:url value="/logout.jsp"/>';
    }
    $(window).load(function(event){
        var hash = window.location.hash;
        if(!hash)return;
        ajaxReloadPage("wrapper-content","html/"+hash.substr(1));
        if($('ul[id=side-menu] li a[href="'+hash+'"]').size()>0){
            $('ul[id=side-menu] li').removeClass('active');
            $('ul[id=side-menu] li a[href="'+hash+'"]').parents('li').last().addClass('active');
        }
//        $('ul[id=side-menu] li').removeClass('active');
//        $('ul[id=side-menu] li a[href="'+hash+'"]').parents('li').last().addClass('active');

    });
    $(window).bind('hashchange',function(){
        var hash = window.location.hash;
        if(!hash)return;
        ajaxReloadPage("wrapper-content","html/"+hash.substr(1));
        if($('ul[id=side-menu] li a[href="'+hash+'"]').size()>0){
            $('ul[id=side-menu] li').removeClass('active');
            $('ul[id=side-menu] li a[href="'+hash+'"]').parents('li').last().addClass('active');
        }
    });
    function changePwd(){
        var form=$('#changePasswordModalForm');
        if(checkForm(form)){
            var old=form.find('input[name=oldpassword]').val();
            var pwd=form.find('input[name=password]').val();
            var result=requestStringData('html/assess/changeUserPwd',{oldpassword:old,password:pwd});
            if(result.success){
                setTimeout(function(){
                    alertSuccess('修改成功');
                    $('#changePasswordModal').modal('hide');
                },300);
            }else{
                alertError('原密码验证失败');
            }
        }
    }
    $('#changePasswordModal').on('shown.bs.modal',function(){
        clearForm($('#changePasswordModalForm'));
    });

</script>
</body>
</html>

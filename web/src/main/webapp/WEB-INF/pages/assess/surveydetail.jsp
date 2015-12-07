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
        <h3>${command.name}</h3>
    </div>
    <div class="col-lg-2">

    </div>
</div>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-lg-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>基本信息</h5>
                    <div class="ibox-tools">
                        <div><a href="#assess/survey"  class="btn btn-primary btn-xs">返回</a></div>
                    </div>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal m-t" id="userForm" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="id" value="${command.id}">
                        <div class="form-group">
                            <label class="col-sm-2 col-lg-2 col-md-2 col-xs-2 control-label">方案名称：</label>
                            <div class="col-sm-8 col-lg-8 col-xs-8 col-md-8">
                                <input disabled="disabled" name="name" value="${command.name}" type="text"  class="form-control" data-validate="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-lg-2 col-md-2 col-xs-2 control-label">审核状态：</label>
                            <div class="col-sm-8 col-lg-8 col-xs-8 col-md-8">
                                <input disabled="disabled" name="name" value="${command.verify==0?'未审核':(command.verify==1?'待审核':(command.verify==2?'审核通过':'审核未通过'))}" type="text"  class="form-control" data-validate="required">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 col-lg-2 col-md-2 col-xs-2 control-label">参检机构：</label>
                            <div class="col-sm-8 col-lg-8 col-xs-8 col-md-8">
                                <table class="table table-bordered">
                                    <thead>
                                    <tr class="tr-tit">
                                        <th class="text-center">机构名称</th>
                                    </tr>
                                    </thead>
                                    <tbody>


                                    <c:forEach var="an" items="${command.orgs}">
                                        <tr class="tr-data" data-anid="${an.id}">
                                            <td >
                                                    ${an.name}
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-lg-2 col-md-2 col-xs-2 control-label">检查内容：</label>
                            <div class="col-sm-8 col-lg-8 col-xs-8 col-md-8">
                                <table class="table table-bordered" id="answerTable">
                                    <thead>
                                    <tr class="tr-tit">
                                        <th class="text-center">细则名称</th>
                                        <th class="text-center">分数</th>
                                    </tr>
                                    </thead>
                                    <tbody>


                                    <c:forEach var="an" items="${command.questions}">
                                        <tr class="tr-data" data-anid="${an.id}">
                                            <td >
                                                    ${an.name}
                                            </td>
                                            <td >
                                                    ${an.rule}
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>


                        <div class="form-group">
                            <div class="col-sm-4 col-lg-4 col-md-4 col-xs-4 col-lg-offset-3 col-sm-offset-3 col-md-offset-3 col-xs-offset-3">
                                <a href="#assess/survey"  class="btn btn-white" type="button">返回</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>




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
                    <h5>审核流程</h5>
                    <div class="ibox-tools">
                        <div><a href="#leader/lsurvey"  class="btn btn-primary btn-xs">返回</a></div>
                    </div>
                </div>
                <div class="ibox-content timeline">

                    <div class="timeline-item">
                        <div class="row">
                            <div class="col-xs-3 date" style="width: 200px">
                                <i class="fa fa-briefcase"></i>
                                <fmt:formatDate value="${command.lastUpdateDate}" pattern="MM月dd日 HH:mm"></fmt:formatDate>
                                <br>
                            </div>
                            <div class="col-xs-7 content no-top-border">
                                <p class="m-b-xs"><strong>提交审核</strong>
                                </p>
                            </div>
                        </div>
                    </div>
                    <c:forEach items="${command.audits}" var="a">
                        <div class="timeline-item">
                            <div class="row">
                                <div class="col-xs-3 date" style="width: 200px">
                                    <i class="fa fa-briefcase"></i>
                                    <fmt:formatDate value="${command.lastUpdateDate}" pattern="MM月dd日 HH:mm"></fmt:formatDate>
                                    <br>
                                </div>
                                <div class="col-xs-7 content no-top-border">
                                    <p class="m-b-xs"><strong>${a.verify==0?'未审核':(a.verify==1?'待审核':(a.verify==2?'审核通过':'审核未通过'))}</strong>
                                    </p>
                                    <p>
                                        ${a.remark}
                                    </p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>




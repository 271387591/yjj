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
        <h3>方案细则</h3>
    </div>
    <div class="col-lg-2">

    </div>
</div>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-lg-12">
            <div class="ibox">
                <div class="ibox-title">
                    <h5>方案细则列表</h5>
                    <div class="ibox-tools">
                        <div><a href="#assess/addquestion"  class="btn btn-primary btn-xs ">添加</a></div>
                    </div>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal" id="userSearch">
                        <div class="col-sm-4 col-lg-4 col-md-4 col-xs-4">
                            <div class="form-group">
                                <label class="col-sm-4 col-lg-4 col-md-4 col-xs-4 control-label">名称</label>
                                <div class="col-sm-8 col-lg-8 col-xs-8 col-md-8">
                                    <input type="text" name="user.Q_name_LK" class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4 col-lg-4 col-md-4 col-xs-4 col-sm-offset-2">
                                <button onclick="searchForm($('#userSearch'),'user',listTable);" class="btn btn-primary btn-sm" id="searchBtn" type="button" >搜索</button>
                                <button onclick="clearSearchForm($('#userSearch'),listTable);" class="btn btn-white btn-sm" id="clearnBtn" type="button" >清空</button>
                            </div>
                        </div>
                    </form>

                    <table class="table table-bordered table-hover" id="pubTable" >
                        <thead>
                        <tr>
                            <th>名称</th>
                            <th>分数</th>
                            <th style="width: 150px">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                    <div class="row">
                        <div id="pubTablepager" class="paging"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="<c:url value="/resources/js/question.js"/>"></script>
<script type="text/javascript">
    jQuery(function(){
        listTable();
    });
</script>




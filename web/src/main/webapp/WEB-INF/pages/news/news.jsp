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
        <h3>新闻管理</h3>
    </div>
    <div class="col-lg-2">

    </div>
</div>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12 col-lg-12 col-md-12 col-xs-12">
            <div class="ibox">
                <div class="ibox-title">
                    <h5>新闻列表</h5>
                    <div class="ibox-tools">
                        <div><a href="#news/add"  class="btn btn-primary btn-xs ">添加</a></div>
                    </div>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal" id="userSearch">
                        <div class="col-sm-4 col-lg-4 col-md-4 col-xs-4">
                            <div class="form-group">
                                <label class="col-sm-4 col-lg-4 col-md-4 col-xs-4 control-label">标题</label>
                                <div class="col-sm-8 col-lg-8 col-xs-8 col-md-8">
                                    <input type="text" name="user.Q_title_LK" class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-4 col-lg-4 col-md-4 col-xs-4">
                            <div class="form-group">
                                <label class="col-sm-4 col-lg-4 col-md-4 col-xs-4 control-label">发布单位</label>
                                <div class="col-sm-8 col-lg-8 col-xs-8 col-md-8">
                                    <input type="text" name="user.Q_pubUnit_LK" class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4 col-lg-4 col-md-4 col-xs-4 col-sm-offset-2">
                                <button class="btn btn-primary" id="searchBtn" type="button" >搜索</button>
                                <button class="btn btn-white" id="clearnBtn" type="button" >清空</button>
                            </div>
                        </div>
                    </form>
                    <div class="panel blank-panel">

                        <div class="panel-heading">
                            <div class="panel-options">
                                <ul id="orgTab" class="nav nav-tabs">
                                    <li class="active" id="pubTab"><a data-toggle="tab" href="#tab-1">已发布</a>
                                    </li>
                                    <li class="" id="npubTab"><a data-toggle="tab" href="#tab-2">未发布</a>
                                    </li>
                                </ul>
                            </div>
                        </div>

                        <div class="panel-body">

                            <div class="tab-content">
                                <div id="tab-1" class="tab-pane active">
                                    <table class="table table-bordered table-hover" id="pubTable" >
                                        <thead>
                                        <tr>
                                            <th>标题</th>
                                            <th>发布单位</th>
                                            <th>发布时间</th>
                                            <th>阅读次数</th>
                                            <th></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                    <div class="row">
                                        <div id="pubTablepager" class="paging"></div>
                                    </div>
                                </div>
                                <div id="tab-2" class="tab-pane">
                                    <table class="table table-bordered table-hover" id="noPubTable" >
                                        <thead>
                                        <tr>
                                            <th>标题</th>
                                            <th>发布单位</th>
                                            <th>创建时间</th>
                                            <th></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                    <div class="row">
                                        <div id="noPubTablepager" class="paging"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript" src="<c:url value="/resources/js/model/news.js"/>"></script>
<script type="text/javascript">
    jQuery(function(){
        listTable();
        $('#npubTab a').click(function (e) {
            e.preventDefault()
            $(this).tab('show');
            listNpTable();
        });
        $('#pubTab a').click(function (e) {
            e.preventDefault()
            $(this).tab('show');
            listTable();
        });
        $('#searchBtn').click(function(){
            if($('#pubTab').hasClass('active')){
                searchForm($('#userSearch'),'user',listTable);
            }else{
                searchForm($('#userSearch'),'user',listNpTable)
            }
        });
        $('#clearnBtn').click(function(){
            if($('#pubTab').hasClass('active')){
                clearSearchForm($('#userSearch'),listTable);
            }else{
                clearSearchForm($('#userSearch'),listNpTable)
            }
        });

    });
</script>




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
        <h3>考核方案</h3>
    </div>
    <div class="col-lg-2">

    </div>
</div>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-lg-12">
            <div class="ibox">
                <div class="ibox-title">
                    <h5>考核方案列表</h5>

                </div>
                <div class="ibox-content">
                    <form class="form-horizontal" id="userSearch">
                        <div class="col-sm-4 col-lg-4 col-md-4 col-xs-4">
                            <div class="form-group">
                                <label class="col-sm-4 col-lg-4 col-md-4 col-xs-4 control-label">方案名称</label>
                                <div class="col-sm-8 col-lg-8 col-md-8 col-xs-8">
                                    <input type="text" name="user.Q_s.name_LK" class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-4 col-lg-4 col-md-4 col-xs-4">
                            <div class="form-group">
                                <label class="col-sm-4 col-lg-4 col-md-4 col-xs-4 control-label">提交人</label>
                                <div class="col-sm-8 col-lg-8 col-md-8 col-xs-8">
                                    <input type="text" name="user.Q_u.nickName_LK" class="form-control">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-4 col-lg-4 col-md-4 col-xs-4 col-sm-offset-2">
                                <button id="searchBtn"  class="btn btn-primary btn-sm" id="searchBtn" type="button" >搜索</button>
                                <button id="clearnBtn"  class="btn btn-white btn-sm" id="clearnBtn" type="button" >清空</button>
                            </div>
                        </div>
                    </form>
                    <div class="panel blank-panel">

                        <div class="panel-heading">
                            <div class="panel-options">
                                <ul id="orgTab" class="nav nav-tabs">
                                    <li class="active" id="npubTab"><a data-toggle="tab" href="#tab-2">待审核</a>
                                    </li>
                                    <li class="" id="ypubTab"><a data-toggle="tab" href="#tab-3">审核通过</a>
                                    </li>
                                    <li class="" id="ynpubTab"><a data-toggle="tab" href="#tab-4">审核未通过</a>
                                    </li>

                                </ul>
                            </div>
                        </div>

                        <div class="panel-body">

                            <div class="tab-content">
                                <div id="tab-2" class="tab-pane active">
                                    <table class="table table-bordered table-hover" id="noPubTable" >
                                        <thead>
                                        <tr>
                                            <th>方案名称</th>
                                            <th>提交人</th>
                                            <th>提交时间</th>
                                            <th style="width: 150px">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                    <div class="row">
                                        <div id="noPubTablepager" class="paging"></div>
                                    </div>
                                </div>
                                <div id="tab-3" class="tab-pane">
                                    <table class="table table-bordered table-hover" id="yPubTable" >
                                        <thead>
                                        <tr>
                                            <th>方案名称</th>
                                            <th>提交人</th>
                                            <th>审核时间</th>
                                            <th style="width: 150px">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                    <div class="row">
                                        <div id="yPubTablepager" class="paging"></div>
                                    </div>
                                </div>
                                <div id="tab-4" class="tab-pane">
                                    <table class="table table-bordered table-hover" id="ynPubTable" >
                                        <thead>
                                        <tr>
                                            <th>方案名称</th>
                                            <th>提交人</th>
                                            <th>审核时间</th>
                                            <th style="width: 150px">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                    <div class="row">
                                        <div id="ynPubTablepager" class="paging"></div>
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

<script type="text/javascript" src="<c:url value="/resources/js/lsurvey.js"/>"></script>
<script type="text/javascript">
    jQuery(function(){
        listNoTable();
        $('#npubTab a').click(function (e) {
            e.preventDefault()
            $(this).tab('show');
            listNoTable();
        });
        $('#ypubTab a').click(function (e) {
            e.preventDefault()
            $(this).tab('show');
            listYTable();
        });
        $('#ynpubTab a').click(function (e) {
            e.preventDefault()
            $(this).tab('show');
            listYNTable();
        });

        $('#searchBtn').click(function(){
            if($('#npubTab').hasClass('active')){
                searchForm($('#userSearch'),'user',listNoTable);
            }else if($('#ypubTab').hasClass('active')){
                searchForm($('#userSearch'),'user',listYTable)
            }else{
                searchForm($('#userSearch'),'user',listYNTable)
            }
        });
        $('#clearnBtn').click(function(){
            if($('#npubTab').hasClass('active')){
                clearSearchForm($('#userSearch'),listNoTable);
            }else if($('#ypubTab').hasClass('active')){
                clearSearchForm($('#userSearch'),listYTable)
            }else{
                clearSearchForm($('#userSearch'),listYNTable)
            }
        });

    });
</script>




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
        <h3>部门机构</h3>
    </div>
    <div class="col-lg-2">

    </div>
</div>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-lg-12">
            <div class="ibox">
                <div class="ibox-title">
                    <h5>部门机构列表</h5>
                    <div class="ibox-tools">
                        <div><a href="#assess/addorg"  class="btn btn-primary btn-xs ">新办</a></div>
                    </div>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal" id="userSearch">
                        <div class="col-sm-4 col-lg-4 col-md-4 col-xs-4">
                            <div class="form-group">
                                <label class="col-sm-4 col-lg-4 col-md-4 col-xs-4 control-label">机构编号</label>
                                <div class="col-sm-8 col-lg-8 col-xs-8 col-md-8">
                                    <input type="text" name="user.Q_orgNo_LK" class="form-control">
                                </div>
                            </div>
                        </div>
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
                            <th>机构编号</th>
                            <th>名称</th>
                            <th>地址</th>
                            <th>法定代表人</th>
                            <th>备注</th>
                            <th>类型</th>
                            <th>许可证编号</th>
                            <th>发证机关</th>
                            <th>诚信等级</th>
                            <th>发证日期</th>
                            <th>过期日期</th>
                            <th style="width: 90px">操作</th>
                            <th style="width: 120px">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                    <div class="row">
                        <div class="pull-left" style="margin-left: 15px">
                            <a target="_blank" href="<c:url value="/resources/tp/orgtemplate.xls"/>" class="btn btn-primary btn-xs"   >Excel模板下载</a>
                            <a target="_blank" href="<c:url value="/resources/tp/sm.html"/>" class="btn btn-primary btn-xs" >模板说明</a>
                            <a href="#assess/imorg" class="btn btn-primary btn-xs" >导入Excel</a>
                        </div>
                        <div id="pubTablepager" class="paging"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
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
<div id="yxorgModel" class="modal fade" role="dialog" aria-labelledby="gridSystemModalLabel" aria-hidden="true">
    <div class="modal-dialog width-35">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><i class="icon-cog"></i>延续</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <form class="form-horizontal">
                        <input type="hidden" name="id">
                        <div class="form-group">
                            <label  class="control-label">发证日期:</label>
                            <div>
                                <input name="startDate" type="text" readonly class="form-control" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label  class="control-label">过期日期:</label>
                            <div>
                                <input type="text" name="endDate" class="form-control"  onclick="laydate({format: 'YYYY-MM-DD'})" >
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button"  class="btn btn-primary" onclick="sbyxorg()">确定</button>
                <button type="button" class="btn" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript" src="<c:url value="/resources/js/org.js"/>"></script>
<script type="text/javascript">
    jQuery(function(){
        listTable();
        $('#pingjrwsaveModal1').find('.i-checks').iCheck({
            checkboxClass: 'icheckbox_square-green',
            radioClass: 'iradio_square-green'
        });
    });
</script>




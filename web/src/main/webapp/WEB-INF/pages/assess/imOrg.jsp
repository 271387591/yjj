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
    <div class="col-sm-10 col-lg-10 col-md-10 col-xs-10">
        <h3>Excel导入</h3>
        <ol class="breadcrumb">
            <li>
                <a href="#assess/org">机构列表</a>
            </li>
            <li>
                <strong>Excel导入</strong>
            </li>
        </ol>
    </div>
    <div class="col-xs-2">

    </div>
</div>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12 col-lg-12 col-md-12 col-xs-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <div class="ibox-tools">
                        <div><a href="#assess/org"  class="btn btn-primary btn-xs">返回</a></div>
                    </div>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal m-t" id="userForm" method="post" enctype="multipart/form-data">
                        <div class="form-group">
                            <label class="col-sm-2 col-lg-2 col-md-2 col-xs-2 control-label">Excel文件：</label>
                            <div class="col-sm-8 col-lg-8 col-md-8 col-xs-8">
                                <div class="col-sm-5" style="padding-left: 0px" >
                                    <input  name="picName" type='text' readonly id="logotextfield" data-validate="excel" class='form-control' onclick="uploadClick($(this),$('#uplogoInput'))" />
                                </div>
                                <button type="button" rel="ajax-upload-avatar" class="btn btn-primary" onclick="uploadClick($('#logotextfield'),$('#uplogoInput'))" >
                                    选择文件
                                </button>

                                <input type="file" style="display:none;" id="uplogoInput" name="pic"  />
                                <i class="notnull">*</i>
                                <i class="notnull green">请使用模板文件</i>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4 col-lg-4 col-md-4 col-xs-4 col-lg-offset-3 col-sm-offset-3 col-md-offset-3 col-xs-offset-3">
                                <button class="btn btn-primary" type="button" onclick="imorg();">导入</button>
                                <a href="javascript:void(0);" id="backBtn" class="btn btn-white" type="button">返回</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="<c:url value="/resources/js/org.js"/>"></script>
<script type="text/javascript">
    jQuery(function(){
        keyupform($('#userForm'));
        $('#backBtn').click(function(){
            reloadHash('#assess/org')
        });
    });
</script>


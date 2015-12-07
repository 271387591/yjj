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
    <div class="col-sm-10 col-lg-10 col-md-10 col-xs-10 col-lg-10 col-md-10 col-xs-10">
        <h3>${command.id!=null?"编辑机构":"添加机构"}</h3>
        <ol class="breadcrumb">

            <li>
                <a href="#assess/org">机构列表</a>
            </li>
            <li>
                <strong>${command.id!=null?"编辑机构":"添加机构"}</strong>
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
                    <h5>基本信息</h5>
                    <div class="ibox-tools">
                        <div><a href="#assess/org"  class="btn btn-primary btn-xs">返回</a></div>
                    </div>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal m-t" id="userForm" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="id" value="${command.id}">
                        <div class="form-group">
                            <label class="col-sm-2 col-lg-2 col-md-2 col-xs-2 control-label">机构名称：</label>
                            <div class="col-sm-8 col-lg-8 col-md-8 col-xs-8">
                                <input name="name" value="${command.name}" type="text"  class="form-control" data-validate="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-lg-2 col-md-2 col-xs-2 control-label">机构编码：</label>
                            <div class="col-sm-8 col-lg-8 col-md-8 col-xs-8">
                                <input type="text" value="${command.orgNo}" class="form-control" name="orgNo" data-validate="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-lg-2 col-md-2 col-xs-2 control-label">地址：</label>
                            <div class="col-sm-8 col-lg-8 col-md-8 col-xs-8">
                                <input type="text" value="${command.address}" class="form-control" name="address" data-validate="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-lg-2 col-md-2 col-xs-2 control-label">法定代表人：</label>
                            <div class="col-sm-8 col-lg-8 col-md-8 col-xs-8">
                                <input type="text" value="${command.dbr}" class="form-control" name="dbr" data-validate="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-lg-2 col-md-2 col-xs-2 control-label">类别：</label>
                            <div class="col-sm-8 col-lg-8 col-md-8 col-xs-8" >
                                <select class="form-control" name="cls" data-validate="required">
                                    <option value="1">食品生产企业</option>
                                    <option value="2">食品销售企业</option>
                                    <option value="3">餐饮企业</option>
                                    <option value="4">药品经营企业</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-lg-2 col-md-2 col-xs-2 control-label">许可证编号：</label>
                            <div class="col-sm-8 col-lg-8 col-md-8 col-xs-8">
                                <input type="text" value="${command.xkzNo}" class="form-control" name="xkzNo" data-validate="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-lg-2 col-md-2 col-xs-2 control-label">发证机关：</label>
                            <div class="col-sm-8 col-lg-8 col-md-8 col-xs-8">
                                <input type="text" value="${command.fzjg}" class="form-control" name="fzjg" data-validate="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-lg-2 col-md-2 col-xs-2 control-label">发证日期：</label>
                            <div class="col-sm-8 col-lg-8 col-md-8 col-xs-8">
                                <input type="text" onclick="laydate({format: 'YYYY-MM-DD'})" value="${command.fzDate}" class="form-control" name="fzDate" data-validate="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-lg-2 col-md-2 col-xs-2 control-label">过期日期：</label>
                            <div class="col-sm-8 col-lg-8 col-md-8 col-xs-8">
                                <input type="text" onclick="laydate({format: 'YYYY-MM-DD'})" value="${command.endDate}" class="form-control" name="endDate" data-validate="required">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 col-lg-2 col-md-2 col-xs-2 control-label">机构图片：</label>
                            <div class="col-sm-8 col-lg-8 col-md-8 col-xs-8">
                                <div class="col-sm-5" style="padding-left: 0px" >
                                    <input value="${command.picName}" name="picName" type='text' readonly id="logotextfield" data-validate="picture" class='form-control' onclick="uploadClick($(this),$('#uplogoInput'))" />
                                </div>
                                <button type="button" rel="ajax-upload-avatar" class="btn btn-primary" onclick="uploadClick($('#logotextfield'),$('#uplogoInput'))" >
                                    选择图片
                                </button>

                                <input type="file" style="display:none;" id="uplogoInput" name="pic"  />
                                <i class="notnull">*</i>
                                <i class="notnull green">建议上传高度约1024，宽度约400的图片</i>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 col-lg-2 col-md-2 col-xs-2 control-label">机构描述：</label>
                            <div class="col-sm-10 col-lg-10 col-md-10 col-xs-10">
                                <script type="text/plain" id="addNewsEditor" style="height:300px;">
                                <p></p>
                                </script>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-4 col-lg-4 col-md-4 col-xs-4 col-lg-offset-3 col-sm-offset-3 col-md-offset-3 col-xs-offset-3">
                                <button class="btn btn-primary" type="button" onclick="saveModel();">保存</button>
                                <a href="javascript:void(0);" id="backBtn" class="btn btn-white" type="button">返回</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="<c:url value="/resources/lib/ueditor/ueditor.all.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/org.js"/>"></script>
<script type="text/javascript">


    jQuery(function(){
        keyupform($('#userForm'));
        var addNewsEditors = UE.getEditor('addNewsEditor');

        if(${command.id!=null}){
            addNewsEditors.ready(function() {
                var div=$('<div></div>').append('${command.description}');
                var des = div.find('img').each(function(){
                    var src=$(this).attr('src') || '';
                    var index=src.indexOf("updload/ue");
                    if(index!=-1){
                        src=appPath+src.substr(index);
                        $(this).attr("src",src);
                    }
                });
                addNewsEditors.setContent(div.html());
            });
            $('select[name=cls]').val('${command.cls}');
        }
        $('#backBtn').click(function(){
            reloadHash('#assess/org')
        });
    });
</script>


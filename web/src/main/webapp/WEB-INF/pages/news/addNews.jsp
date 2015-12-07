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
        <h3>${command.id!=null?"编辑新闻":"添加新闻"}</h3>
        <ol class="breadcrumb">
            <li>
                <a href="#news/index">新闻列表</a>
            </li>
            <li>
                <strong>${command.id!=null?"编辑新闻":"添加新闻"}</strong>
            </li>
        </ol>
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
                        <div><a href="#news/index"  class="btn btn-primary btn-xs">返回</a></div>
                    </div>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal m-t" id="userForm" method="post">
                        <input type="hidden" name="id" value="${command.id}">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">新闻标题：</label>
                            <div class="col-sm-8">
                                <input name="title" value="${command.title}" type="text"  class="form-control" data-validate="required">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">发布单位：</label>
                            <div class="col-sm-8">
                                <input type="text" value="${command.pubUnit}" class="form-control" name="pubUnit" data-validate="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">是否发布</label>
                            <div class="col-sm-8">
                                <label class="checkbox-inline">
                                    <input type="radio" value="true"  name="publish">是</label>
                                <label class="checkbox-inline">
                                    <input type="radio" value="false" name="publish">否</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">资讯内容</label>
                            <div class="col-sm-10">
                                <script type="text/plain" id="addNewsEditor" style="height:300px;">
                                <p></p>
                                </script>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-4 col-sm-offset-3">
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
<script type="text/javascript" src="<c:url value="/resources/js/model/news.js"/>"></script>
<script type="text/javascript">


    jQuery(function(){
        keyupform($('#userForm'));
        $('input[type=radio]').iCheck({
            checkboxClass: 'icheckbox_square-green',
            radioClass: 'iradio_square-green'
        });

        var addNewsEditors = UE.getEditor('addNewsEditor');

        if(${command.id!=null}){
            addNewsEditors.ready(function() {
                var div=$('<div></div>').append('${command.content}');
                var des = div.find('img').each(function(){
                    var src=$(this).attr('src') || '';
                    var index=src.indexOf("updload/ue");
                    if(index!=-1){
                        src='${imgUrl}'+src.substr(index);
                        $(this).attr("src",src);
                    }
                });
                addNewsEditors.setContent(div.html());
            });
            if(${command.publish}){
                $('input[value=true]').iCheck('check');
            }else{
                $('input[value=false]').iCheck('check');
            }
        }
        $('#backBtn').click(function(){
            if(addNewsEditors){
                addNewsEditors.destroy();
            }
            reloadHash('#news/index')
        })
    });
</script>


/**
 * Created by lihao1 on 7/27/15.
 */
var columns=[
    {
        width:400,
        name:'name',
        renderer:function(v,rec){
            return '<a href="javascript:void(0);" onclick="edit('+rec.id+')">'+v+'</a>';
        }
    },
    {
        width:200,
        name:'enabled',
        renderer:function(v){
            return v?'<span class="label label-lg label-success">已发布</span>':'<span class="label label-lg label-default">未发布</span>';
        }
    },
    {
        name:'createDate',
        width:160,
        renderer:function(v){
            return new Date(v).format("yyyy-MM-dd hh:mm:ss");
        }
    },
    {
        width:60,
        renderer:function(v,rec){
            var ehidden=rec.hasUser?"hidden":"",pubHidden=rec.enabled?"hidden":"";
            var pTitle="'发布问卷'",pContent="'您确定要发布该问卷，该操作会取消之前发布的问卷。'";
            return '<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">'+
                '<a class="red" href="javascript:void(0);" data-rel="tooltip" title="删除" onclick="createDeleteModal('+rec.id+',null,removeUser)">'+
                '<i class="fa fa-lg fa-trash bigger-130"></i>'+
                '</a>'+
                '<a class="green '+ehidden+'" href="javascript:void(0);" data-rel="tooltip" title="编辑" onclick="edit('+rec.id+')">'+
                '<i class="fa fa-lg fa-edit bigger-130"></i>'+
                '</a>'+
                '<a class="blue '+pubHidden+'" href="javascript:void(0);" data-rel="tooltip" title="发布" onclick="createModal('+rec.id+','+pTitle+','+pContent+',publishS);">'+
                '<i class="fa fa-lg fa-bullhorn bigger-130"></i>'+
                '</a>'+
                '<a class="green" href="javascript:void(0);" data-rel="tooltip" title="查看调查结果" onclick="detail('+rec.id+')">'+
                '<i class="fa fa-lg fa-info-circle bigger-130"></i>'+
                '</a>'+
                '</div>';
        }
    }
];
function listTable(params){
    $('#userTable').htable({
        url:appPath+'html/survey/security/list',
        params:$.extend({},params),
        columns:columns,
        pager:$('#paging')
    });
}
function edit(id){
    if(id){
        reloadPage("html/survey/security/edit/"+id);
    }else{
        reloadPage("html/survey/security/add")
    }
}
function publishS(id){
    var result=requestJSONData('html/survey/security/publish/'+id);
    if(result.success){
        alertSuccess('操作成功');
        listTable();
    }else{
        alertError(result.message);
    }
}

function removeUser(id,modal){
    var result=requestStringData('html/survey/security/delete/'+id);
    if(result.success){
        alertSuccess('操作成功');
        listTable();
    }else{
        alertError(result.message);
    }
    return true;
}

function detail(id){
    ajaxReloadPage("mainContent","html/survey/security/detail/"+id);
}
function saveAdvert(){
    var datas=$('#exhibitionForm').serializeArray();
    var obj={};
    for(var i=0;i<datas.length;i++){
        obj[datas[i].name]=datas[i].value;
    }
    if(!checkForm($('#exhibitionForm'))){
        return
    }
    var result=requestJSONData('html/survey/security/save',obj);
    if(result.success){
        alertSuccess("保存成功",function(){
            reloadPage('html/survey/security/edit/'+result.data.id);
        });
    }else{
        alertError(result.msg);
    }
}



var qcolumns=[
    {
        width:150,
        name:'question',
        renderer:function(v,rec){
            return '<a href="javascript:void(0);" onclick="editQuestion('+rec.surveyId+','+rec.id+')">'+v+'</a>';
        }
    },
    {
        width:150,
        name:'type',
        renderer:function(v){
            if(v==0){
                return '单选';
            }else if(v==1){
                return '多选';
            }else if(v==2){
                return '文本';
            }
        }
    },
    {
        width:60,
        renderer:function(v,rec){

            return '<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">'+
                '<a class="red" href="javascript:void(0);" data-rel="tooltip" title="删除" onclick="createDeleteModal('+rec.id+',null,removeQue)">'+
                '<i class="fa fa-lg fa-trash bigger-130"></i>'+
                '</a>'+
                '<a class="green" href="javascript:void(0);"  data-rel="tooltip" title="编辑" onclick="editQuestion('+rec.surveyId+','+rec.id+')">'+
                '<i class="fa fa-lg fa-edit bigger-130"></i>'+
                '</a>'+
                '</div>';
        }
    }
];
function listQuestionTable(surveyId,params){
    $('#exhNewsTable').htable({
        url:appPath+'html/question/security/list',
        params:$.extend({Q_surveyId_EQ:surveyId},params),
        columns:qcolumns,
        pager:$('#exhNewsTablePaging')
    });
}
function editQuestion(sid,id){
    if(id){
        ajaxReloadPage("descriptionExh","html/question/security/edit/"+sid+"/"+id);
    }else{
        ajaxReloadPage("descriptionExh","html/question/security/add/"+sid);
    }
}


function addTableTr(dtable,a){
    var index=dtable.find('tr').size()-2;
    $('<tr class="tr-data">'+
    '<td class="text-center">'+
    '<input id="answer'+index+'" type="text" class="form-control">'+
    '</td>'+
    '<td class="text-center">'+
    '<a title="删除" class="icon-action" onclick="removeTableTr(this);" href="javascript:void(0)"><span class="fa fa-remove red bigger-160"></span></a>'+
    '</td>'+
    '</tr>').appendTo(dtable.find('tbody'));

}
function removeTableTr(a){
    a.closest('tr').remove();
}
function saveQuestion(sid){
    var form=$('#addNewsForm');
    var id = form.find('input[name=id]').val();
    var surveyId = form.find('input[name=surveyId]').val();
    var question = form.find('input[name=question]').val();
    if(!question){
        validateData(form.find('input[name=question]'));
    }
    var type = form.find('select[name=type]').val();
    if(!type){
        validateData(form.find('input[name=type]'));
    }
    var answers=[];
    form.find('input[id*=answer]').each(function(an){
        var obj={};
        obj.id=$(this).data('anid');
        obj.answer=$(this).val();
        answers.push(obj);
    });
    if(type!=2&&answers.length<1){
        alertError("请至少添加一个答案");
        return;
    }
    var data={
        id:id,
        surveyId:surveyId,
        question:question,
        type:type,
        answers:answers
    }
    var result=requestJSONData('html/question/security/save',data);
    if(result.success){
        alertSuccess("保存成功");
        ajaxReloadPage('descriptionExh','html/question/security/questionList/'+sid);
    }else{
        alertError(result.message);
    }

}
function removeQue(id){
    var result=requestStringData('html/question/security/delete/'+id);
    if(result.success){
        alertSuccess("删除成功");
        listQuestionTable(result.data.surveyId)
    }else{
        alertError(result.message);
    }
}








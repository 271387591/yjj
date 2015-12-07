/**
 * Created by lihao1 on 6/20/15.
 */
function saveModel(type){
    if(checkForm($('#userForm'))){
        var datas=$('#userForm').serializeArray();
        var obj={};
        for(var i=0;i<datas.length;i++){
            obj[datas[i].name]=datas[i].value;
        }
        if(obj.rule>100){
            alertError('细则分数不能超过100');
            return;
        }
        var result=requestJSONData('html/assess/savequestion',obj);
        if(result.success){
            createCustomConfirm2(null,'保存成功',['继续添加','返回列表'],function(){
                ajaxReloadPage("content-wrapper","html/assess/addquestion");
            },function(){
                reloadHash('#assess/question');
            });
        }else{
            alertError(result.message);
        }
    }

}
var columns=[

    {
        name:'name'
    },

    {
        name:'rule'
    },
    {
        width:100,
        renderer:function(v,rec){
            var pTitle="删除",pContent="'该细则可能被其他方案引用，请谨慎操作，您确定要删除？'";
            var p="'"+rec.integrity+"'";
            return '<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">'+
                '<a class="green " href="#assess/editquestion/'+rec.id+'" data-rel="tooltip" title="编辑">'+
                '<i class="fa fa-lg fa-pencil bigger-130"></i>'+
                '</a>'+
                '<a class="red " href="javascript:void(0);" data-rel="tooltip" title="删除" onclick="createConfirm('+rec.id+','+pContent+',removeUser);">'+
                '<i class="fa fa-lg fa-trash bigger-130"></i>'+
                '</a>'+
                '</div>';
        }
    }

];
function listTable(params){
    $('#pubTable').htable({
        url:appPath+'html/assess/listquestion',
        params:$.extend({},params),
        columns:columns,
        pager:$('#pubTablepager')
    });
}
function removeUser(id){
    var result=requestStringData('html/assess/deletequestion/'+id);
    if(result.success){
        alertSuccess('操作成功');
        setTimeout(function(){
            listTable();
        },300);
    }else{
        alertError(result.message);
    }
}


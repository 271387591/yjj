/**
 * Created by lihao1 on 6/20/15.
 */
function saveModel(){
    submitJSONForm($('#userForm'),'html/dictionaryType/save',null,function(result){
        if(result.success){
            createCustomConfirm2(null,'保存成功',['继续添加','返回列表'],function(){
                edit();
            },function(){
                reloadPage('html/dictionaryType/index');
            });
        }else{
            alertError(result.message);
        }
    });
}

var columns=[
    {
        width:150,
        name:'id'
    },
    {
        width:200,
        name:'dtype'
    },
    {
        name:'createDate',
        width:160,
        renderer:function(v){
            return new Date(v).format("yyyy-MM-dd hh:mm:ss");
        }
    },
    {
        width:100,
        renderer:function(v,rec){
            var pContent="'删除该分类将会删除所有与该分类相关的值，您确定要删除？'";
            return '<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">'+
                '<a class="green" href="javascript:void(0);" data-rel="tooltip" title="编辑" onclick="edit('+rec.id+')">'+
                '<i class="fa fa-lg fa-pencil bigger-130"></i>'+
                '</a>'+
                '<a class="red" href="javascript:void(0);" data-rel="tooltip" title="删除" onclick="createConfirm('+rec.id+','+pContent+',removeUser);">'+
                '<i class="fa fa-lg fa-trash bigger-130"></i>'+
                '</a>'+
                '</div>';
        }
    }
];
function listTable(params){
    $('#userTable').htable({
        url:appPath+'html/dictionaryType/list',
        params:$.extend({},params),
        columns:columns,
        pager:$('#pager')
    });

}
function removeUser(id,modal){
    var result=requestStringData('html/dictionaryType/delete/'+id);
    if(result.success){
        alertSuccess('操作成功');
        listTable();
    }else{
        alertError(result.message);
    }
    return true;
}
function edit(id){
    if(id){
        //reloadPage('html/dictionaryType/edit/'+id);
        ajaxReloadPage('content-wrapper','html/dictionaryType/edit/'+id);
    }else{
        //reloadPage('html/dictionaryType/add');
        ajaxReloadPage('content-wrapper','html/dictionaryType/add');
    }
}

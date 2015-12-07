/**
 * Created by lihao1 on 6/20/15.
 */
function saveModel(){
    submitJSONForm($('#userForm'),'html/dictionary/save',null,function(result){
        if(result.success){
            createCustomConfirm2(null,'保存成功',['继续添加','返回列表'],function(){
                ajaxReloadPage("content-wrapper","html/dictionary/addDic");
            },function(){
                reloadHash('#dictionary/index');
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
        width:200,
        name:'keyValue'
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
            return '<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">'+
                '<a class="green" href="#dictionary/editDic/'+rec.id+'" data-rel="tooltip" title="编辑">'+
                '<i class="fa fa-lg fa-pencil bigger-130"></i>'+
                '</a>'+
                '<a class="red" href="javascript:void(0);" data-rel="tooltip" title="删除" onclick="createRemoveConfirm('+rec.id+',removeUser)">'+
                '<i class="fa fa-lg fa-trash bigger-130"></i>'+
                '</a>'+
                '</div>';
        }
    }
];
function listTable(params){
    $('#userTable').htable({
        url:appPath+'html/dictionary/list',
        params:$.extend({},params),
        columns:columns,
        pager:$('#pager')
    });

}
function removeUser(id,modal){
    var result=requestStringData('html/dictionary/delete/'+id);
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
        //reloadPage('html/dictionary/editDic/'+id);
        ajaxReloadPage('content-wrapper','html/dictionary/editDic/'+id);
    }else{
        //reloadPage('html/dictionary/addDic');
        ajaxReloadPage('content-wrapper','html/dictionary/addDic');
    }
}

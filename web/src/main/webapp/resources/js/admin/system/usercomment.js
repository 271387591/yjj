/**
 * Created by lihao1 on 7/27/15.
 */
var columns=[
    {
        width:150,
        name:'contract'
    },
    {
        width:300,
        name:'content'
    },
    {
        width:200,
        name:'username'
    },
    {
        width:200,
        name:'email'
    },
    {
        name:'createDate',
        width:140,
        renderer:function(v){
            return new Date(v).format("yyyy-MM-dd hh:mm:ss");
        }
    },
    {
        width:60,
        renderer:function(v,rec){
            return '<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">'+
                '<a class="red" href="javascript:void(0);" data-rel="tooltip" title="删除" onclick="createDeleteModal('+rec.id+',null,removeUser)">'+
                '<i class="fa fa-lg fa-trash bigger-130"></i>'+
                '</a>'+
                '<a class="green" href="javascript:void(0);" data-rel="tooltip" title="查看详情" onclick="detail('+rec.id+')">'+
                '<i class="fa fa-lg fa-info-circle bigger-130"></i>'+
                '</a>'+
                '</div>';
        }
    }
];
function listTable(params){
    $('#friendTable').htable({
        url:appPath+'html/userComments/security/list',
        params:$.extend({},params),
        columns:columns,
        pager:$('#friendTablePaging')
    });
}
function detail(id){
    ajaxReloadPage("mainContent","html/userComments/security/getcom/"+id);
}
function removeUser(id,modal){
    var result=requestStringData('html/userComments/security/delete/'+id);
    if(result.success){
        alertSuccess('操作成功');
        listTable();
    }else{
        alertError(result.message);
    }
    return true;
}
function removeDetail(id,modal){
    var result=requestStringData('html/userComments/security/delete/'+id);
    if(result.success){
        ajaxReloadPage('mainContent','html/userComments/security/commentList');
    }else{
        alertError(result.message);
    }
    return true;
}

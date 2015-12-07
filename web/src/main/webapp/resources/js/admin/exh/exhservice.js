/**
 * Created by lihao1 on 6/20/15.
 */

function saveAdvert(type,publish){
    var datas=$('#userForm').serializeArray();
    var obj={};
    for(var i=0;i<datas.length;i++){
        obj[datas[i].name]=datas[i].value;
    }
    obj.content=UE.getEditor('desEditor').getContent();
    if(!checkForm($('#userForm'))){
        return
    }
    delete obj.editorValue;
    console.log(obj);
    var result=requestJSONData('html/exhService/security/save',obj);
    if(result.success){
        alertSuccess("保存成功",function(){
            reloadPage('html/exhService/security/index');
        });
    }else{
        alertError(result.message);
    }
}
var columns=[
    {
        width:400,
        name:'title',
        renderer:function(v,rec){
            return '<a href="javascript:void(0);" onclick="edit('+rec.id+')">'+v+'</a>';
        }
    },
    {
        width:200,
        name:'source'
    },
    {
        name:'createDate',
        width:140,
        renderer:function(v){
            return new Date(v).format("yyyy-MM-dd hh:mm:ss");
        }
    },
    {
        width:90,
        renderer:function(v,rec){
            var pTitle="'设为热门'",pContent="'您确定要将该展馆设为热门展馆?'";
            return '<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">'+
                '<a class="red" href="javascript:void(0);" data-rel="tooltip" title="删除" onclick="createDeleteModal('+rec.id+',null,removeNews)">'+
                '<i class="fa fa-lg fa-trash bigger-130"></i>'+
                '</a>'+
                '<a class="green" href="javascript:void(0);" data-rel="tooltip" title="编辑" onclick="edit('+rec.id+')">'+
                '<i class="fa fa-lg fa-edit bigger-130"></i>'+
                '</a>'+
                '</div>';
        }

    }
];
function listTable(params){
    $('#userTable').htable({
        url:appPath+'html/exhService/list',
        params: $.extend({},params),
        columns:columns,
        pager:$('#paging')
    });
}

function edit(id){
    if(id){
        reloadPage("html/exhService/security/edit/"+id);
    }else{
        reloadPage("html/exhService/security/add")
    }
}
function removeNews(id,modal){
    var result=requestStringData('html/exhService/security/delete/'+id);
    if(result.success){
        alertSuccess('操作成功');
        listTable();
    }else{
        alertError(result.message);
    }
    return true;
}


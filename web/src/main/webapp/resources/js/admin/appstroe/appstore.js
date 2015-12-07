/**
 * Created by lihao1 on 6/20/15.
 */
function saveAdvert(type){

    if(!checkForm($('#userForm'))){
        return;
    }
    if(!$('#uphototx').val()){
        alertError("请选择附件");
        return;
    }
    var datas=$('#userForm').serializeArray();
    var obj={};
    for(var i=0;i<datas.length;i++){
        obj[datas[i].name]=datas[i].value;
    }
    $.ajaxFileUpload({
        url: appPath + 'html/appstore/security/upload',
        secureuri:false,
        fileElementId:'uphototx',
        dataType: 'text/html',
        data: obj,
        error: function() {
        },
        success: function(response) {
            var result=JSON.parse(response);
            console.log(result)
            if(result.success){
                alertSuccess("保存成功")
                ajaxReloadPage('mainContent','html/appstore/security/appList');
            }else{
                alertError(result.msg);
            }
        }
    });
}

var columns=[
    {
        name:'version',
        renderer:function(v,rec){
            return '<a href="#edit" onclick="detail('+rec.id+');">'+v+'</a>';
        }
    },
    {
        name:'plat',
        renderer:function(v,rec){
            if(v==0){
                return 'Android';
            }
        }
    },
    {
        name:'createDate',
        renderer:function(v){
            return new Date(v).format("yyyy-MM-dd hh:mm:ss");
        }
    },
    {
        name:'enabled',
        renderer:function(v){
            if(v){
                return '<span class="label label-lg label-success">当前最新</span>';
            }else{
                return '<span class="label label-lg label-default">历史版本</span>';
            }

        }
    },

    {
        width:100,
        name:'enabled',
        renderer:function(v,rec){
            var pTitle="'设为最新'",pContent="'您确定要将该版本设为最新?'",delDiv=v?"hidden":"red",updateDiv=v?"hidden":"green";
            return '<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">'+
                '<a class="'+delDiv+'" href="javascript:void(0);" data-rel="tooltip" title="删除" onclick="createDeleteModal('+rec.id+',null,removeUser)">'+
                '<i class="fa fa-lg fa-trash bigger-130"></i>'+
                '</a>'+
                '<a class="'+updateDiv+'" href="javascript:void(0);" data-rel="tooltip" onclick="createModal('+rec.id+','+pTitle+','+pContent+',publishAct);" title="设为最新">'+
                '<i class="fa fa-lg fa-edit bigger-130"></i>'+
                '</a>'+
                '</div>';
        }
    }
];
function listTable(params){
    $('#userTable').htable({
        url:appPath+'html/appstore/security/list',
        params:$.extend({},params),
        columns:columns,
        pager:$('#paging')
    });

}
function publishAct(id){
    var result=requestStringData('html/appstore/security/update/'+id,{});
    if(result.success){
        alertSuccess('操作成功');
        listTable();
    }else{
        alertError(result.message);
    }
}
function removeUser(id,modal){
    var result=requestStringData('html/appstore/security/delete/'+id,{});
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
        $('#mainContent').load(appPath+"html/appstore/security/addApp?id="+id);
    }else{
        $('#mainContent').load(appPath+"html/appstore/security/addApp#right-content")
    }
}
function detail(id){
    $('#mainContent').load(appPath+"html/appstore/security/detail/"+id);
}


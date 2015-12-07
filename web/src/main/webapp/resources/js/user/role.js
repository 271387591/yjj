/**
 * Created by lihao1 on 6/20/15.
 */
function initZtree(features){
    var setting = {
        check: {
            enable: true,
            chkboxType:{
                "Y" : "ps",
                "N" : "ps"
            }
        },
        view: {
            showIcon:false
        },
        data: {
            simpleData: {
                enable: true
            }
        }
    };
    var zNodes=requestJSONData('html/feature/list');
    if(features && features.length>0){
        var data=zNodes.data;
        for(var i=0;i<data.length;i++){
            var eid=data[i].id;
            for(var j=0;j<features.length;j++){
                if(eid==features[j]){
                    data[i].checked=true;
                }
            }
        }
        $.fn.zTree.init($("#treeDemo"), setting, data);
    }else{
        $.fn.zTree.init($("#treeDemo"), setting, zNodes.data);
    }
}

function saveModel(){
    submitJSONForm($('#userForm'),'html/role/save',function(data){
        var treeObj=$.fn.zTree.getZTreeObj("treeDemo");
        var nodes = treeObj.getCheckedNodes(true);
        var r=[];
        if(nodes){
            for(var i=0;i<nodes.length;i++){
                r.push(nodes[i].id);
            }
        }
        data.features=r;
    },function(result){
        if(result.success){
            createCustomConfirm2(null,'保存成功',['继续添加','返回列表'],function(){
                //edit();
                //reloadHash('#/role/addRole');
                ajaxReloadPage("content-wrapper","html/role/addRole");
            },function(){
                reloadHash('#/role/index');
            });
        }else{
            alertError(result.message);
        }
    });
}

var columns=[
    {
        width:200,
        name:'name'
    },
    {
        width:600,
        name:'description'
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
            var bj=accessRes.updateRole?'':'hidden';
            var de=accessRes.deleteRole?'':'hidden';
            return '<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">'+
                '<a class="green '+bj+'" href="#/role/editRole/'+rec.id+'" data-rel="tooltip" title="编辑">'+
                '<i class="fa fa-lg fa-pencil bigger-130"></i>'+
                '</a>'+
                '<a class="red '+de+'" href="javascript:void(0);" data-rel="tooltip" title="删除" onclick="createRemoveConfirm('+rec.id+',removeUser)">'+
                '<i class="fa fa-lg fa-trash bigger-130"></i>'+
                '</a>'+
                '</div>';
        }
    }
];
function listTable(params){
    $('#userTable').htable({
        url:appPath+'html/role/list',
        params:$.extend({'Q_name_NEQ':'ROLE_ADMIN'},params),
        columns:columns,
        pager:$('#pager')
    });

}
function removeUser(id){
    var ids=[];
    ids.push(id);
    var result=requestStringData('html/user/delete',{ids:ids.join(',')});
    if(result.success){
        alertSuccess('操作成功');
        listTable();
    }else{
        alertError(result.message);
    }
}
function edit(id){
    if(id){
        //reloadPage("html/role/editRole/"+id);
        ajaxReloadPage("content-wrapper","html/role/editRole/"+id);
    }else{
        //reloadPage("html/role/addRole")
        ajaxReloadPage("content-wrapper","html/role/addRole")
    }
}

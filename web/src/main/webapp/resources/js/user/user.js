/**
 * Created by lihao1 on 6/20/15.
 */
function saveModel(type){
    submitJSONForm($('#userForm'),'html/user/save',function(data){
        var r=[],rb={};
        var roles=$('#roleSelector').val();
        if(roles){
            for(var i=0;i<roles.length;i++){
                rb={};
                rb.id=roles[i];
                r.push(rb);
            }
        }
        data.roles=r;
        return true;
    },function(result){
        if(result.success){
            createCustomConfirm2(null,'保存成功',['继续添加','返回列表'],function(){
                ajaxReloadPage("content-wrapper","html/user/addUser");
            },function(){
                reloadHash('#/user/index');
            });
        }else{
            alertError(result.message);
        }
    });
}
function changeUserPwd(modal){
    var form=$('#changeUserPasswordModel').find('#updateUserPwd');
    submitJSONForm(form,'html/user/changeUserPwd',null,function(result){
        if(result.success){
            alertSuccess('操作成功');
            listTable();
        }else{
            alertError(result.message);
        }
    });
    modal.modal('hide');
}
function expiredUser(id){
    var result=requestJSONData('html/user/expiredUser/'+id);
    if(result.success){
        alertSuccess('操作成功');
        listTable();
    }else{
        alertError(result.message);
    }
}

var columns=[
    {
        width:200,
        name:'username'
    },
    {
        width:100,
        name:'nickName'
    },
    {
        width:200,
        name:'email'
    },
    {
        name:'createDate',
        width:160,
        renderer:function(v){
            return new Date(v).format("yyyy-MM-dd hh:mm:ss");
        }
    },
    {
        name:'credentialsExpired',
        width:80,
        renderer:function(v){
            if(v==0){
                return '<span class="label label-lg label-success">是</span>';
            }else{
                return '<span class="label label-lg label-grey"">否</span>';
            }
        }
    },

    {
        width:100,
        renderer:function(v,rec){
            var pTitle="禁用用户",pContent="'您确定要禁用该用户？'";
            if(rec.credentialsExpired==1){
                pTitle='解禁用户';
                pContent="'您确定要解禁该用户？'"
            }
            var bj='';
            var de='';
            var pd='';
            var ep='';
            return '<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">'+
                '<a class="green '+bj+'" href="#/user/editUser/'+rec.id+'" data-rel="tooltip" title="编辑">'+
                '<i class="fa fa-lg fa-pencil bigger-130"></i>'+
                '</a>'+
                '<a class="red '+de+'" href="javascript:void(0);" data-rel="tooltip" title="删除" onclick="createRemoveConfirm('+rec.id+',removeUser)">'+
                '<i class="fa fa-lg fa-trash bigger-130"></i>'+
                '</a>'+
                '<a class="green '+pd+'" href="#changeUserPasswordModel" data-rel="tooltip" userId="'+rec.id+'" title="修改密码" data-toggle="modal" data-target="#changeUserPasswordModel">'+
                '<i class="fa fa-lg fa-edit bigger-130"></i>'+
                '</a>'+
                '<a class="blue '+ep+'" href="javascript:void(0);" data-rel="tooltip" onclick="createConfirm('+rec.id+','+pContent+',expiredUser);" title="'+pTitle+'" >'+
                '<i class="fa fa-lg fa-key bigger-130"></i>'+
                '</a>'+
                '</div>';
        }
    }
];
function listTable(params){
    $('#userTable').htable({
        url:appPath+'html/user/list',
        params:$.extend({},params),
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
        ajaxReloadPage("content-wrapper","html/user/editUser/"+id);
        //reloadPage("html/user/editUser/"+id);
    }else{
        //reloadPage("html/user/addUser")
        ajaxReloadPage("content-wrapper","html/user/addUser");
    }
}
function detail(id){
    if(id){
        //reloadPage("html/user/detail/"+id);
        ajaxReloadPage("content-wrapper","html/user/detail/"+id);
    }
}


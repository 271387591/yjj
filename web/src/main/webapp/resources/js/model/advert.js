/**
 * Created by lihao1 on 6/20/15.
 */
function saveModel(type){
    var datas=$('#userForm').serializeArray();
    var obj={};
    for(var i=0;i<datas.length;i++){
        obj[datas[i].name]=datas[i].value;
    }
    //obj.description=UE.getEditor('desEditor').getContent();
    //if(!checkForm($('#userForm'))){
    //    return
    //}
    //if(!obj.lng || !obj.lat){
    //    alertError('请选择展馆地图位置');
    //    return;
    //}
    //delete obj.editorValue;

    var options = {
        url:appPath + 'html/advert/upload',
        dataType:'json',
        beforeSubmit:function(data){
            if(!checkForm($('#userForm'))){
                return false;
            }

            return true;
        },
        success: function (result) {
            if(result.success){
                createCustomConfirm2(null,'保存成功',['继续添加','返回列表'],function(){
                    ajaxReloadPage("content-wrapper","html/advert/add");
                },function(){
                    reloadHash('#advert/index');
                });
            }else{
                alertError(result.message);
            }

        }
    };
    $("#userForm").ajaxForm(options);
    $('#userForm').ajaxSubmit(options);



    //submitJSONForm($('#userForm'),'html/advert/save',function(data){
    //    data.content=data.editorValue;
    //    delete data.editorValue;
    //    return true;
    //},function(result){
    //    if(result.success){
    //        createCustomConfirm2(null,'保存成功',['继续添加','返回列表'],function(){
    //            ajaxReloadPage("content-wrapper","html/advert/add");
    //        },function(){
    //            reloadHash('#advert/index');
    //        });
    //    }else{
    //        alertError(result.message);
    //    }
    //});
}
var columns=[
    {
        width:300,
        name:'title'
    },
    {
        width:100,
        name:'pubUnit'
    },

    {
        name:'publishDate',
        width:160,
        renderer:function(v){
            return new Date(v).format("yyyy-MM-dd hh:mm:ss");
        }
    },
    {
        name:'readNum',
        width:80
    },

    {
        width:100,
        renderer:function(v,rec){
            var bj='';
            var de='';
            var pd='';
            var ep='';
            var pTitle="禁用用户",pContent="'您确定要取消发布？'";
            return '<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">'+
                '<a class="green '+bj+'" href="#/advert/edit/'+rec.id+'" data-rel="tooltip" title="编辑">'+
                '<i class="fa fa-lg fa-pencil bigger-130"></i>'+
                '</a>'+

                '<a class="green '+pd+'" href="javascript:void(0);" onclick="createConfirm('+rec.id+','+pContent+',pub);" data-rel="tooltip"  title="取消发布">'+
                '<i class="fa fa-lg fa-mail-reply bigger-130"></i>'+
                '</a>'+
                '</div>';
        }
    }
];
var npcolumns=[
    {
        width:300,
        name:'title'
    },
    {
        width:100,
        name:'pubUnit'
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
            var bj='';
            var de='';
            var pd='';
            var ep='';
            var pTitle="禁用用户",pContent="'您确定要发布？'";
            return '<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">'+
                '<a class="green '+bj+'" href="#/advert/edit/'+rec.id+'" data-rel="tooltip" title="编辑">'+
                '<i class="fa fa-lg fa-pencil bigger-130"></i>'+
                '</a>'+
                '<a class="red '+de+'" href="javascript:void(0);" data-rel="tooltip" title="删除" onclick="createRemoveConfirm('+rec.id+',removeUser)">'+
                '<i class="fa fa-lg fa-trash bigger-130"></i>'+
                '</a>'+
                '<a class="green '+pd+'" href="javascript:void(0);" onclick="createConfirm('+rec.id+','+pContent+',pub);" data-rel="tooltip"  title="发布">'+
                '<i class="fa fa-lg fa-mail-forward bigger-130"></i>'+
                '</a>'+
                '</div>';
        }
    }
];

function listTable(params){
    $('#pubTable').htable({
        url:appPath+'html/advert/list',
        params:$.extend({'Q_publish_EQ':1},params),
        columns:columns,
        pager:$('#pubTablepager')
    });
}
function listNpTable(params){
    $('#noPubTable').htable({
        url:appPath+'html/advert/list',
        params:$.extend({'Q_publish_EQ':0},params),
        columns:npcolumns,
        pager:$('#noPubTablepager')
    });
}
function removeUser(id){
    var result=requestStringData('html/advert/delete/'+id);
    if(result.success){
        alertSuccess('操作成功');
        listNpTable();
        setTimeout(function(){
            listTable();
        },300);
    }else{
        alertError(result.message);
    }
}

function pub(id){
    var result=requestStringData('html/advert/pub/'+id);
    if(result.success){
        alertSuccess('操作成功');
        listNpTable();
        setTimeout(function(){
            listTable();
        },300);
    }else{
        alertError(result.message);
    }
}


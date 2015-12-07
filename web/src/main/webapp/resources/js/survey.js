/**
 * Created by lihao1 on 6/20/15.
 */
function saveModel(type){
    var form=$('#userForm');
    if(checkForm(form)){
        var id=form.find('input[name=id]').val();
        var name=form.find('input[name=name]').val();
        var orgs=form.find('select[name=orgs]').val();
        var questions=form.find('select[name=questions]').val();

        var os=[],qs=[];
        for(var i=0;i<questions.length;i++){
            var obj={};
            obj.id=questions[i];
            qs.push(obj);
        }
        for(var i=0;i<orgs.length;i++){
            var obj={};
            obj.id=orgs[i];
            os.push(obj);
        }

        var obj={
            id:id,
            name:name,
            orgs:os,
            questions:qs
        }
        var result=requestJSONData('html/assess/savesurvey',obj);
        if(result.success){
            createCustomConfirm2(null,'保存成功',['继续添加','返回列表'],function(){
                ajaxReloadPage("content-wrapper","html/assess/savesurvey");
            },function(){
                reloadHash('#assess/survey');
            });
        }else{
            alertError(result.message);
        }

    }
}
var columns=[
    {
        name:'name',
        renderer:function(v,rec){
            return '<a href="#assess/detailsurvey/'+rec.id+'">'+v+'</a>';
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
        width:100,
        renderer:function(v,rec){
            return '<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">'+
                '<a class="green " href="#assess/editsurvey/'+rec.id+'" data-rel="tooltip" title="编辑">'+
                '<i class="fa fa-lg fa-pencil bigger-130"></i>'+
                '</a>'+
                '<a class="red " href="javascript:void(0);" data-rel="tooltip" title="删除" onclick="createRemoveConfirm('+rec.id+',removensurvey);">'+
                '<i class="fa fa-lg fa-trash bigger-130"></i>'+
                '</a>'+
                    '<a class="blue " href="javascript:void(0);" data-rel="tooltip" title="提交审核" onclick="verifysurvey('+rec.id+');">'+
                '<i class="fa fa-lg fa-external-link bigger-130"></i>'+
                '</a>'+

                '</div>';
        }
    }
];
var nocolumns=[
    {
        name:'name',
        renderer:function(v,rec){
            return '<a href="#assess/detailsurvey/'+rec.id+'">'+v+'</a>';
        }
    },

    {
        name:'lastUpdateDate',
        width:160,
        renderer:function(v){
            return new Date(v).format("yyyy-MM-dd hh:mm:ss");
        }
    }
];
var ycolumns=[
    {
        name:'name',
        renderer:function(v,rec){
            return '<a href="#assess/detailsurvey/'+rec.id+'">'+v+'</a>';
        }
    },

    {
        name:'lastUpdateDate',
        width:160,
        renderer:function(v){
            return new Date(v).format("yyyy-MM-dd hh:mm:ss");
        }
    },
    {
        width:100,
        renderer:function(v,rec){
            var pTitle="删除",pContent="'该方案可能被其他机构引用或评价，如删除将会解除其所有引用，数据将不再保存，请谨慎操作，您确定要删除？'";
            return '<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">'+
                '<a class="red " href="javascript:void(0);" data-rel="tooltip" title="删除" onclick="createConfirm('+rec.id+','+pContent+',removepsurvey);">'+
                '<i class="fa fa-lg fa-trash bigger-130"></i>'+
                '</a>'+
                '<a class="blue " href="#assess/surveytime/'+rec.id+'" data-rel="tooltip" title="查看审核流程" >'+
                '<i class="fa fa-lg fa-search bigger-130"></i>'+
                '</a>'+
                '<a class="blue" href="#assess/surveypj/'+rec.id+'" data-rel="tooltip" title="考评打分">'+
                '<i class="fa fa-lg fa-pencil bigger-130"></i>'+
                '</a>'+

                '</div>';
        }
    }
];
var yncolumns=[
    {
        name:'name',
        renderer:function(v,rec){
            return '<a href="#assess/detailsurvey/'+rec.id+'">'+v+'</a>';
        }
    },

    {
        name:'lastUpdateDate',
        width:160,
        renderer:function(v){
            return new Date(v).format("yyyy-MM-dd hh:mm:ss");
        }
    },
    {
        width:100,
        renderer:function(v,rec){
            return '<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">'+
                '<a class="green " href="#assess/editsurvey/'+rec.id+'" data-rel="tooltip" title="编辑">'+
                '<i class="fa fa-lg fa-pencil bigger-130"></i>'+
                '</a>'+
                '<a class="red " href="javascript:void(0);" data-rel="tooltip" title="删除" onclick="createRemoveConfirm('+rec.id+',removensurvey);">'+
                '<i class="fa fa-lg fa-trash bigger-130"></i>'+
                '</a>'+
                '<a class="blue " href="javascript:void(0);" data-rel="tooltip" title="提交审核" onclick="verifysurvey('+rec.id+');">'+
                '<i class="fa fa-lg fa-external-link bigger-130"></i>'+
                '</a>'+
                '<a class="green " href="#assess/surveytime/'+rec.id+'" data-rel="tooltip" title="查看审核流程" >'+
                '<i class="fa fa-lg fa-search bigger-130"></i>'+
                '</a>'+
                '</div>';
        }
    }
];



function listTable(params){
    $('#pubTable').htable({
        url:appPath+'html/assess/listsurvey',
        params:$.extend({Q_verify_EQ:0},params),
        columns:columns,
        pager:$('#pubTablepager')
    });
}
function listNoTable(params){
    $('#noPubTable').htable({
        url:appPath+'html/assess/listsurvey',
        params:$.extend({Q_verify_EQ:1},params),
        columns:nocolumns,
        pager:$('#noPubTablepager')
    });
}
function listYTable(params){
    $('#yPubTable').htable({
        url:appPath+'html/assess/listsurvey',
        params:$.extend({Q_verify_EQ:2},params),
        columns:ycolumns,
        pager:$('#yPubTablepager')
    });
}
function listYNTable(params){
    $('#ynPubTable').htable({
        url:appPath+'html/assess/listsurvey',
        params:$.extend({Q_verify_EQ:3},params),
        columns:yncolumns,
        pager:$('#ynPubTablepager')
    });
}

function removensurvey(id){
    var result=requestStringData('html/assess/deletensurvey/'+id);
    if(result.success){
        alertSuccess('操作成功');
        setTimeout(function(){
            if($('#pubTab').hasClass('active')){
                listTable();
            }else{
                listYNTable();
            }
        },300);
    }else{
        alertError(result.message);
    }
}
function removepsurvey(id){
    var result=requestStringData('html/assess/deletensurvey/'+id);
    if(result.success){
        alertSuccess('操作成功');
        setTimeout(function(){
            listYTable();
        },300);
    }else{
        alertError(result.message);
    }
}

function verifysurvey(id){
    var result=requestStringData('html/assess/verifysurvey/'+id);
    if(result.success){
        alertSuccess('操作成功');
        setTimeout(function(){
            listTable();
        },300);
    }else{
        alertError(result.message);
    }
}




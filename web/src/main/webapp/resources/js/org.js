/**
 * Created by lihao1 on 6/20/15.
 */
function saveModel(type){
    var datas=$('#userForm').serializeArray();
    var obj={};
    for(var i=0;i<datas.length;i++){
        obj[datas[i].name]=datas[i].value;
    }
    var options = {
        url:appPath + 'html/assess/uploadorg',
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
                    ajaxReloadPage("wrapper-content","html/assess/addorg");
                },function(){
                    reloadHash('#assess/org');
                });
            }else{
                alertError(result.msg);
            }

        }
    };
    $("#userForm").ajaxForm(options);
    $('#userForm').ajaxSubmit(options);
}
function bgModel(){
    var datas=$('#userForm').serializeArray();
    var obj={};
    for(var i=0;i<datas.length;i++){
        obj[datas[i].name]=datas[i].value;
    }
    var result=requestJSONData('html/assess/bgorg',obj);
    if(result.success){
        setTimeout(function(){
            listTable();
            reloadHash('#assess/org');
        },300);
    }else{
        alertError(result.message);
    }
}
function convertprogress(progress){
    var sty='',progress=progress?progress:'';
    if(progress=='A'){
        sty='badge-primary';
    }else if(progress=='B'){
        sty='badge-success';
    }else if(progress=='C'){
        sty='badge-success';
    }else if(progress=='D'){
        sty='badge-warning';
    }else if(progress=='E'){
        sty='badge-danger';
    }
    return '<div class="badge '+sty+'" style="width: 25px">'+progress+'</div>'
}
var columns=[
    {
        width:100,
        name:'orgNo'
    },
    {
        name:'name'
    },
    {
        name:'address'
    },
    {
        name:'dbr'
    },

    {
        name:'type',
        renderer:function(v){
            if(v==1){
                return '新办';
            }else if(v==2){
                return '变更';
            }else if(v==3){
                return '延续';
            }else if(v==4){
                return '注销';
            }
            return '';
        }
    },
    {
        name:'cls',
        renderer:function(v){
            if(v==1){
                return '食品生产企业';
            }else if(v==2){
                return '食品销售企业';
            }else if(v==3){
                return '餐饮企业';
            }else if(v==4){
                return '药品经营企业';
            }
        }
    },
    {
        name:'xkzNo'
    },
    {
        name:'fzjg'
    },
    {
        name:'integrity',
        renderer:function(v){
            return convertprogress(v);
        }
    },

    {
        name:'fzDate',
        width:160,
        renderer:function(v){
            return new Date(v).format("yyyy-MM-dd");
        }
    },
    {
        name:'endDate',
        width:160,
        renderer:function(v){
            return new Date(v).format("yyyy-MM-dd");
        }
    },

    {
        cls:'td-w-60',
        renderer:function(v,rec){
            var pTitle="删除",pContent="'您确定要注销该机构？'";
            var start="'"+rec.fzDate+"'";
            var end="'"+rec.endDate+"'";
            return '<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">'+
                '<a class="green" href="#assess/bgorg/'+rec.id+'" data-rel="tooltip" title="变更">'+
                '<i class="fa fa-lg fa-edit bigger-130"></i>'+
                '</a>'+
                '<a class="red " onclick="yxorg('+rec.id+','+start+','+end+');" data-rel="tooltip" title="延续">'+
                '<i class="fa fa-lg fa-level-up bigger-130"></i>'+
                '</a>'+
                    '<a class="blue " href="javascript:void(0);" data-rel="tooltip" title="注销" onclick="createConfirm('+rec.id+','+pContent+',zxOrg);">'+
                '<i class="fa fa-lg fa-exclamation-circle bigger-130"></i>'+
                '</a>'+
                '</div>';
        }
    }
    ,
    {
        cls:'td-w-80',
        renderer:function(v,rec){
            var pTitle="删除",pContent="'删除改机构将会解除其所有引用，机构数据将不再保存，请谨慎操作，您确定要删除？'";
            var p="'"+rec.integrity+"'";
            return '<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">'+
                '<a class="green " href="#assess/editorg/'+rec.id+'" data-rel="tooltip" title="编辑">'+
                '<i class="fa fa-lg fa-pencil bigger-130"></i>'+
                '</a>'+
                '<a class="red " href="javascript:void(0);" data-rel="tooltip" title="删除" onclick="createConfirm('+rec.id+','+pContent+',removeUser);">'+
                '<i class="fa fa-lg fa-trash bigger-130"></i>'+
                '</a>'+
                    '<a class="blue" href="#assess/orgsurvey/'+rec.id+'" data-rel="tooltip" title="查看考评方案" >'+
                '<i class="fa fa-lg fa-search bigger-130"></i>'+
                '</a>'+
                '<a class="green" href="javascript:void(0);" onclick="cxPj('+rec.id+','+p+')"   data-rel="tooltip" title="诚信评级">'+
                '<i class="fa fa-lg fa-bar-chart bigger-130"></i>'+
                '</a>'+
                '</div>';
        }
    }

];
function listTable(params){
    $('#pubTable').htable({
        url:appPath+'html/assess/listorg',
        params:$.extend({},params),
        columns:columns,
        pager:$('#pubTablepager')
    });
}
function removeUser(id){
    var result=requestStringData('html/assess/deleteorg/'+id);
    if(result.success){
        alertSuccess('操作成功');
        setTimeout(function(){
            listTable();
        },300);
    }else{
        alertError(result.message);
    }
}

function imorg(){
    if(!checkForm($('#userForm'))){
        return;
    }
    var options = {
        url:appPath + 'html/assess/importorg',
        dataType:'json',
        beforeSubmit:function(data){
            if(!checkForm($('#userForm'))){
                return false;
            }

            return true;
        },
        success: function (result) {
            if(result.success){
                alertSuccess('导入成功');
                reloadHash('#assess/org');
            }else{
                alertError(result.msg);
            }

        }
    };
    $("#userForm").ajaxForm(options);
    $('#userForm').ajaxSubmit(options);
}
var fcolumns=[
    {
        name:'name'
    },
    {
        name:'total'
    },

    {
        width:100,
        renderer:function(v,rec){
            var p=rec.hasPj;
            var h1=p?"hidden":"";
            var h2=p?"":"hidden";
            return '<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">'+
                '<a class="red " href="javascript:void(0);" data-rel="tooltip" title="删除" onclick="createRemoveConfirm('+rec.id+',removeorgsurvey);">'+
                '<i class="fa fa-lg fa-trash bigger-130"></i>'+
                '</a>'+
                '<a class="blue '+h1+'" href="#assess/pjorgsurvey/'+rec.id+'/'+rec.orgId+'" data-rel="tooltip" title="考评打分">'+
                '<i class="fa fa-lg fa-pencil bigger-130"></i>'+
                '</a>'+
                '<a class="blue '+h2+'" href="#assess/rpjorgsurvey/'+rec.orgId+'/'+rec.id+'"  data-rel="tooltip" title="重新考评">'+
                '<i class="fa fa-lg fa-pencil-square-o bigger-130"></i>'+
                '</a>'+

                '</div>';
        }
    }
];
function listFTable(params){
    $('#pubTable').htable({
        url:appPath+'html/assess/listorgsurvey/'+orgId,
        params:$.extend({},params),
        columns:fcolumns,
        pager:$('#pubTablepager'),
        callback:function(list){

        }
    });
}
function removeorgsurvey(id){
    var result=requestStringData('html/assess/removeorgsurvey/'+id+'/'+orgId);
    if(result.success){
        setTimeout(function(){
            listFTable();
        },300);
    }else{
        alertError(result.message);
    }
}
function cxPj(id,p){
    var modal=$('#pingjrwsaveModal1')
    modal.modal('show');
    modal.on('show.bs.modal',function(){
        modal.find('input[type=radio]').iCheck('uncheck');
    });
    if(p){
        modal.find('input[value='+p+']').iCheck('check');
    }
    modal.find('#pingjrwsaveModalSaveBtn').off('click').on('click',function(){
        var v= modal.find('input[type=radio]:checked').val();
        if(v){
            var result=requestStringData('html/assess/cx/'+id+'/'+v);
            if(result.success){
                setTimeout(function(){
                    listTable();
                },300);
            }else{
                alertError(result.message);
            }

            modal.modal('hide');
        }
    });
}
function zxOrg(id){
    var result=requestStringData('html/assess/zxorg/'+id);
    if(result.success){
        setTimeout(function(){
            listTable();
        },300);
    }else{
        alertError(result.message);
    }
}
function yxorg(id,start,end){
    var modal=$('#yxorgModel');
    modal.modal('show');
    var form=modal.find('form');
    form.find('input[name=id]').val(id);
    form.find('input[name=startDate]').val(start);
    form.find('input[name=endDate]').val(end);
}
function sbyxorg(){
    var modal=$('#yxorgModel');
    var form=modal.find('form');
    var id=form.find('input[name=id]').val();
    var startDate=form.find('input[name=startDate]').val();
    var endDate=form.find('input[name=endDate]').val();
    var result=requestJSONData('html/assess/yxorg',{
        id:id,
        endDateStr:endDate
    });
    if(result.success){
        setTimeout(function(){
            listTable();
            modal.modal('hide');
        },300);
    }else{
        alertError(result.message);
    }
}


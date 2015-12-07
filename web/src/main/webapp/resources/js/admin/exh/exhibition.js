/**
 * Created by lihao1 on 6/20/15.
 */
function saveAdvert(){
    var datas=$('#exhibitionForm').serializeArray();
    var obj={};
    for(var i=0;i<datas.length;i++){
        obj[datas[i].name]=datas[i].value;
    }
    if(!checkForm($('#exhibitionForm'))){
        return
    }
    obj.trade=$('#hySelector').val().join(",");
    obj.keyword=$('#keywordSelector').val().join(",");
    $.ajaxFileUpload({
        url: appPath + 'html/exhibition/security/upload',
        secureuri:false,
        fileElementId:['upexhInput','upexhlogoInput'],
        dataType: 'text/html',
        data: obj,
        error: function() {
        },
        success: function(response) {
            var result=JSON.parse(response);
            if(result.success){
                alertSuccess("保存成功",function(){
                    reloadPage('html/exhibition/security/edit/'+result.data);
                });
            }else{
                alertError(result.msg);
            }
        }
    });
}
function saveDes(exhId){
    var datas=$('#exhDescriptionForm').serializeArray();
    var obj={};
    for(var i=0;i<datas.length;i++){
        obj[datas[i].name]=datas[i].value;
    }
    obj.description=(obj.editorValue)|| '';
    obj.exhId=exhId;
    delete obj.editorValue;
    var result=requestJSONData('html/exhDescription/security/save',obj);
    if(result.success){
        alertSuccess('保存成功');
    }else{
        alertSuccess(result.message);
    }
}
function saveGuide(exhId){
    var datas=$('#exhGuideForm').serializeArray();
    var obj={};
    for(var i=0;i<datas.length;i++){
        obj[datas[i].name]=datas[i].value;
    }
    obj.guide=(obj.editorValue)|| '';
    obj.exhId=exhId;
    delete obj.editorValue;
    var result=requestJSONData('html/exhGuide/security/save',obj);
    if(result.success){
        alertSuccess('保存成功');
    }else{
        alertSuccess(result.message);
    }
}
function saveGuideTo(exhId){
    var datas=$('#guidetoForm').serializeArray();
    var obj={};
    for(var i=0;i<datas.length;i++){
        obj[datas[i].name]=datas[i].value;
    }
    obj.guideTo=(obj.editorValue) ||'';
    obj.exhId=exhId;
    delete obj.editorValue;
    var result=requestJSONData('html/exhGuideTo/security/save',obj);
    if(result.success){
        alertSuccess('保存成功');
    }else{
        alertSuccess(result.message);
    }
}
function saveSponsor(exhId){
    var datas=$('#sponsorForm').serializeArray();
    var obj={};
    for(var i=0;i<datas.length;i++){
        obj[datas[i].name]=datas[i].value;
    }
    obj.sponsor=(obj.editorValue) || '';
    obj.exhId=exhId;
    delete obj.editorValue;
    var result=requestJSONData('html/exhSponsor/security/save',obj);
    if(result.success){
        alertSuccess('保存成功');
    }else{
        alertSuccess(result.message);
    }
}
function saveTravel(exhId){
    var datas=$('#travelForm').serializeArray();
    var obj={};
    for(var i=0;i<datas.length;i++){
        obj[datas[i].name]=datas[i].value;
    }
    obj.travel=(obj.editorValue)|| '';
    obj.exhId=exhId;
    delete obj.editorValue;
    var result=requestJSONData('html/exhTravel/security/save',obj);
    if(result.success){
        alertSuccess('保存成功');
    }else{
        alertSuccess(result.message);
    }
}
var columns=[
    {
        name:'name',
        width:200,
        renderer:function(v,rec){
            return '<a href="javascript:void(0);" onclick="edit('+rec.id+')">'+v+'</a>';
        }
    },
    {
        width:150,
        name:'hallName'
    },
    {
        width:200,
        name:'address'
    },
    {
        width:180,
        name:'tradeNames'
    },
    {
        name:'startDate',
        width:120,
        renderer:function(v){
            return new Date(v).format("yyyy-MM-dd");
        }
    },
    {
        name:'endDate',
        width:120,
        renderer:function(v){
            return new Date(v).format("yyyy-MM-dd");
        }
    },
    {
        width:80,
        renderer:function(v,rec){
            var pTitle="'设为热门'",pContent="'您确定要将该展馆设为热门展馆?'",delDiv=v?"hidden":"red",updateDiv=v?"hidden":"green";
            return '<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">'+
                '<a class="'+delDiv+'" href="javascript:void(0);" data-rel="tooltip" title="删除" onclick="createDeleteModal('+rec.id+',null,removeUser)">'+
                '<i class="fa fa-lg fa-trash bigger-130"></i>'+
                '</a>'+
                '<a class="'+updateDiv+'" href="javascript:void(0);" data-rel="tooltip" onclick="edit('+rec.id+');" title="编辑">'+
                '<i class="fa fa-lg fa-edit bigger-130"></i>'+
                '</a>'+
                '</div>';
        }

    }
];
function listTable(params){
    $('#userTable').htable({
        url:appPath+'html/exhibition/list',
        params: $.extend({},params),
        columns:columns,
        pager:$('#paging')
    });
}

function edit(id){
    if(id){
        reloadPage("html/exhibition/security/edit/"+id);
    }else{
        reloadPage("html/exhibition/security/add")
    }
}
function setHot(id){
    var result=requestStringData('html/exhibitionHall/security/hot/'+id,{});
    if(result.success){
        alertSuccess('操作成功');
        listGenTable();
    }else{
        alertError(result.message);
    }
}
function removeUser(id,modal){
    var result=requestStringData('html/exhibition/security/delete/'+id);
    if(result.success){
        alertSuccess('操作成功');
        listTable();
    }else{
        alertError(result.message);
    }
    return true;
}



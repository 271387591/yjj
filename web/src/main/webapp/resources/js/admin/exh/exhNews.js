/**
 * Created by lihao1 on 7/17/15.
 */
var newsColumns=[
    {
        name:'title',
        width:500,
        renderer:function(v,rec){
            return '<a href="javascript:void(0);" onclick="editNews('+rec.exhId+','+rec.id+')">'+v+'</a>';
        }
    },

    {
        name:'keywordNames',
        width:200
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
            return '<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">'+
                '<a class="red" href="javascript:void(0);" data-rel="tooltip" title="删除" onclick="createDeleteModal('+rec.id+',null,removeNews)">'+
                '<i class="fa fa-lg fa-trash bigger-130"></i>'+
                '</a>'+
                '<a class="green" href="javascript:void(0);" data-rel="tooltip" onclick="editNews('+rec.exhId+','+rec.id+');" title="编辑">'+
                '<i class="fa fa-lg fa-edit bigger-130"></i>'+
                '</a>'+
                '</div>';
        }

    }

];
function listNewsTable(exhId,params){
    $('#exhNewsTable').htable({
        url:appPath+'html/exhNews/list',
        params: $.extend({Q_exhId_EQ:exhId},params),
        columns:newsColumns,
        pager:$('#exhNewsTablePaging')
    });
}
function saveNews(exhId){
    if(!checkForm($('#addNewsForm'))){
        return
    }
    var datas=$('#addNewsForm').serializeArray();
    var obj={};
    for(var i=0;i<datas.length;i++){
        obj[datas[i].name]=datas[i].value;
    }
    obj.news=UE.getEditor('addNewsEditor').getContent();
    obj.exhId=exhId;
    obj.keywordIds=$('#newsKeywordSelector').val().join(",");
    delete obj.editorValue;
    var result=requestJSONData('html/exhNews/security/save',obj);
    if(result.success){
        alertSuccess('保存成功');
        ajaxReloadPage("newsExh","html/exhNews/security/newsList/"+exhId);
    }else{
        alertSuccess(result.message);
    }
}
function editNews(exhId,id){
    if(id){
        ajaxReloadPage("newsExh","html/exhNews/security/editNews/"+exhId+"/"+id);
    }else{
        $('#newsExh').load(appPath+"html/exhNews/security/addNews/"+exhId);
    }
}
function removeNews(id){
    var result=requestStringData('html/exhNews/security/delete/'+id);
    if(result.success){
        alertSuccess('操作成功');
        listNewsTable(result.data.exhId);
    }else{
        alertError(result.message);
    }
}
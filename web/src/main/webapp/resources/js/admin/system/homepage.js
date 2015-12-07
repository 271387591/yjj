/**
 * Created by lihao1 on 6/20/15.
 */
function saveAdvert(type,publish){
    var datas=$('#userForm').serializeArray();
    var obj={};
    for(var i=0;i<datas.length;i++){
        obj[datas[i].name]=datas[i].value;
    }
    if(!checkForm($('#userForm'))){
        return
    }
    $.ajaxFileUpload({
        url: appPath + 'html/homePage/security/upload',
        secureuri:false,
        fileElementId:['uplogoInput'],
        dataType: 'text/html',
        data: obj,
        error: function() {
        },
        success: function(response) {
            var result=JSON.parse(response);
            if(result.success){
                alertSuccess("保存成功");
            }else{
                alertError(result.msg);
            }
        }
    });
}
var friendColumns=[
    {
        name:'name'
    },
    {
        width:600,
        name:'url'
    },
    {
        width:90,
        renderer:function(v,rec){
            var pTitle="'设为热门'",pContent="'您确定要将该展馆设为热门展馆?'";
            return '<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">'+
                '<a class="red" href="javascript:void(0);" data-rel="tooltip" title="删除" onclick="createDeleteModal('+rec.id+',null,removeUser)">'+
                '<i class="fa fa-lg fa-trash bigger-130"></i>'+
                '</a>'+
                '<a class="green" href="javascript:void(0);" data-rel="tooltip" title="编辑" onclick="editFriend('+rec.id+')">'+
                '<i class="fa fa-lg fa-edit bigger-130"></i>'+
                '</a>'+
                '</div>';
        }

    }
];
function saveFriend(){
    var datas=$('#addFriendForm').serializeArray();
    var obj={};
    for(var i=0;i<datas.length;i++){
        obj[datas[i].name]=datas[i].value;
    }
    if(!checkForm($('#addFriendForm'))){
        return
    }
    var result=requestJSONData('html/friendLink/security/save',obj);
    if(result.success){
        alertSuccess("保存成功");
        ajaxReloadPage("friendTab",'html/friendLink/security/friendList');
    }else{
        alertError(result.message);
    }

}
function editFriend(id){
    if(id){
        ajaxReloadPage("friendTab","html/friendLink/security/edit/"+id);
    }else{
        ajaxReloadPage("friendTab","html/friendLink/security/add")
    }
}
function listFriendTable(params){
    $('#friendTable').htable({
        url:appPath+'html/friendLink/list',
        params: $.extend({},params),
        columns:friendColumns,
        pager:$('#friendTablePaging')
    });
}
function removeUser(id,modal){
    var result=requestStringData('html/friendLink/security/delete/'+id);
    if(result.success){
        alertSuccess('操作成功');
        listFriendTable();
    }else{
        alertError(result.message);
    }
    return true;
}
function saveEmail(id){
    var datas=$('#emailForm').serializeArray();
    var obj={};
    for(var i=0;i<datas.length;i++){
        obj[datas[i].name]=datas[i].value;
    }
    if(!checkForm($('#emailForm'))){
        return
    }
    var result=requestJSONData('html/emailConfig/security/save',obj);
    if(result.success){
        alertSuccess("保存成功")
    }else{
        alertError("保存失败");
    }
}



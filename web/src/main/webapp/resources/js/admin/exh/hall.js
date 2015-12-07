/**
 * Created by lihao1 on 6/20/15.
 */
function initAdd(add,lng,lat,description){
    changeMenu($('#menu-hall'));
    var map = new BMap.Map("baiduMap");
    if(add){
        map.centerAndZoom("成都",11);
    }else{
        var point = new BMap.Point(lng, lat);
        map.centerAndZoom(point, 11);
        var marker = new BMap.Marker(point);
        map.addOverlay(marker);

    }
    var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});
    var top_left_navigation = new BMap.NavigationControl();
    var top_right_navigation = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_RIGHT, type: BMAP_NAVIGATION_CONTROL_SMALL});
    map.addControl(top_left_control);
    map.addControl(top_left_navigation);
    map.addControl(top_right_navigation);
    map.addEventListener("click",function(e){
        map.clearOverlays();
        var marker = new BMap.Marker(new BMap.Point(e.point.lng, e.point.lat));
        map.addOverlay(marker);
        $('input[name=lng]').val(e.point.lng);
        $('input[name=lat]').val(e.point.lat);
    });
}
function initList(tabIndex){
    if(tabIndex==0){
        listGenTable();
    }else{
        listHotTable();
    }
    $('#tab-generalHall a').click(function (e) {
        e.preventDefault()
        $(this).tab('show');
        listGenTable();
        tabIndex=0;
        $('#userSearch input[name="user.hot"]').val(tabIndex);
    });
    $('#tab-hotHall a').click(function (e) {
        e.preventDefault()
        $(this).tab('show');
        listHotTable();
        tabIndex=1;
        $('#userSearch input[name="user.hot"]').val(tabIndex);
    });
    $('#searchBtn').click(function(){
        if(tabIndex==0){
            searchForm($('#userSearch'),'user',listGenTable);
        }else if(tabIndex==1){
            searchForm($('#userSearch'),'user',listHotTable);
        }

    });
    $('#clearBtn').click(function(){
        if(tabIndex==0){
            clearSearchForm($('#userSearch'),listGenTable);
        }else if(tabIndex==1){
            clearSearchForm($('#userSearch'),listHotTable);
        }
    });
}
function saveAdvert(type,publish){
    var datas=$('#userForm').serializeArray();
    var obj={};
    for(var i=0;i<datas.length;i++){
        obj[datas[i].name]=datas[i].value;
    }
    obj.description=UE.getEditor('desEditor').getContent();
    if(!checkForm($('#userForm'))){
        return
    }
    if(!obj.lng || !obj.lat){
        alertError('请选择展馆地图位置');
        return;
    }
    delete obj.editorValue;
    $.ajaxFileUpload({
        url: appPath + 'html/exhibitionHall/security/upload',
        secureuri:false,
        fileElementId:['uplogoInput','upxcInput'],
        dataType: 'text/html',
        data: obj,
        error: function() {
        },
        success: function(response) {
            var result=JSON.parse(response);
            if(result.success){
                alertSuccess("保存成功",function(){
                    reloadPage('html/exhibitionHall/security/index');
                });
            }else{
                alertError(result.msg);
            }
        }
    });
}
var gencolumns=[
    {
        name:'name',
        renderer:function(v,rec){
            return '<a href="javascript:void(0);" onclick="edit('+rec.id+')">'+v+'</a>';
        }
    },
    {
        width:200,
        name:'address'
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
                '<a class="red" href="javascript:void(0);" data-rel="tooltip" title="删除" onclick="checkExh('+rec.id+')">'+
                '<i class="fa fa-lg fa-trash bigger-130"></i>'+
                '</a>'+
                '<a class="green" href="javascript:void(0);" data-rel="tooltip" title="编辑" onclick="edit('+rec.id+')">'+
                '<i class="fa fa-lg fa-edit bigger-130"></i>'+
                '</a>'+
                '<a class="blue" href="javascript:void(0);" data-rel="tooltip" onclick="createModal('+rec.id+','+pTitle+','+pContent+',setHot);" title="设为热门">'+
                '<i class="fa fa-lg fa-thumbs-up bigger-130"></i>'+
                '</a>'+
                '</div>';
        }

    }
];
function listGenTable(params){
    $('#generalHallTable').htable({
        url:appPath+'html/exhibitionHall/list',
        params: $.extend({'Q_t.hot_EQ':0},params),
        columns:gencolumns,
        pager:$('#generalHallTablePager')
    });
}
var hotcolumns=[
    {
        name:'name',
        renderer:function(v,rec){
            return '<a href="javascript:void(0);" onclick="edit('+rec.id+')">'+v+'</a>';
        }
    },
    {
        width:200,
        name:'address'
    },
    {
        name:'updateDate',
        width:140,
        renderer:function(v){
            if(v){
                return new Date(v).format("yyyy-MM-dd hh:mm:ss");
            }
        }
    },
    {
        name:'createDate',
        width:140,
        renderer:function(v){
            if(v){
                return new Date(v).format("yyyy-MM-dd hh:mm:ss");
            }

        }
    },
    {
        width:90,
        renderer:function(v,rec){
            var pTitle="'设为普通'",pContent="'您确定要将该展馆设为普通展馆?'";
            return '<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">'+
                '<a class="red" href="javascript:void(0);" data-rel="tooltip" title="删除" onclick="checkExh('+rec.id+')">'+
                '<i class="fa fa-lg fa-trash bigger-130"></i>'+
                '</a>'+
                '<a class="green" href="javascript:void(0);" data-rel="tooltip" title="编辑" onclick="edit('+rec.id+')">'+
                '<i class="fa fa-lg fa-edit bigger-130"></i>'+
                '</a>'+
                '<a class="blue" href="javascript:void(0);" data-rel="tooltip" onclick="createModal('+rec.id+','+pTitle+','+pContent+',setHot);" title="设为普通">'+
                '<i class="fa fa-lg fa-thumbs-down bigger-130"></i>'+
                '</a>'+
                '</div>';
        }

    }
];
function listHotTable(params){
    $('#hotHallTable').htable({
        url:appPath+'html/exhibitionHall/list',
        params: $.extend({'Q_t.hot_EQ':1},params),
        columns:hotcolumns,
        pager:$('#hotHallTablePager')
    });
}
function edit(id){
    if(id){
        reloadPage("html/exhibitionHall/security/edit/"+id);
    }else{
        reloadPage("html/exhibitionHall/security/add")
    }
}
function setHot(id){
    var result=requestStringData('html/exhibitionHall/security/hot/'+id,{});
    if(result.success){
        alertSuccess('操作成功');
        if(result.data){
            listGenTable();
        }else{
            listHotTable();
        }
    }else{
        alertError(result.message);
    }
}
function checkExh(id){
    var result=requestStringData('html/exhibitionHall/security/checkExh/'+id);
    if(result.success){
        if(result.data){
            createModal({},'删除展馆','该展馆下有展会信息，请先删除展会信息.');
        }else{
            createDeleteModal(id,null,deleteUser)
        }
    }else{
        alertError('请求失败');
    }
}
function deleteUser(id,modal){
    var result=requestStringData('html/exhibitionHall/security/delete/'+id);
    if(result.success){
        alertSuccess('操作成功');
        listGenTable();
    }else{
        alertError(result.message);
    }
    return true;
}


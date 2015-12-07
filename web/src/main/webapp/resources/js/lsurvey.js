/**
 * Created by lihao1 on 6/20/15.
 */


var nocolumns=[
    {
        name:'name'
    },
    {
        name:'nickName'
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
                '<a class="red " href="#leader/looksurvey/'+rec.id+'?t=h" data-rel="tooltip" title="审核" >'+
                '<i class="fa fa-lg fa-check-circle bigger-130"></i>'+
                '</a>'+
                '<a class="blue " href="#leader/lsurveytime/'+rec.id+'" data-rel="tooltip" title="查看审核流程" >'+
                '<i class="fa fa-lg fa-search bigger-130"></i>'+
                '</a>'+
                '</div>';
        }
    }
];
var ycolumns=[
    {
        name:'name'
    },
    {
        name:'nickName'
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
                '<a class="green " href="#leader/looksurvey/'+rec.id+'?t=l" data-rel="tooltip" title="查看方案详情" >'+
                '<i class="fa fa-lg fa-info-circle bigger-130"></i>'+
                '</a>'+
                '<a class="blue " href="#leader/lsurveytime/'+rec.id+'" data-rel="tooltip" title="查看审核流程" >'+
                '<i class="fa fa-lg fa-search bigger-130"></i>'+
                '</a>'+
                '</div>';
        }
    }
];


function listNoTable(params){
    $('#noPubTable').htable({
        url:appPath+'html/leader/listsurvey',
        params:$.extend({'Q_s.verify_EQ':1},params),
        columns:nocolumns,
        pager:$('#noPubTablepager')
    });
}
function listYTable(params){
    $('#yPubTable').htable({
        url:appPath+'html/leader/listsurvey',
        params:$.extend({'Q_s.verify_EQ':2},params),
        columns:ycolumns,
        pager:$('#yPubTablepager')
    });
}
function listYNTable(params){
    $('#ynPubTable').htable({
        url:appPath+'html/leader/listsurvey',
        params:$.extend({'Q_s.verify_EQ':3},params),
        columns:ycolumns,
        pager:$('#ynPubTablepager')
    });
}






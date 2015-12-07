String.prototype.format=function()
{
    if(arguments.length==0) return this;
    for(var s=this, i=0; i<arguments.length; i++)
        s=s.replace(new RegExp("\\{"+i+"\\}","g"), arguments[i]);
    return s;
};
Date.prototype.format = function(format){
    var d=this;
    var o = {
        "M+" : d.getMonth()+1, //month
        "d+" : d.getDate(), //day
        "h+" : d.getHours(), //hour
        "m+" : d.getMinutes(), //minute
        "s+" : d.getSeconds(), //second
        "q+" : Math.floor((d.getMonth()+3)/3), //quarter
        "S" : d.getMilliseconds() //millisecond
    }

    if(/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }

    for(var k in o) {
        if(new RegExp("("+ k +")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
        }
    }
    return format;
};

function alertSuccess(msg,callback){
    layer.msg(msg, {icon: 1,time: 2000},callback);
}
function alertError(msg,callback){
    layer.msg(msg, {icon: 2,time: 2000},callback);
}
function alertNotify(msg){
    layer.msg(msg, {icon: 3,time: 2000},callback);
}
function ajaxError(response){
    var status= response.status;
    if(status==403){
        alertError('你没有足够权限');
    }else if(status==404){
        alertError('找不到访问地址');
    }else if(status==400){
        alertError('请求出错');
    }else if(status==500){
        alertError('服务器异常');
    }else{
        alertError("网路未知错误");
    }
}
function ajaxComplete(XHR, TS){
    var timeout=XHR.getResponseHeader('sessiontimeout');
    if(timeout){
        createAlert('你的登录已超时,请重新登录',function(){
            flushPage('login.jsp');
        })
    }
}
function createRemoveConfirm(id,callback){
    layer.confirm('您确定要删除该数据?', {icon: 3, title:'删除'}, function(index){
        if(callback){
            callback(id);
        }
        layer.close(index);
    }, function(index){
        layer.close(index);
    });
}

function createConfirm(parmas,content,callback){
    layer.confirm(content, {icon: 3, title:'提示'}, function(index){
        if(callback){
            callback(parmas);
        }
        layer.close(index);
    }, function(index){
        layer.close(index);
    });
}
function createCustomConfirm2(parmas,content,btns,callback1,callback2){
    layer.confirm(content, {
        btn: btns,
        shade: false
    }, function(index){
        if(callback1){
            callback1(parmas);
        }
        layer.close(index);
    }, function(index){
        if(callback2){
            callback2(parmas);
        }
        layer.close(index);
    });
}

function createAlert(msg,callback){
    layer.alert(msg, {icon: 3},callback);
}

function requestJSONData(url, o,callback) {
    var result = null;
    $.ajax({
        async: false,
        url: appPath + url,
        dataType: 'json',
        contentType: "application/json;charset=UTF-8",
        data: JSON.stringify(o),
        cache: false,
        type: "POST",
        success: function (data) {
            result = data;
            if(callback){
                callback(result);
            }
        },
        complete:ajaxComplete,
        error:ajaxError
    });
    return result;
}

function requestStringData(url, o) {
    var result = null;
    $.ajax({
        async: false,
        url: appPath+url,
        data: o,
        cache: false,
        type: "POST",
        success: function (data) {
            result = data;
        },
        complete:ajaxComplete,
        error:ajaxError
    });
    return result;
}
function doGet(url,data,callback){
    $.get(appPath+url,data,callback);
}

function initComboData(combo,url, param, valueFiled, displayFiled,defaultValue,hasnulls) {
    var result = [];
    var list = requestStringData(url, param);
    combo.find("option").remove();
    if (list && list.data && list.data.length > 0) {
        var result = list.data;
        if(hasnulls){
            combo.append('<option value="">请选择</option>')
        }
        for (var i = 0; i < result.length; i++) {
            var comboData = result[i];
            var value=comboData[valueFiled];
            var display=comboData[displayFiled];
            var selected='selected="selected"';
            var hasValue=(value==defaultValue)?selected:'';
            if(typeof defaultValue =='object'){
                for(var item in defaultValue){
                    if(defaultValue[item]==value){
                        hasValue=selected;
                        break;
                    }
                }
            }
            combo.append('<option value="' + value + '" '+hasValue+'>' + display + '</option>')
        }
    }
    return result;
}


function submitAjaxForm(form, url,beforeCall, callback) {

    var options = {
        url:appPath + url,
        dataType:'json',
        beforeSubmit:function(data){
            if(!checkForm(form)){
                return false;
            }
            if(beforeCall){
                return beforeCall(data);
            }
            return true;
        },
        complete:ajaxComplete,
        error:ajaxError,
        success: function (result) {
            if(callback){
                callback(result);
            }
            form.find('button').removeAttr("disabled");
        }
    };
    form.find('button').attr('disabled',"true");
    form.ajaxForm(options);
    form.ajaxSubmit(options);

}
function submitJSONForm(form,url,beforeCall,callback){

    if(!checkForm(form)){
        return;
    }
    var datas=form.serializeArray();
    var obj={};
    for(var i=0;i<datas.length;i++){
        obj[datas[i].name]=datas[i].value;
    }
    if(beforeCall){
        var re = beforeCall(obj);
        if(!re){
            return;
        }
    }
    form.find('button').attr('disabled',"true");
    requestJSONData(url,obj,function(result){
        if(callback){
            callback(result);
        }
        form.find('button').removeAttr("disabled");
    });
}
function searchForm(form,objectName,callback) {
    var params = {};
    form.find("[name^='" + objectName + "']").each(function (i, o) {
        var name = $(o).attr("name");
        var index=name.indexOf('.');
        name=name.substr(index+1);
        params[name] = $(o).val();
    });
    if(callback){
        callback(params);
    }
}
function clearSearchForm(form,callback) {
    clearForm(form);
    if(callback){
        callback({});
    }
}


function reloadPage(url){
    window.location=(appPath+url);
}
function flushPage(url){
    window.location.replace(appPath+url);
}

function ajaxReloadPage(div,url){
    $('#'+div).load(appPath+url);
}
function reloadHash(hash){
    window.location.hash=hash;
}



function checkForm(form) {
    var valid = true;
    form.find("input[type='text'],input[type='password'],select,textarea").each(function (i, o) {
        if (!validateData($(o))) {
            valid = false;
        }
    });
    if(!valid){
        $("html, body").animate({ scrollTop: 0 }, 600);
    }
    return valid;
}

function clearForm(obj,notClears) {
    obj.find("input[type='text'],input[type='hidden'],input[type='password'],select,textarea").not(notClears).each(function (i, o) {
        validSuccess($(o));
        $(o).val('');
    });
}


function blurInput(inputs){
    inputs.blur(function(){
        validateData($(this));
    });
}
function keyupform(form){
    form.find('input[type=text],input[type=password]').keyup(function(n,l){
        validateData($(this))
    });
    form.find('select').change(function(){
        validateData($(this));
    })
}


function validateData(obj) {
    var validate=obj.data('validate');
    if(validate=='required'){
        return validateRequired(obj);
    }else if(validate=='mobile'){
        return validateMobile(obj);
    }else if(validate=='password'){
        return validatePassword(obj);
    }else if(validate=='passwordHit'){
        var pwd=$('#'+obj.data('hit'));
        return validatePasswordHit(pwd,obj);
    }else if(validate=='integer'){
        return validateInteger(obj);
    }else if(validate=='email'){
        return validateEmail(obj);
    }else if(validate=='username'){
        return validateUsername(obj);
    }else if(validate=='length'){
        var length=obj.data('len');
        length=new Number(length);
        return validateLength(obj,length);
    }else if(validate=='picture'){
        return validatePicture(obj);
    }else if(validate=='iso8859'){
        return validateIso8859(obj);
    }else if(validate=='url'){
        return validateUrl(obj);
    }
    return true;
}
function validateRequired(obj){
    var v = obj.val();
    if(!v){
        validFailure(obj, '不能为空');
        return false;
    }
    validSuccess(obj);
    return true;
}
function validatePassword(obj){
    if(!/^[0-9a-zA-Z]{6,16}$/.test(obj.val())){
        validFailure(obj,'请输入6-16位数字或字母组成的密码,不含下划线和特殊字符');
        return false;
    }
    validSuccess(obj);
    return true;
}
function validatePasswordHit(pas,hit){
    if(pas.val()!=hit.val()){
        validFailure(hit,'两次密码输入不一致');
        return false;
    }
    validSuccess(hit);
    return true;
}
function validateMobile(obj) {    //移动电话
    if(!(/^1[3|4|5|8][0-9]\d{4,8}$/.test(obj.val()))){
        validFailure(obj,'请输入合法的手机号码');
        return false;
    }
    validSuccess(obj);
    return true;
}
function validateInteger(obj) {    //整数
    if(!(/^[0-9]*[1-9][0-9]*$/.test(obj.val()))){
        validFailure(obj,'请输入整数');
        return false;
    }
    validSuccess(obj);
    return true;
}

function validateEmail(obj) {   //邮件
    var regEmail=/^((([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6}\;))*(([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})))$/;
    if(!(regEmail.test(obj.val()))){
        validFailure(obj,'请输入正确的电子邮件地址');
        return false;
    }
    validSuccess(obj);
    return true;

}
function validateUsername(obj) {   //邮件
    var regEmail=/^[a-zA-Z0-9_]{3,16}$/;
    if(!(regEmail.test(obj.val()))){
        validFailure(obj,'请输入3-16位由字母或数字组成的用户名');
        return false;
    }
    validSuccess(obj);
    return true;
}
function validateLength(obj,length) {   //长度
    var val=obj.val();
    if(val.length>length){
        validFailure(obj,'长度不能超过'+length+'位');
        return false;
    }
    validSuccess(obj);
    return true;
}
function validatePicture(obj) {   //图片
    if(!(/\.(jpe?g|png|gif|bmp)$/i.test(obj.val()))){
        validFailure(obj,'请选择jpg/png/gif/bmp/jpeg格式的图片');
        return false;
    }
    validSuccess(obj);
    return true;
}
function validateIso8859(obj) {   //图片
    if((/[^a-zA-Z0-9]/g.test(obj.val()))){
        validFailure(obj,'只能输入英文或数字');
        return false;
    }
    validSuccess(obj);
    return true;
}
function validateUrl(obj) {   //URL
    if(!obj.val()){
        return true;
    }
    if(!(/^(https?|s?ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i.test(obj.val()))){
        validFailure(obj,'请输入正确的URL');
        return false;
    }
    validSuccess(obj);
    return true;
}





function validFailure(obj, title) {
    var error='<span class="validation help-block m-b-none">'+
                title+
                '</span>';
    obj.closest('.form-group').removeClass('has-success').addClass('has-error');
    obj.parent('div').children('.validation').remove();
    obj.parent('div').append(error);
}
function validSuccess(obj) {
    obj.parent('div').children('.validation').remove();
    obj.closest('.form-group').removeClass('has-error').addClass('has-success');
}



function validateNumber(obj) {  //电话号码
    if(!(/^[0-9]*$/.test(obj.val()))){
        validFailure(obj,'请输入数字');
        return false;
    }
    validSuccess(obj)
    return true;
}
function isPostCode(number) {//邮编
    var postCode = /^[1-9]\d{5}$/;
    return postCode.exec(number);

}
function setImagePreviews(avalue) {
    var docObj = document.getElementById("doc");
    var dd = document.getElementById("dd");
    dd.innerHTML = "";
    var fileList = docObj.files;
    for (var i = 0; i < fileList.length; i++) {

        dd.innerHTML += "<div style='float:left' > <img id='img" + i + "'  /> </div>";
        var imgObjPreview = document.getElementById("img"+i);
        if (docObj.files && docObj.files[i]) {
            //火狐下，直接设img属性
            imgObjPreview.style.display = 'block';
            imgObjPreview.style.width = '150px';
            imgObjPreview.style.height = '180px';
            //imgObjPreview.src = docObj.files[0].getAsDataURL();
            //火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
            imgObjPreview.src = window.URL.createObjectURL(docObj.files[i]);
        }
        else {
            //IE下，使用滤镜
            docObj.select();
            var imgSrc = document.selection.createRange().text;
            alert(imgSrc)
            var localImagId = document.getElementById("img" + i);
            //必须设置初始大小
            localImagId.style.width = "150px";
            localImagId.style.height = "180px";
            //图片异常的捕捉，防止用户修改后缀来伪造图片
            try {
                localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
                localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
            }
            catch (e) {
                alert("您上传的图片格式不正确，请重新选择!");
                return false;
            }
            imgObjPreview.style.display = 'none';
            document.selection.empty();
        }
    }

    return true;
}
function uploadClick(tempFile,file){
    file.click();
    file.change(function(){
        tempFile.val($(this).val());
    })
}
function backToTop(className){
    var $backToTopTxt = "返回顶部", $backToTopEle = $('<a class="backToTop" href="javascript:void(0);"><i class="fa fa-angle-double-up"></i></a>').appendTo($(className))
        .text('').attr("title", $backToTopTxt).click(function() {
            $("html, body").animate({ scrollTop: 0 }, 600);
        }), $backToTopFun = function() {
        var st = $(document).scrollTop(), winh = $(window).height();
        (st > 0)? $backToTopEle.show(): $backToTopEle.hide();
        //IE6下的定位
        if (!window.XMLHttpRequest) {
            $backToTopEle.css("top", st + winh - 166);
        }
    };
    $(window).bind("scroll", $backToTopFun);
    $(function() { $backToTopFun(); });
};
function getLocation(){
    if (navigator.geolocation)
    {
        navigator.geolocation.getCurrentPosition(showPosition);
    }
    else{x.innerHTML="Geolocation is not supported by this browser.";}
}
function showPosition(position)
{
    $('#content-wrapper').html(position.coords.latitude+'<br/>'+position.coords.longitude);
}

function hchosen(select){
    select.chosen({
        placeholder_text:'关键字搜索',
        no_results_text:'未找到相关项'
    });
}
function searchMultiSelect(select,options){
    var opt= $.extend({
        selectableHeader: "<input type='text' style='margin-bottom: 2px' class='form-control' autocomplete='off' placeholder='搜索'>",
        selectionHeader: "<input type='text' style='margin-bottom: 2px' class='form-control' autocomplete='off' placeholder='搜索'>",
        afterInit: function(ms){
            var that = this,
                $selectableSearch = that.$selectableUl.prev(),
                $selectionSearch = that.$selectionUl.prev(),
                selectableSearchString = '#'+that.$container.attr('id')+' .ms-elem-selectable:not(.ms-selected)',
                selectionSearchString = '#'+that.$container.attr('id')+' .ms-elem-selection.ms-selected';

            that.qs1 = $selectableSearch.quicksearch(selectableSearchString)
                .on('keydown', function(e){
                    if (e.which === 40){
                        that.$selectableUl.focus();
                        return false;
                    }
                });

            that.qs2 = $selectionSearch.quicksearch(selectionSearchString)
                .on('keydown', function(e){
                    if (e.which == 40){
                        that.$selectionUl.focus();
                        return false;
                    }
                });
        },
        afterSelect: function(){
            this.qs1.cache();
            this.qs2.cache();
        },
        afterDeselect: function(){
            this.qs1.cache();
            this.qs2.cache();
        }
    },options);
    select.multiSelect(opt);
}
function logout(){
    createConfirm(null,'你确定要退出系统?',function(){
        window.location.replace(appPath+'logout.jsp?plat=security');
    });
}



(function($){
    var methods = {
        init:function(options){
            var o = $.extend({
                mode:'MULTI',//SINGLE
                idProperty:'id',
                params:{
                    start:0,
                    limit:15
                }
            }, options || {});
            var self = this;
            this.each(function() {
                methods._loadTableUrl(o.url, o.params,self, o.columns, o.pager, o.idProperty);
                methods._initCheck.call(self,o);
            });
        },
        getSelected:function(){
            var self = this,data=[];
            self.find('tr > td:first-child input:checkbox').each(function(){
                if(this.checked){
                    var identObj = $(this).closest('tr').find('td:last-child');
                    var id = identObj.data('id');
                    data.push(id);
                }
            });
            return data;
        },
        _initCheck:function(o){
            var self = this;
            if(o.mode=='MULTI'){
                self.find('th input:checkbox').on('click',function(){
                    var that = this;
                    $(this).closest(self).find('tr > td:first-child input:checkbox')
                        .each(function(){
                            this.checked = that.checked;
                            $(this).closest('tr').toggleClass('selected');
                        });
                });
            }else if(o.mode=='SINGLE'){
                self.find('th input:checkbox').toggleClass('disabled')
                self.find('tr > td:first-child input:checkbox').on('click',function(){
                    $(this).closest('tr').toggleClass('selected');
                    var current=$(this),cChecked=this.checked;
                    self.find('tr > td:first-child input:checkbox').not(current).each(function(){
                        this.checked=cChecked?!cChecked:cChecked;
                    });
                });
            }
        },
        _requestData:function(url,o,callback){
            //var result = null;
            $.ajax({
                //async: false,
                url: url,
                data:o,
                //cache: false,
                type: "POST",
                success: function (data) {
                    var result = data;
                    if(callback){
                        callback(result);
                    }
                },
                complete:ajaxComplete,
                error:ajaxError
            });
            //return result;
        },
        _loadTableData:function(url,parmas,pager,callback,tableCallBack){
            methods._requestData(url, parmas,function(result){
                var list=result.data;
                var total=result.total;
                var currentPage=Math.ceil(parmas.start/parmas.limit)+1;
                if(pager){
                    pager.pager({
                        totalRecords: total||0,
                        pageSize: parmas.limit,
                        pagenumber:currentPage,
                        buttonClickCallback:callback
                    });
                }
                if(tableCallBack){
                    tableCallBack(list);
                }

            });
        },
        _loadTableUrl:function(url,parmas,table,columns,pager,idProperty){
            //table.append('<div class="message-loading-overlay"><i class="fa fa-2x fa-spin fa-spinner"></i></div>');
            parmas= $.extend({start:0,limit:10}, parmas);
            methods._loadTableData(url, parmas,pager,function(pageNumber,event){
                var pageP=$.extend(parmas,{start:(pageNumber-1)*(parmas.limit)});
                methods._loadTableUrl(url,pageP,table,columns,pager,idProperty)
            },function(list){
                var tbody=table.find('tbody');
                var html="";
                if(list && list.length>0){
                    for(var i=0;i<list.length;i++){
                        var obj=list[i];
                        var tr='<tr>';

                        for(var j=0;j<columns.length;j++){
                            var column=columns[j];
                            var name=column.name ||'';
                            var width=column.width;
                            var cls=column.cls;
                            var val=obj[name];
                            if(val==undefined || val==null ){
                                val='';
                            }
                            var sub=Math.floor(width/12);
                            if(!isNaN(sub) && val.length>sub){
                                val= val.substr(0,(sub-3))+'...';
                            }

                            var renderer=function(v){
                                if(column.renderer){
                                    return column.renderer(v,obj);
                                }else{
                                    return null;
                                }
                            }(val);

                            var td,tdcls,tdwidth;

                            if(cls){
                                tdcls='class="'+cls+'"';
                            }
                            //if(width>0){
                            //    tdwidth='style="min-width:'+width+'px;"';
                            //}
                            td='<td '+(tdcls?tdcls:'')+' '+(tdwidth?tdwidth:'')+'>'+(renderer!=null?renderer:val)+'</td>';
                            tdcls=undefined;
                            tdwidth=undefined;
                            tr+= td;
                        }
                        tr+='<td class="hidden" data-id="'+obj[idProperty]+'"></td></tr>'
                        html+=tr;
                    }
                }else{
                    html+='<tr><td colspan="'+columns.length+'">当前没有记录</td></tr>'
                }
                tbody.html(html);
            });

            //setTimeout(function() {
            //    table.find('.message-loading-overlay').remove();
            //}, 300 + parseInt(Math.random() * 300));
        }
    };

    $.fn.htable = function(method) {

        // Method calling logic
        if (methods[method] && method.charAt(0) != '_') {
            return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
        } else if (typeof method === 'object' || !method) {
            return methods.init.apply(this, arguments);
        } else {
            $.error('Method ' +  method + ' does not exist on jQuery.pagination');
        }

    };

})(jQuery);

$(function(){
	var action=window.location.pathname;
	if((action.indexOf('/medcine/account/login')>-1)&&window.top!=window.self){
		top.location.href=location.href;
	};
	$(".orderNum").each(function(){
		var pre=$('<span class="input-group-addon sub">-</span>');
        var suf=$('<span class="input-group-addon add">+</span>');
        var obj=$(this);
        obj.addClass('text-center');
        var num=parseInt(obj.val()==''?1:obj.val());
        obj.val(num)
        obj.before(pre).after(suf);
        pre.click(function(){
        	var num=parseInt(obj.val());
        	obj.val(num>1?num-1:num).change();
        })
        suf.click(function(){
        	var num=parseInt(obj.val());
        	obj.val(num+1).change();
        })
	})
	$(".ztree-select").each(function(){
		var obj=$(this);
		var id=obj.attr("id");
		var url=transUrl(obj.attr("url"));
		var texthtml='<div id="'+id+'" class="input-group"><input type="text"  class="form-control" placeholder="请选择" disabled /><span class="input-group-addon btn-select">选择</span></div>';
		obj.attr("type","hidden").attr("id","");
		obj.parent().append(texthtml);
		$("#"+id).click(function(){
			var ztreehtml='<ul id="'+id+'tree" class="ztree select-ztree"></ul>';
			if($("#"+id+"tree").length>0){
				$("#"+id+"tree").remove();
			}
			obj.parent().append(ztreehtml);
			var tree=obj.parent().find("#"+id+"tree");
			var setting = {
			      data: {
			          simpleData: {
			              enable: true
			          }
			      },
					callback:{
						beforeClick: selectZTreeClick
					}
					
			};
			var zTreeObj;
			var zNodes = null;
			$.get(url,function(r){
				zNodes=r.selectlist;
				zTreeObj = $.fn.zTree.init($("#"+id+"tree"), setting, zNodes);
			})
		}).parent().mouseleave(function(){
			$("#"+id+"tree").remove();
		});
		function selectZTreeClick(treeId, treeNode, clickFlag){
			console.log("121212")
			var tree=$("#"+treeId);
			tree.parent().children("input[type=hidden]").val(treeNode.id).change();
			tree.parent().children("div").children("input[type=text]").val(treeNode.name).change();
			tree.remove();
		}
		
	});
	$(".image-upload-input").each(function(){
		uploadtool($(this).attr("id"));
		refershitem();
	})
})


var refershselectztree=function(){
	setTimeout(function(){	
	$(".ztree-select").each(function(){
		var obj=$(this);
		var value=obj.val();
		if(value!=undefined&&value.length>0){
			var infoUrl=obj.attr("info");
			$.get(transUrl(infoUrl)+value, function (res) {
				for(var key in res){
					if(key!="code"){
						console.log(res[key])
						for(var field in res[key])
							if(field=="name"){
								obj.next().children("input[type=text]").val(res[key][field]);
							}
					}
				}
				
		    });
		}else{
			obj.next().children("input[type=text]").val('');
		}
	});	
},1)
}



var refershitem=function(){
	$(".image-upload-input").each(function(){
		var e=$(this);
		setTimeout(function(){
		var url=e.val();
		console.log(e.val())
		var num=e.attr("data-num");
		var uploadButton=e.next().children(".webuploader-container");
		var list=uploadButton.siblings();
		list.remove();
		if(url!=undefined&&url.length>2){
			var arr=url.indexOf(',')>-1?url.split(','):[url];
			for(var i=0;i<arr.length;i++){
				if(arr[i].length>10){
				var itemhtml='<div id="WU_FILE_'+i+'" class="file-item upload-state-done"><img src="'+transUrl(arr[i])+'"><div class="status uploadsuccess"><span class="glyphicon glyphicon-ok"></span></div><span class="cancel delimgbtns" data-url="'+arr[i]+'"><i class=" glyphicon glyphicon-trash"></i></span></div>';
				uploadButton.before(itemhtml);
				}
			}
		}
		list=uploadButton.siblings();
		if (list.length >= num) {
			uploadButton.hide();
	    } else {
	    	uploadButton.show();
	    }
	},1)
	});
	
}

var uploadtool=function(id){
	var that=$("#"+id);
	that.attr("type","hidden");
	var num=that.attr("data-num");
	var uploadhtml='<div class="controls"><div id="'+id+'upload">添加图片</div></div>';
	that.parent().append(uploadhtml);
    var uploadButton = $('#'+id+'upload'); // 上传按钮
    var ratio = window.devicePixelRatio || 1;
    // 缩略图大小
    var thumbnailWidth = 100 * ratio;
    var thumbnailHeight = 100 * ratio;
    // 初始化Web Uploader
    var upload = WebUploader.create({
        // 自动上传。
        auto: true,
        // swf文件路径
        swf: transUrl("/statics/webupload/Uploader.swf"),
        // 文件接收服务端。
        server: transUrl("/upload/image"),  //  这里是图片上传地址
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash
        pick: {
            id: uploadButton
        },
        duplicate: true,
        fileSingleSizeLimit: 5242880, //  单个文件大小
        fileNumLimit: num, // 限制上传个数
        accept: {
            title: 'Images',
            extensions: 'jpg,jpeg,png',
            mimeTypes: 'image/jpg,image/jpeg,image/png' //修改这行
        },
        thumb: {
            width: 100,
            height: 100,
            // 图片质量，只有type为`image/jpeg`的时候才有效。
            quality: 70,
            // 是否允许放大，如果想要生成小图的时候不失真，此选项应该设置为false.
            allowMagnify: true,
            // 是否允许裁剪。
            crop: true,
            // 为空的话则保留原有图片格式。
            // 否则强制转换成指定的类型。
            type: 'image/jpeg'
        }
    });
// 当有文件添加进来的时候
upload.on('fileQueued', function(file) {
    var item = $(
            '<div id="' + file.id + 'i" class="file-item">' +
            '<img>' +
            '<div class="status"></div><span class = "cancel delimgbtns"><i class=" glyphicon glyphicon-trash"></></span>' +
            '</div>'
        );
        var img = item.find('img');
    uploadButton.before(item);
    // 创建缩略图
    upload.makeThumb(file, function(error, src) {
        if (error) {
            img.replaceWith('<span>不能预览</span>');
            return;
        }
        img.attr('src', src);
    }, thumbnailWidth, thumbnailHeight);
    var uploadfilesNum = upload.getStats().queueNum; //  共选中几个图片
    var imgitem=$('#'+id).siblings();
    // 最多支持 5张
    if (imgitem.length >= num) {
        uploadButton.hide();
        if (imgitem.length>= (num + 1)) {
            // 中断 取消 大于  5张图片的对象
            upload.removeFile(file, true);
            imgitem.last().remove();
        }
    } else {
    	uploadButton.show();
    }
});
// 文件上传过程中创建进度条实时显示。
upload.on('uploadProgress', function(file, percentage) {
	var item = $('#' + file.id+'i'),
    status =item.find('div.status');
    status.html(percentage * 100 + '%');
});

// 文件上传成功，给item添加成功class, 用样式标记上传成功。
upload.on('uploadSuccess', function(file, response) {
    var item = $('#' + file.id+'i'),
        img = item.find('img'),
        fileUrl = response.imgurl; // 图片上传地址
    item.addClass('upload-state-done');
    img.attr('src',transUrl(fileUrl));
    item.find(".delimgbtns").attr("data-url",fileUrl)
    item.children("div.status").html('<span class="glyphicon glyphicon-ok"></span>').addClass("uploadsuccess");
    var e=uploadButton.parent().parent().children("input[type=hidden]");
    var value=e.val()!=''&&e.val().length>0?e.val()+','+fileUrl:fileUrl;
    e.attr("value",value).change();
});

// 文件上传失败，显示上传出错。
upload.on('uploadError', function(file) {
	var item = $('#' + file.id+'i'),
    status = item.find('div.status');
    status.html('上传失败');
    });
// 删除按钮事件
$('.controls').on('click','.delimgbtns',function() {
	console.log($(this))
    var url=$(this).attr("data-url");
    var item = $(this).parent();
    $.get(transUrl("/upload/deleteimage?path=")+url,function(r){
    	if(r.code==0){
    		var e=item.parent().parent().children("input[type=hidden]");
    	    var value=e.val();
    	    if(value.indexOf(',')>-1){
    	    	value=value.split(',').filter(function(e){
        			return e!=url;
        		})
    	    }
    		e.attr("value",value).change();
    		item.remove();
    		upload.reset();
            uploadButton.show(); // 上传按钮显示
    	}else{
    		alert("删除失败");
    	}
    })
})

}

//重写alert
window.alert = function (msg) {
	top.layer.alert(msg, {icon: 6});
};

//重写confirm式样框
window.confirm = function(msg, callback) {
	top.layer.confirm(msg, {icon: 3, title:'请确认',shade:false}, function(index){
			callback();
		  layer.close(index);
		});
};

window.tips=function(msg){
	top.layer.open({
		  type: 0,
		  btn:[],
		  offset: 'rb',
		  skin:'layui-layer-molv',
		  title:'操作结果',
		  time:3000,
		  shade:false,
		  content: msg
		});
}

window.androidtips=function(msg){
	layer.msg(msg);
}

function number(obj){
	var _this=$(obj);
	var _input=_this.parent().find("input[type=text]");
	var val=parseInt(_input.val());
	
	if(_this.html()=='+'){
		_input.val(val+1);
	}else{
		if(val>1){
			_input.val(val-1);
		}
	}
}

function mobileView(obj){
	var w1=$(window.top).width();
	var x=(w1-400)/2;
	x=window.top!=self?x-w1*0.14:x;
	layer.open({
		  type: 1,
		  shade: false,
		  offset: ['10px', x+'px'],
		  area: '400px',
		  scrollbar: false,
		  title: "文章预览", //不显示标题
		  content: $(obj).next("div") //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
	});
}

window.viewPicture=function(obj){
	var url=$(obj).attr("url");
	var arr=url.split(",");
	var data=[];
	for(var i=0;i<arr.length;i++){
		var item={
				"alt": "图片"+i,
			     "pid": i, 
			     "src": transUrl(arr[i]), 
			     "thumb": transUrl(arr[i]) 
		};
		data.push(item);
	};
	console.log(JSON.stringify(data));
	var json={
			"title": "文章图片", //相册标题
			"id": 123, //相册id
			"start": 0, //初始显示的图片序号，默认0
			"data": data
		};
	layer.photos({
	    photos:json
	    ,anim: 5
	    ,shade:0.01
	  });
}

/**
 *
 * @param options
 */
window.openWindow = function (options) {
    let globalParams = {
        skin: 'layui-layer-molv',//皮肤
        title: '标题',//标题
        type: 2,//打开窗口的类型 1：html里的div内容 2：iframe方式，页面的路径
        closeBtn: 1, //关闭按钮的形状 0、1
        anim: -1,
        isOutAnim: false,
        shadeClose: false,
        area: ['70%', '90%'],
        maxmin: true,
        content: '',
        btn: true, //按钮
        top: true //窗口弹出是否在iframe上层
    };
    globalParams = $.extend(globalParams, options);
    if (globalParams.top) {
        parent.layer.open(globalParams);
    } else {
        layer.open(globalParams);
    }
};
window.openWindowForm = function (options) {
    let globalParams = {
        skin: 'layui-layer-molv',//皮肤
        title: '标题',//标题
        type: 2,//打开窗口的类型 1：html里的div内容 2：iframe方式，页面的路径
        closeBtn: 1, //关闭按钮的形状 0、1
        anim: -1,
        isOutAnim: false,
        shadeClose: true,
        area: ['90%', '95%'],
        maxmin: true,
        content: $('#formwrap'),
        btn: true, //按钮
        top: true //窗口弹出是否在iframe上层
    };
    globalParams = $.extend(globalParams, options);
    if (globalParams.top) {
        parent.layer.open(globalParams);
    } else {
        layer.open(globalParams);
    }
};

//获取选中的数据
function getSelectedRowData(gridId) {
    var id = getSelectedRow(gridId);
    return $(gridId).jqGrid('getRowData', id);
}

//选择一条记录
function getSelectedRow() {
	var selectedrow = $("tr.selected");
    if (selectedrow.length==0) {
    	top.layer.msg('请选择一条记录')
        return '';
    }else if(selectedrow.length>1){
    	top.layer.msg('只能选择一条记录')
    	return '';
    }
    return selectedrow.find("td.bs-checkbox").children("input").attr("value");
};

function check(r){
	if(r.code==403){
		alert("登录状态异常，请重新登录,3秒后跳转至登录界面");
		setTimeout(function(){
			window.top.location.href=transUrl("/account/login");
		},2000)
		return false;
	}else if(r.code==0){
		return true;
	}else if(r.code==-1){
		alert("数据错误");
	}
	
}

function closeForm(){
	layer.closeAll();
}

//选择一条记录
function getSelected() {
    var selectedrow = $("tr.selected");
    if (selectedrow.length==0) {
    	top.layer.msg('请选择一条记录')
        return '';
    }else if(selectedrow.length>1){
    	top.layer.msg('只能选择一条记录')
    	return '';
    }
    return selectedrow.find("td.bs-checkbox").children("input").attr("value");
};

function overflowHidden(value,num){
	if(num==undefined||num==''){
		num=19;
	}
	if(value!=undefined){
		if(value.length>num){
			return '<span data-toggle="tooltip"  title="'+value+'">'+value.substring(0,num)+'...</span>';
		}else{
			return '<span>'+value+'</span>';
		}
	}else{
		return '';
	}
}

function openForm(title,w,id){
	title=title!=undefined?title:'编辑';
	id=id!=undefined?id:'formwrap';
	w=w!=undefined?w:'550';
	var w1=$(window.top).width();
	var x=(w1-w)/2;
	x=window.top!=self?x-w1*0.14:x;
	layer.open({
		  type: 1,
		  shade: false,
		  offset: ['10px', x+'px'],
		  area: w+'px',
		  title: title, //不显示标题
		  content: $('#'+id) //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
	});
}

//选择多条记录
function getSelectedRows() {
	var selectedrow = $("tr.selected");
    if (selectedrow.length==0) {
    	top.layer.msg('请选择一条记录');
        return '';
    }else{
    	var ids=[];
    	selectedrow.each(function(index,obj){
    		ids[index]=$(this).find("td.bs-checkbox").children("input").attr("value");
    	});
    	return ids;
    }
	
};


function newtab(url,title){
	title=title.length>6?title.substring(0,5)+"..":title;
    var myDate = new Date();
    var MenuId = ''+myDate.getMinutes()+''+myDate.getSeconds();
    if(url.indexOf('http')<0){
    	var rootarr=window.location.href.split('/');
        var url =rootarr[0]+'//'+rootarr[2]+'/'+rootarr[3]+url;
    }
    if(window.top==window.self){
    	var tabNav = $("#myTab>ul:eq(0)");
        var tabContent = $("#myTab>div:eq(0)");
    }else{
    	var topdoc=$(window.top.document);
    	var tabNav =topdoc.find("#myTab>ul:eq(0)");
        var tabContent = topdoc.find("#myTab>div:eq(0)");
    }
    var index = tabNav.children("li.tab" + MenuId).index();
    var activeli = tabNav.children("li.active");
    var activediv = tabContent.children("div.active");
    var sumWidth = 0;
    tabNav.children().each(function () {
            sumWidth += $(this).width();
        })
    if (tabNav.width() < sumWidth+140) {
            alert("窗口数量达到最大，请关闭部分窗口后重新打开新窗口");
    } else {
       var pagetitle = '<li url="'+url+'" class="active tab' + MenuId + '"><a href="#tab' + MenuId + '" data-toggle="tab"><span>' + title + '</span><strong class="glyphicon glyphicon-remove"></strong></a></li>';
       var page = '<div class="tab-pane fade active in" id="tab' + MenuId + '"><iframe id="iframe'+MenuId+'" style=" min-height:' + tabContent.children("div").height()+'px;width:100%;" scrolling="auto" frameborder="0" src="'+url+'"></iframe></div>';
       activeli.removeClass("active");
       activediv.removeClass("active in");
       tabNav.append(pagetitle);
       tabContent.append(page);
    }
}

function transUrl(url){
	var rootarr=window.location.href.split('/');
    return rootarr[0]+'//'+rootarr[2]+'/'+rootarr[3]+url;
}


/**
 * 翻译性别
 * @param gender
 * @returns {*}
 */
function transGender(gender) {
    if (gender == 1) {
        return '男';
    }
    if (gender == 2) {
        return '女';
    }
    return '未知';
};

function transIcon(value){
	return '<span class="'+value+'"></span>';
}

function GetUrlParam(paraName) {
　　　　var url = document.location.toString();
　　　　var arrObj = url.split("?");

　　　　if (arrObj.length > 1) {
　　　　　　var arrPara = arrObj[1].split("&");
　　　　　　var arr;

　　　　　　for (var i = 0; i < arrPara.length; i++) {
　　　　　　　　arr = arrPara[i].split("=");

　　　　　　　　if (arr != null && arr[0] == paraName) {
　　　　　　　　　　return arr[1];
　　　　　　　　}
　　　　　　}
　　　　　　return "";
　　　　}
　　　　else {
　　　　　　return "";
　　　　}
　　}


function transDirectoryType(value){
	if(value==0){
		return '<span class="btn btn-success btn-xs">目录</span>';
	}else if(value==1){
		return '<span class="btn btn-warning btn-xs">菜单</span>';
	}else{
		return '<span class="btn btn-default btn-xs">按钮</span>';
	}
}

function transIsNot(value) {
    if (value == 1) {
        return '<span class="label label-success">是</span>';
    }
    return '<span class="label label-danger">否</span>';
};

function transMenuStatus(value){
	 if (value == 1) {
	        return '<span class="label label-success">启用</span>';
	    }
	    return '<span class="label label-danger">禁用</span>';
}

function transAccountStatus(value) {
    if (value == 1) {
        return '<span class="btn btn-success btn-xs">正常</span>';
    }else if(value==-1){
    	return '<span class="btn btn-default btn-xs">锁定</span>';
    }else{
    	'<span class="btn btn-warning btn-xs">异常</span>';
    }
};

function tipError(error, element){
	element.parent().next().html(error);
}

function transStatus(value) {
    if (value == 1) {
        return '<span class="btn btn-success btn-xs">有效</span>';
    }
    return '<span class="btn btn-default btn-xs">无效</span>';
};

var openqr=function(url){
	$("#qr").html('');
    var w1=$(window.top).width();
    var x=(w1-300)/2;
    x=window.top!=self?x-w1*0.14:x;
    layer.open({
        type: 1,
        title: false,
        closeBtn: 0,
        offset: ['50px', x+'px'],
        shade:0.01,
        shadeClose: true,
        content: '<div id="qrwrap" class="qrwrap"><p class="qr-text text-center">超市扫描系统</p><div id="qr" class="qr"></div><p class="qr-text text-center">请使用手机扫码</p></div>',
		success:function () {
            url=transUrl(url);
            // 设置参数方式
            var qrcode = new QRCode('qr', {
                text: url,
                width: 200,
                height: 200,
                colorDark : '#000000',
                colorLight : '#ffffff',
                correctLevel : QRCode.CorrectLevel.H
            });
        }
    });
}

function toUrl(href) {
    window.location.href = href;
}

/**
 * 用JS获取地址栏参数的方法
 * @param name
 * @returns {null}
 * @constructor
 */
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}

/**
 * 主要功能:导出功能公共方法
 *
 * @param formId 表单ID,带'#'号,如'#formId'
 * @param url 请求后台地址
 * @param extraObj 往后台请求额外参数,对象格式,如:{'aaa': 111}
 */
function exportFile(formId, url, extraObj) {
    var form = $('<form>'); //定义一个form表单
    form.attr('style', 'display: none');
    form.attr('target', '');
    form.attr('method', 'post');
    form.attr('action', url);

    var json = getJson(formId);
    if (typeof extraObj != 'undefined') {
        json = $.extend(json, extraObj);
    }

    $('body').append(form);//将表单放置在web中
    for (var i in json) {
        var input = $('<input>');
        input.attr('type', 'hidden');
        input.attr('name', i);
        input.attr('value', json[i]);
        form.append(input);
    }

    form.submit();//表单提交
}

/**
 * 将form转化为json
 * @param form 传入 form表单的dom $("#baseFm")
 * @returns {___anonymous49_50}  序列化的键值对 {key:value,key2:value2,....}
 */
function getJson(form) {
    var o = {};
    var $form = $(form).find('input,textarea,select');
    $.each($form, function (i, item) {
        var $this = $(item);

        if ($this.attr("type") == 'radio') {
            o[$this.attr("name")] = $("input[name='" + $this.attr("name") + "']:checked").val();
            return true;
        }
        if ($this.hasClass("rate")) {
            o[$this.attr("name")] = parseFloat($this.val().toString().replace(/\$|\,/g, '')) * parseFloat($this.attr("unit"));
        } else {
            o[$this.attr("name")] = $this.val();
        }
    })
    return o;
}


Ajax = function () {

    //var opt = { type:'GET',dataType:'json',resultMsg:true };
    function request(opt) {

        if (typeof opt.cache == 'undefined') {
            opt.cache = false;
        }

        if (typeof opt == 'undefined') {
            return;
        }
        //opt = $.extend(opt, p);
        if (typeof(opt.type) == 'undefined') {
            opt.type = 'GET'
        }
        if (typeof(opt.async) == 'undefined') {
            opt.async = false;
        }
        if (typeof(opt.dataType) == 'undefined') {
            opt.dataType = 'json'
        }
        if (typeof(opt.contentType) == 'undefined') {
            opt.contentType = 'application/x-www-form-urlencoded;chartset=UTF-8'
        }
        if (typeof(opt.params) == 'undefined' || opt.params == null) {
            opt.params = {};
        }
        opt.params.date = new Date();
        if (typeof(opt.beforeSubmit) != 'undefined') {
            var flag = opt.beforeSubmit(opt);
            if (!flag) {
                return;
            }
        }

        if (typeof(opt.resultMsg) == 'undefined') {
            opt.resultMsg = true;
        }

        $.ajax({
            async: opt.async,
            url: opt.url,
            dataType: opt.dataType,
            contentType: opt.contentType,
            data: opt.params,
            crossDomain: opt.crossDomain || false,
            type: opt.type,
            cache: opt.cache,
            success: function (data) {
                if (typeof data == 'string' && data.indexOf("exception") > 0 || typeof data.code != 'undefined' && data.code != '0') {
                    var result = {code: null};
                    if (typeof data == 'string') {
                        result = eval('(' + data + ')')
                    } else if (typeof data == 'object') {
                        result = data;
                    }

                    if (opt.resultMsg && result.msg) {
                        layer.alert(result.msg, {icon: 5});
                    }
                    return;
                }
                if (opt.resultMsg && data.msg) {
                    layer.alert(data.msg, {icon: 6}, function () {
                        if (typeof(opt.successCallback) != 'undefined') {
                            opt.successCallback(data);
                        }
                    });
                    return;
                }

                if (typeof(opt.successCallback) != 'undefined') {
                    opt.successCallback(data);
                }
            },
            error: function () {
                layer.alert("此页面发生未知异常,请联系管理员", {icon: 5});
            }
        });
    }

    return {
        /**
         * Ajax调用request
         */
        request: request
    };
}();
//iframe自适应
$(window).on('resize', function () {
    var $content = $('#mainApp');
    $content.height($(this).height());
    $content.find('iframe').each(function () {
        $(this).height($content.height() - 150);
    });
    var $rrapp = $('#rrapp').parent();
    $rrapp.height($(this).height());
    $(this).height($content.height());
}).resize();
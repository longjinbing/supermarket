		var vue = new Vue({
		el : '#content',
		data : {
			sysSmsLog : {}
		},
		methods : {
			addForm : function() {
				vue.sysSmsLog = {};
				openForm("新增");
			},
			editForm : function() {
				var id=getSelectedRow();
				if (id != '') {
					var url=transUrl("/syssmslog/info/");
					$.get(url+ id, function (r) {
						if(check(r)){
						vue.sysSmsLog=r.sysSmsLog;
						openForm("修改信息");
						}
				    });
					if(vue.sysSmsLog==null){alert("网络错误")}
				}
				
			},
			deleteData : function() {
				var id = getSelectedRows();
				if (id != '') {
					confirm('确定要删除选中的记录？', function() {
						var url=transUrl("/syssmslog/delete");
						$.ajax({
							type : "POST",
							url : url,
							contentType : "application/json",
							data : JSON.stringify(id),
							success : function(r) {
								if(check(r)){
									vue.reload();
									tips("删除成功");
								}
							},
							error : function() {
								alert("网络错误，请稍后重试");
							}
						});
					});
				}
			},
			saveOrUpdate : function() {
				var url = vue.sysSmsLog.id == null ? transUrl("/syssmslog/save") : transUrl("/syssmslog/update");
				$.ajax({
					type : "POST",
					url : url,
					contentType : "application/json",
					data : JSON.stringify(vue.sysSmsLog),
					success : function(r) {
						if(check(r)){
							vue.reload();
							closeForm();
							tips("操作成功");
						}
					}
				});
			},
			reload : function() {
				var table=$("tr.selected").eq(0).closest("table");
				table=table.length==0?$('#datatable'):table;
				table.bootstrapTable('refresh', {query:{
					searchField : $("#searchField").val(),
					searchText : $("#searchText").val(),
					startTime : "",
					endTime : ""
				},'silent': true});
			},
			timeQuery : function() {
				$('#datatable').bootstrapTable('refresh', {query:{
					searchField : "",
					searchText : "",
					startTime : $("#startTime").val(),
					endTime : $("#endTime").val()
				},'silent': true});
			},
			exportData : function(exportType) {
				$('#datatable').tableExport({
					type : exportType,
					escape : 'false',
					fileName : 'sysSmsLog'
				})
			}
		}
	});
	$(function() {
		var url=transUrl("/syssmslog/list");
		var table = new BSTable("datatable", url,
				initColumn());
		table.init();
		$("#modal-form").validate({
			rules : {			
				userId : {
					required : true
				},
				content : {
					required : true
				},
				mobile : {
					required : true
				},
				createTime : {
					required : true
				},
				sign : {
					required : true
				},
				type : {
					required : true
				},
				extno : {
					required : true
				},
				sendStatus : {
					required : true
				},
				sendId : {
					required : true
				},
				invalidNum : {
					required : true
				},
				successNum : {
					required : true
				},
				blackNum : {
					required : true
				},
				returnMsg : {
					required : true
				}			},
			messages : {
				userId : {
					required : "请填写操作人"
				},
				content : {
					required : "请填写必填参数。发送内容（1-500 个汉字）UTF-8编码"
				},
				mobile : {
					required : "请填写必填参数。手机号码。多个以英文逗号隔开"
				},
				createTime : {
					required : "请填写可选参数。发送时间，填写时已填写的时间发送，不填时为当前时间发送"
				},
				sign : {
					required : "请填写必填参数。用户签名"
				},
				type : {
					required : "请填写必填参数。固定值 pt"
				},
				extno : {
					required : "请填写可选参数。扩展码，用户定义扩展码，只能为数字"
				},
				sendStatus : {
					required : "请填写1成功 0失败"
				},
				sendId : {
					required : "请填写发送编号"
				},
				invalidNum : {
					required : "请填写无效号码数"
				},
				successNum : {
					required : "请填写成功提交数"
				},
				blackNum : {
					required : "请填写黑名单数"
				},
				returnMsg : {
					required : "请填写返回消息"
				}			},
			submitHandler : function(form) {
				vue.saveOrUpdate();
			},
			errorPlacement : function(error, element) {
				tipError(error, element);
			}
		});
	});
	var initColumn = function() {
		return [ {
			checkbox : true
		}, 	{
			title : '序号',
			field : 'id',
			align : 'left',
			visible : true,
			valign : 'middle',
			formatter : function(value, row, index) {
				return autoId("datatable", index);
		}}, 	{
			title : '主键',
			field : 'id',
			align : 'left',
			visible : false,
			valign : 'middle'
		},{
			title : '操作人',
			field : 'userId',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},{
			title : '必填参数。发送内容（1-500 个汉字）UTF-8编码',
			field : 'content',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},{
			title : '必填参数。手机号码。多个以英文逗号隔开',
			field : 'mobile',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},{
			title : '可选参数。发送时间，填写时已填写的时间发送，不填时为当前时间发送',
			field : 'createTime',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},{
			title : '必填参数。用户签名',
			field : 'sign',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},{
			title : '必填参数。固定值 pt',
			field : 'type',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},{
			title : '可选参数。扩展码，用户定义扩展码，只能为数字',
			field : 'extno',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},{
			title : '1成功 0失败',
			field : 'sendStatus',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},{
			title : '发送编号',
			field : 'sendId',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},{
			title : '无效号码数',
			field : 'invalidNum',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},{
			title : '成功提交数',
			field : 'successNum',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},{
			title : '黑名单数',
			field : 'blackNum',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},{
			title : '返回消息',
			field : 'returnMsg',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		} ];
	};

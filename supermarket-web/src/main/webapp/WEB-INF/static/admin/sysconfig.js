		var vue = new Vue({
		el : '#content',
		data : {
			sysConfig : {}
		},
		methods : {
			addForm : function() {
				vue.sysConfig = {};
				openForm("新增");
			},
			editForm : function() {
				var id=getSelectedRow();
				if (id != '') {
					var url=transUrl("/sysconfig/info/");
					$.get(url+ id, function (r) {
						if(check(r)){
						vue.sysConfig=r.sysConfig;
						openForm("修改信息");
						}
				    });
					if(vue.sysConfig==null){alert("网络错误")}
				}
				
			},
			deleteData : function() {
				var id = getSelectedRows();
				if (id != '') {
					confirm('确定要删除选中的记录？', function() {
						var url=transUrl("/sysconfig/delete");
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
				var url = vue.sysConfig.id == null ? transUrl("/sysconfig/save") : transUrl("/sysconfig/update");
				$.ajax({
					type : "POST",
					url : url,
					contentType : "application/json",
					data : JSON.stringify(vue.sysConfig),
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
					fileName : 'sysConfig'
				})
			}
		}
	});
	$(function() {
		var url=transUrl("/sysconfig/list");
		var table = new BSTable("datatable", url,
				initColumn());
		table.init();
		$("#modal-form").validate({
			rules : {			
				item : {
					required : true
				},
				value : {
					required : true
				},
				status : {
					required : true
				},
				remark : {
					required : true
				},
				isDelete : {
					required : true
				},
				createId : {
					required : true
				},
				createTime : {
					required : true
				}			},
			messages : {
				item : {
					required : "请填写key"
				},
				value : {
					required : "请填写value"
				},
				status : {
					required : "请填写状态   0：隐藏   1：显示"
				},
				remark : {
					required : "请填写备注"
				},
				isDelete : {
					required : "请填写"
				},
				createId : {
					required : "请填写创建者ID"
				},
				createTime : {
					required : "请填写创建时间"
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
			title : 'key',
			field : 'item',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},{
			title : 'value',
			field : 'value',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},{
			title : '状态   0：隐藏   1：显示',
			field : 'status',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},{
			title : '备注',
			field : 'remark',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},{
			title : '',
			field : 'isDelete',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},{
			title : '创建者ID',
			field : 'createId',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},{
			title : '创建时间',
			field : 'createTime',
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

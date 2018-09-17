		var vue = new Vue({
		el : '#content',
		data : {
			storeSupplier : {}
		},
		methods : {
			addForm : function() {
				vue.storeSupplier = {};
				openForm("新增");
			},
			editForm : function() {
				var id=getSelectedRow();
				if (id != '') {
					var url=transUrl("/storesupplier/info/");
					$.get(url+ id, function (r) {
						if(check(r)){
						vue.storeSupplier=r.storeSupplier;
						openForm("修改信息");
						}
				    });
					if(vue.storeSupplier==null){alert("网络错误")}
				}
				
			},
			details:function(){
				var id = getSelectedRow();
				if (id != '') {
					var url = transUrl("/storesupplier/details/")+id;
					$.get(url, function(r) {
						if (check(r)) {
							vue.storeSupplier= r.storeSupplier;
							openForm("查看详情",550,"detailswrap");
						}
					});
				}
			},
			deleteData : function() {
				var id = getSelectedRows();
				if (id != '') {
					confirm('确定要删除选中的记录？', function() {
						var url=transUrl("/storesupplier/delete");
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
				var url = vue.storeSupplier.id == null ? transUrl("/storesupplier/save") : transUrl("/storesupplier/update");
				$.ajax({
					type : "POST",
					url : url,
					contentType : "application/json",
					data : JSON.stringify(vue.storeSupplier),
					success : function(r) {
						if(check(r)){
							vue.reload();
							vue.$data.storeSupplier={};
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
					fileName : 'storeSupplier'
				})
			}
		}
	});
	$(function() {
		var url=transUrl("/storesupplier/list");
		var table = new BSTable("datatable", url,
				initColumn());
		table.init();
		$("#modal-form").validate({
			rules : {			
				name : {
					required : true
				},
				username : {
					required : true
				},
				address : {
					required : true
				},
				phone : {
					required : true
				},
				qq : {
					required : true
				},
			},
			messages : {
				name : {
					required : "请填写供应商"
				},
				username : {
					required : "请填写联系人"
				},
				address : {
					required : "请填写地址"
				},
				phone : {
					required : "请填写联系电话"
				},
				qq : {
					required : "请填写QQ号码"
				},
			},
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
		}}, 
			{
			title : '供应商',
			field : 'name',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},			{
			title : '联系人',
			field : 'username',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},			{
			title : '地址',
			field : 'address',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},			{
			title : '联系电话',
			field : 'phone',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},			{
			title : '添加时间',
			field : 'createTime',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},			{
			title : 'QQ号码',
			field : 'qq',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},			{
			title : '更新时间',
			field : 'updateTime',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		}, ];
	};

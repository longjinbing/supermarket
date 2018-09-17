		var vue = new Vue({
		el : '#content',
		data : {
			storeUser : {}
		},
		methods : {
			addForm : function() {
				vue.storeUser = {};
				openForm("新增");
			},
			editForm : function() {
				var id=getSelectedRow();
				if (id != '') {
					var url=transUrl("/storeuser/info/");
					$.get(url+ id, function (r) {
						if(check(r)){
						vue.storeUser=r.storeUser;
						openForm("修改信息");
						}
				    });
					if(vue.storeUser==null){alert("网络错误")}
				}
				
			},
			details:function(){
				var id = getSelectedRow();
				if (id != '') {
					var url = transUrl("/storeuser/details/")+id;
					$.get(url, function(r) {
						if (check(r)) {
							vue.storeUser= r.storeUser;
							openForm("查看详情",550,"detailswrap");
						}
					});
				}
			},
			deleteData : function() {
				var id = getSelectedRows();
				if (id != '') {
					confirm('确定要删除选中的记录？', function() {
						var url=transUrl("/storeuser/delete");
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
				var url = vue.storeUser.id == null ? transUrl("/storeuser/save") : transUrl("/storeuser/update");
				$.ajax({
					type : "POST",
					url : url,
					contentType : "application/json",
					data : JSON.stringify(vue.storeUser),
					success : function(r) {
						if(check(r)){
							vue.reload();
							vue.$data.storeUser={};
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
					fileName : 'storeUser'
				})
			}
		}
	});
	$(function() {
		var url=transUrl("/storeuser/list");
		var table = new BSTable("datatable", url,
				initColumn());
		table.init();
		$("#modal-form").validate({
			rules : {			
				name : {
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
				level : {
					required : true
				},
				status : {
					required : true
				},
			},
			messages : {
				name : {
					required : "请填写姓名"
				},
				address : {
					required : "请填写家庭住址"
				},
				phone : {
					required : "请填写电话"
				},
				qq : {
					required : "请填写QQ号码"
				},
				level : {
					required : "请填写会员等级"
				},
				status : {
					required : "请填写会员状态"
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
			title : '姓名',
			field : 'name',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'8%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},			{
			title : '家庭住址',
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
			title : '电话',
			field : 'phone',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'8%',
			formatter:function(value,row,index){
				return overflowHidden(value,11);
			}
		},			{
			title : 'QQ号码',
			field : 'qq',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'8%',
			formatter:function(value,row,index){
				return overflowHidden(value,11);
			}
		},			{
			title : '会员等级',
			field : 'level',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'8%',
			formatter:function(value,row,index){
				return overflowHidden(value,6);
			}
		},			{
			title : '会员状态',
			field : 'status',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'5%',
			formatter:function(value,row,index){
				return overflowHidden(value,5);
			}
		},			{
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
		}];
	};

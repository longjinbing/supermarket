		var vue = new Vue({
		el : '#content',
		data : {
			storePosition : {}
		},
		methods : {
			addForm : function() {
				vue.storePosition = {};
				openForm("新增");
			},
			editForm : function() {
				var id=getSelectedRow();
				if (id != '') {
					var url=transUrl("/storeposition/info/");
					$.get(url+ id, function (r) {
						if(check(r)){
						vue.storePosition=r.storePosition;
						openForm("修改信息");
						}
				    });
					if(vue.storePosition==null){alert("网络错误")}
				}
				
			},
			details:function(){
				var id = getSelectedRow();
				if (id != '') {
					var url = transUrl("/storeposition/details/")+id;
					$.get(url, function(r) {
						if (check(r)) {
							vue.storePosition= r.storePosition;
							openForm("查看详情",550,"detailswrap");
						}
					});
				}
			},
			deleteData : function() {
				var id = getSelectedRows();
				if (id != '') {
					confirm('确定要删除选中的记录？', function() {
						var url=transUrl("/storeposition/delete");
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
				var url = vue.storePosition.id == null ? transUrl("/storeposition/save") : transUrl("/storeposition/update");
				$.ajax({
					type : "POST",
					url : url,
					contentType : "application/json",
					data : JSON.stringify(vue.storePosition),
					success : function(r) {
						if(check(r)){
							vue.reload();
							vue.$data.storePosition={};
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
					fileName : 'storePosition'
				})
			}
		}
	});
	$(function() {
		var url=transUrl("/storeposition/list");
		var table = new BSTable("datatable", url,
				initColumn());
		table.init();
		$("#modal-form").validate({
			rules : {			
				name : {
					required : true
				},
				remark : {
					required : true
				},
				positionSn : {
					required : true
				},
			},
			messages : {
				name : {
					required : "请填写货架名称"
				},
				remark : {
					required : "请填写货架位置"
				},
				positionSn : {
					required : "请填写货架编号"
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
			title : '货架名称',
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
			title : '货架位置',
			field : 'remark',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},			{
			title : '货架编号',
			field : 'positionSn',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},			{
			title : '',
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
			title : '',
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

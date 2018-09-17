		var vue = new Vue({
		el : '#content',
		data : {
			storeOrder : {}
		},
		methods : {
			addForm : function() {
				vue.storeOrder = {};
				openForm("新增");
			},
			editForm : function() {
				var id=getSelectedRow();
				if (id != '') {
					var url=transUrl("/storeorder/info/");
					$.get(url+ id, function (r) {
						if(check(r)){
						vue.storeOrder=r.storeOrder;
						openForm("修改信息");
						}
				    });
					if(vue.storeOrder==null){alert("网络错误")}
				}
				
			},
			details:function(){
				var id = getSelectedRow();
				if (id != '') {
					var url = transUrl("/storeorder/details/")+id;
					$.get(url, function(r) {
						if (check(r)) {
							vue.storeOrder= r.storeOrder;
							openForm("查看详情",550,"detailswrap");
						}
					});
				}
			},
			deleteData : function() {
				var id = getSelectedRows();
				if (id != '') {
					confirm('确定要删除选中的记录？', function() {
						var url=transUrl("/storeorder/delete");
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
				var url = vue.storeOrder.id == null ? transUrl("/storeorder/save") : transUrl("/storeorder/update");
				$.ajax({
					type : "POST",
					url : url,
					contentType : "application/json",
					data : JSON.stringify(vue.storeOrder),
					success : function(r) {
						if(check(r)){
							vue.reload();
							vue.$data.storeOrder={};
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
					fileName : 'storeOrder'
				})
			}
		}
	});
	$(function() {
		var url=transUrl("/storeorder/list");
		var table = new BSTable("datatable", url,
				initColumn());
		table.init();
		$("#modal-form").validate({
			rules : {			
				orderSn : {
					required : true
				},
				userId : {
					required : true
				},
				status : {
					required : true
				},
				orderPrice : {
					required : true
				},
				goodsPrice : {
					required : true
				},
				parentId : {
					required : true
				},
			},
			messages : {
				orderSn : {
					required : "请填写订单号"
				},
				userId : {
					required : "请填写用户"
				},
				status : {
					required : "请填写状态"
				},
				orderPrice : {
					required : "请填写结算价格"
				},
				goodsPrice : {
					required : "请填写商品总价"
				},
				parentId : {
					required : "请填写总订单Id"
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
			title : '订单号',
			field : 'orderSn',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},			{
			title : '用户',
			field : 'userId',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return "非会员";
			}
		},			{
			title : '状态',
			field : 'status',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return transAccountStatus(value);
			}
		},			{
			title : '结算价格',
			field : 'orderPrice',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},			{
			title : '商品总价',
			field : 'goodsPrice',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},			{
			title : '购买时间',
			field : 'createTime',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},	{
			title : '修改时间',
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

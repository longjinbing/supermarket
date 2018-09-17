		var vue = new Vue({
		el : '#content',
		data : {
			storeCart : {}
		},
		methods : {
			addForm : function() {
				vue.storeCart = {};
				openForm("新增");
			},
			editForm : function() {
				var id=getSelectedRow();
				if (id != '') {
					var url=transUrl("/storecart/info/");
					$.get(url+ id, function (r) {
						if(check(r)){
						vue.storeCart=r.storeCart;
						openForm("修改信息");
						}
				    });
					if(vue.storeCart==null){alert("网络错误")}
				}
				
			},
			details:function(){
				var id = getSelectedRow();
				if (id != '') {
					var url = transUrl("/storecart/details/")+id;
					$.get(url, function(r) {
						if (check(r)) {
							vue.storeCart= r.storeCart;
							openForm("查看详情",550,"detailswrap");
						}
					});
				}
			},
			deleteData : function() {
				var id = getSelectedRows();
				if (id != '') {
					confirm('确定要删除选中的记录？', function() {
						var url=transUrl("/storecart/delete");
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
				var url = vue.storeCart.id == null ? transUrl("/storecart/save") : transUrl("/storecart/update");
				$.ajax({
					type : "POST",
					url : url,
					contentType : "application/json",
					data : JSON.stringify(vue.storeCart),
					success : function(r) {
						if(check(r)){
							vue.reload();
							vue.$data.storeCart={};
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
					fileName : 'storeCart'
				})
			}
		}
	});
	$(function() {
		var url=transUrl("/storecart/list");
		var table = new BSTable("datatable", url,
				initColumn());
		table.init();
		$("#modal-form").validate({
			rules : {			
				userId : {
					required : true
				},
				goodsId : {
					required : true
				},
				goodsName : {
					required : true
				},
				marketPrice : {
					required : true
				},
				price : {
					required : true
				},
				number : {
					required : true
				},
				picUrl : {
					required : true
				},
			},
			messages : {
				userId : {
					required : "请填写会员Id"
				},
				goodsId : {
					required : "请填写商品Id"
				},
				goodsName : {
					required : "请填写产品名称"
				},
				marketPrice : {
					required : "请填写市场价"
				},
				price : {
					required : "请填写零售价格"
				},
				number : {
					required : "请填写数量"
				},
				picUrl : {
					required : "请填写商品图片"
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
			title : '会员Id',
			field : 'userId',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},			{
			title : '商品Id',
			field : 'goodsId',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},			{
			title : '产品名称',
			field : 'goodsName',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},			{
			title : '市场价',
			field : 'marketPrice',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},			{
			title : '零售价格',
			field : 'price',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},			{
			title : '数量',
			field : 'number',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},			{
			title : '商品图片',
			field : 'picUrl',
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

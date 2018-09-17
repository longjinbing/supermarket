		var vue = new Vue({
		el : '#content',
		data : {
			storeCategory : {},
			positionList:[]
		},
		methods : {
			addForm : function() {
				vue.storeCategory = {};
				vue.loadPositionList();
				openForm("新增");
			},
			editForm : function() {
				var id=getSelectedRow();
				if (id != '') {
					var url=transUrl("/storecategory/info/");
					$.get(url+ id, function (r) {
						if(check(r)){
						vue.storeCategory=r.storeCategory;
						vue.loadPositionList();
						openForm("修改信息");
						}
				    });
					if(vue.storeCategory==null){alert("网络错误")}
				}
				
			},
			loadPositionList:function(){
                $.get(transUrl("/storeposition/queryAll"), function(r) {
                    if (check(r)) {
                            if(vue.storeCategory.id!=undefined){
                            var result=[];
                            $.each(r.data,function (index,item) {
                                var obj=item;
                                if(item.id==vue.storeCategory.id){
                                    obj.selected="selected";
                                }
                                result.push(obj);
                            })
                                console.log(result)
                            vue.positionList=result;
                        }else{
                            vue.positionList=r.data;
                        }
                    }
                });
			},
			details:function(){
				var id = getSelectedRow();
				if (id != '') {
					var url = transUrl("/storecategory/details/")+id;
					$.get(url, function(r) {
						if (check(r)) {
							vue.storeCategory= r.storeCategory;
							openForm("查看详情",550,"detailswrap");
						}
					});
				}
			},
			deleteData : function() {
				var id = getSelectedRows();
				if (id != '') {
					confirm('确定要删除选中的记录？', function() {
						var url=transUrl("/storecategory/delete");
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
				var url = vue.storeCategory.id == null ? transUrl("/storecategory/save") : transUrl("/storecategory/update");
				$.ajax({
					type : "POST",
					url : url,
					contentType : "application/json",
					data : JSON.stringify(vue.storeCategory),
					success : function(r) {
						if(check(r)){
							vue.reload();
							vue.$data.storeCategory={};
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
					fileName : 'storeCategory'
				})
			}
		}
	});
	$(function() {
		var url=transUrl("/storecategory/list");
		var table = new BSTable("datatable", url,
				initColumn());
		table.init();
		$("#modal-form").validate({
			rules : {			
				name : {
					required : true
				},
				orderNum : {
					required : true
				},
				status : {
					required : true
				},
				positionId : {
					required : true
				},
			},
			messages : {
				name : {
					required : "请填写名称"
				},
				orderNum : {
					required : "请填写排序"
				},
				status : {
					required : "请填写状态"
				},
				positionId : {
					required : "请填写货架号"
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
			title : '名称',
			field : 'name',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},				{
			title : '排序',
			field : 'orderNum',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},					{
			title : '状态',
			field : 'status',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},			{
			title : '货架号',
			field : 'positionId',
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

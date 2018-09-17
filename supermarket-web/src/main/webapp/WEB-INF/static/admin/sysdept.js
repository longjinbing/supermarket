		var vue = new Vue({
		el : '#content',
		data : {
			sysDept : {},
			parentId:0
		},
		methods : {
			addForm : function() {
				vue.sysDept = {};
				vue.sysDept.parentId=vue.parentId;
				openForm("新增");
			},
			editForm : function() {
				var id=getSelectedRow();
				if (id != '') {
					var url="/sysdept/info/";
					$.get(url+ id, function (r) {
						if(check(r)){
						vue.sysDept=r.sysDept;
						openForm("修改信息");
						}
				    });
					if(vue.sysDept==null){alert("网络错误")}
				}
				
			},
			deleteData : function() {
				var id = getSelectedRows();
				if (id != '') {
					confirm('确定要删除选中的记录？', function() {
						var url=transUrl("/sysdept/delete");
						$.ajax({
							type : "POST",
							url : url,
							contentType : "application/json",
							data : JSON.stringify(id),
							success : function(r) {
								if(check(r)){
									vue.reload();
									var treeObj = $.fn.zTree.getZTreeObj("dept-tree");
									treeObj.destroy();
									$.get(transUrl("/sysdept/deptlist"),function(r){
										zNodes=r.selectlist;
										/*获取服务器数据*/
										treeObj = $.fn.zTree.init($("#dept-tree"), setting, zNodes);
									});
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
				var url = vue.sysDept.id == null ? transUrl("/sysdept/save") : transUrl("/sysdept/update");
				$.ajax({
					type : "POST",
					url : url,
					contentType : "application/json",
					data : JSON.stringify(vue.sysDept),
					success : function(r) {
						if(check(r)){
							vue.reload();
							var treeObj = $.fn.zTree.getZTreeObj("dept-tree");
							treeObj.destroy();
							$.get(transUrl("/sysdept/deptlist"),function(r){
								zNodes=r.selectlist;
								/*获取服务器数据*/
								treeObj = $.fn.zTree.init($("#dept-tree"), setting, zNodes);
							});
							layer.closeAll();
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
					fileName : 'sysDept'
				})
			}
		}
	});
	$(function() {
		var url=transUrl("/sysdept/list?id=0");
		var table = new BSTable("datatable", url,
				initColumn());
		table.init();
		$('#'+vue.$data.tableId).on('post-body.bs.table',function(){
			$(".getUser").each(function(){
				getUser(this);
			})
		})
		$("#modal-form").validate({
			rules : {			
				parentId : {
					required : true
				},
				name : {
					required : true
				},
				orderNum : {
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
				parentId : {
					required : "请填写上级部门ID，一级部门为0"
				},
				name : {
					required : "请填写部门名称"
				},
				orderNum : {
					required : "请填写排序"
				},
				isDelete : {
					required : "请填写是否删除  -1：已删除  0：正常"
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
		}}, {
			title : '主键',
			field : 'id',
			align : 'left',
			visible : false,
			valign : 'middle'
		},{
			title : '部门名称',
			field : 'name',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},{
			title : '排序',
			field : 'orderNum',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},{
			title : '创建者',
			field : 'createId',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%'
		},{
			title : '创建时间',
			field : 'createTime',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		} ];
	};
	var setting = {
			 view: {
	          selectedMulti: false
	      },
	      data: {
	          simpleData: {
	              enable: true
	          }
	      },
			check:{
				enable: false,
				chkStyle: "checkbox",
				chkboxType: { "Y": "p", "N": "s" }
			},
			callback: {
				onClick: zTreeOnClick
			}
			
	};
	$.get(transUrl("/sysdept/deptlist"),function(r){
		var zNodes=r.selectlist;
		var zTreeObj = $.fn.zTree.init($("#dept-tree"), setting, zNodes);
	})
	function zTreeOnClick(event, treeId, treeNode){
		var url=transUrl("/sysdept/list?id=")+treeNode.id;
		$('#datatable').bootstrapTable('refresh', {'silent': true,'url':url});
		vue.$data.parentId=treeNode.id;
		event.preventDefault();
		event.stopPropagation();
	}


	

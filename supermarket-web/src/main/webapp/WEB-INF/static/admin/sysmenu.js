		var vue = new Vue({
		el : '#content',
		data : {
			sysMenu : {},
			parentId: 0
		},
		methods : {
			addForm : function() {
				vue.sysMenu = {};
				vue.sysMenu.parentId=vue.parentId;
				openForm("新增");
			},
			editForm : function() {
				var id=getSelectedRow();
				if (id != '') {
					var url=transUrl("/sysmenu/info/");
					$.get(url+ id, function (r) {
						if(check(r)){
						vue.sysMenu=r.sysMenu;
						openForm("修改信息");
						}
				    });
					if(vue.sysMenu==null){alert("网络错误")}
				}
				
			},
			deleteData : function() {
				var id = getSelectedRows();
				if (id != '') {
					confirm('确定要删除选中的记录？', function() {
						var url=transUrl("/sysmenu/delete");
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
			refershMenu:function(){
				var url=transUrl("/sysmenu/refersh");
				$.get(url, function (r) {
					if(check(r)){
						vue.reload();
						tips("菜单重置成功");
					}
			    });
			},
			saveOrUpdate : function() {
				var url = vue.sysMenu.id == null ? transUrl("/sysmenu/save") : transUrl("/sysmenu/update");
				$.ajax({
					type : "POST",
					url : url,
					contentType : "application/json",
					data : JSON.stringify(vue.sysMenu),
					success : function(r) {
						if(check(r)){
                            vue.$data.sysMenu={};
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
				var zTreeObj = $.fn.zTree.getZTreeObj("menu-tree");
				zTreeObj.destroy();
				$.get(transUrl("/sysmenu/ztreelist"),function(r){
					var zNodes=r.selectlist;
					/*获取服务器数据*/
					zTreeObj = $.fn.zTree.init($("#menu-tree"), setting, zNodes);
				})
				
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
					fileName : 'sysMenu'
				})
			}
		}
	});
	$(function() {
		var url=transUrl("/sysmenu/list?id=0");
		var table = new BSTable("datatable", url,
				initColumn());
		table.init();
		$("#modal-form").validate({
			rules : {			
				parentId : {
					required : true
				},
				name : {
					required : true
				},
				url : {
					required : true
				},
				type : {
					required : true
				},
				icon : {
					required : true
				},
				orderNum : {
					required : true
				},
				status : {
					required : true
				},
				updateId : {
					required : true
				},
				updateTime : {
					required : true
				}			},
			messages : {
				parentId : {
					required : "请填写父菜单ID，一级菜单为0"
				},
				name : {
					required : "请填写菜单名称"
				},
				url : {
					required : "请填写菜单URL"
				},
				type : {
					required : "请填写类型   0：目录   1：菜单   2：按钮"
				},
				icon : {
					required : "请填写菜单图标"
				},
				orderNum : {
					required : "请填写排序"
				},
				status : {
					required : "请填写"
				},
				updateId : {
					required : "请填写创建者ID"
				},
				updateTime : {
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
		}},{
			title : '菜单名称',
			field : 'name',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'20%',
			formatter:function(value,row,index){
				return overflowHidden(value,8);
			}
		},{
			title : '菜单URL',
			field : 'url',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'15%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},{
			title : '授权',
			field : 'perms',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'10%',
			formatter:function(value,row,index){
				return overflowHidden(value,15);
			}
		},{
			title : '类型 ',
			field : 'type',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'7%',
			formatter:function(value,row,index){
				return transDirectoryType(value);
			}
		},{
			title : '菜单图标',
			field : 'icon',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'5%',
			formatter:function(value,row,index){
				return transIcon(value);
			}
		},{
			title : '排序',
			field : 'orderNum',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'5%',
			formatter:function(value,row,index){
				return overflowHidden(value,25);
			}
		},{
			title : '状态',
			field : 'status',
			align : 'left',
			visible : true,
			sortable:true,
			valign : 'middle',
			width:'5%',
			formatter:function(value,row,index){
				return transMenuStatus(value);
			}
		},{
			title : '创建时间',
			field : 'updateTime',
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
	var setting = {
			 view: {
	         selectedMulti: false
	     },
	     data: {
	         simpleData: {
	             enable: true
	         }
	     },
	     edit: {
	         enable: false
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
	$.get(transUrl("/sysmenu/ztreelist"),function(r){
		var zNodes=r.selectlist;
		/*获取服务器数据*/
		var zTreeObj = $.fn.zTree.init($("#menu-tree"), setting, zNodes);
	})
	function zTreeOnClick(event, treeId, treeNode){
		var url=transUrl("/sysmenu/list?id=")+treeNode.id;
		$('#datatable').bootstrapTable('refresh', {'silent': true,'url':url});
		vue.$data.parentId=treeNode.id;
		event.preventDefault();
		event.stopPropagation();
	}
	
	